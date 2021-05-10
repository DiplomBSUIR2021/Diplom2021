package com.diploma.demo.view.owner;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.service.impl.OwnerServiceImpl;
import com.diploma.demo.view.utils.CrudController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
@FxmlView("owner-page-tab.fxml")
public class OwnerController extends CrudController {
    OwnerServiceImpl ownerService;

    @FXML
    private TableView ownerTableView;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabView;
    @FXML
    private Tab tabCreate;

    @FXML
    private TableColumn<Owner, Long> tcID;
    @FXML
    private TableColumn<Owner, String> tcType;
    @FXML
    private TableColumn<Owner, String> tcName;
    @FXML
    private TableColumn<Owner, Date> tcBirthDate;
    @FXML
    private TableColumn<Owner, String> tcDocType;
    @FXML
    private TableColumn<Owner, String> tcDocN;
    @FXML
    private TableColumn<Owner, String> tcRegistrationDate;
    @FXML
    private TableColumn<Owner, String> tcAddress;

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker tfBirthDate;
    @FXML
    private TextField tfDocType;
    @FXML
    private TextField tfDocN;

    @FXML
    private Button btnEntityHistory;
    @FXML
    private Button btnFullHistory;

    @FXML
    void initialize() {
        setTabPane(tabPane);
        setTableView(ownerTableView);

        setBtnEntityHistory(btnEntityHistory);
        setBtnFullHistory(btnFullHistory);

        read();
        this.ownerTableView.setRowFactory(tv -> {
            TableRow<Owner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    Owner clickedRow = row.getItem();
                    activeRowID = row.getItem().getId();
                    if (event.getClickCount() == 2) {
                        feelTextFields(clickedRow);
                        selectTab(tabCreate);
                        System.out.println("row clicket");
                        System.out.println(clickedRow.getId());
                    } else if (event.getClickCount() == 1) {
                        System.out.println("1 click");
                        System.out.println(clickedRow.getId());
                    }
                }
            });
            return row;
        });
    }
    @FXML
    private void getEntityHistory() {
        getEntityHistory(ownerService);
    }
    @FXML
    private void getFullHistory() {
        getFullHistory(ownerService);
    }

    @FXML
    private void create() {
        Owner owner = new Owner();
        Address address = new Address();
        owner.setRegistrationAddress(address);

        tfID.setText("");
        updateObjectFromTextField(owner);
        ownerService.addOwner(owner);

        refresh();
        selectTab(tabView);
    }
    @FXML
    private void update() {
        Long id = getIdFromTextField(tfID);
        if (id == null) {
            return;
        }
        Optional<Owner> owner = ownerService.findById(id);
        owner.ifPresent(val -> {
            tfID.setText("");
            updateObjectFromTextField(val);
            ownerService.updateOwner(val);

            refresh();
        });
        selectTab(tabView);
    }
    @FXML
    private void delete() {
        deleteEntity(ownerService);
    }

    protected void refresh() {
        refreshTableView(ownerService);
    }

    private void updateObjectFromTextField(Owner owner) {
        // setLongValFromTextField(i -> owner.getRegistrationAddress().setApartmentn(i), a);

        setStringValFromTextField(i -> owner.setType(i), tfType);
        setStringValFromTextField(i -> owner.setName(i), tfName);

        setDateFromDatePicker(i -> owner.setRegistrationDate(i), tfBirthDate);

        setStringValFromTextField(i -> owner.setDocType(i), tfDocType);
        setStringValFromTextField(i -> owner.setDocN(i) , tfDocN);
    }

    void feelTextFields(Owner owner) {
        setTextFieldValue(tfID, owner.getId().toString());
        setTextFieldValue(tfType, owner.getType());
        setTextFieldValue(tfName, owner.getName());

        setDatePicker(tfBirthDate, owner.getRegistrationDate());

        setTextFieldValue(tfDocType, owner.getDocType());
        setTextFieldValue(tfDocN, owner.getDocN());
    }

    private void read() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        tcDocType.setCellValueFactory(new PropertyValueFactory<>("docType"));
        tcDocN.setCellValueFactory(new PropertyValueFactory<>("docN"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(getFullAddress(cellData.getValue().getRegistrationAddress())));

        List<Owner> owners = ownerService.getAll();
        this.ownerTableView.getItems().addAll(owners);
    }

    @Autowired
    public OwnerController( OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }
}
