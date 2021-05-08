package com.diploma.demo.view.owner;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.service.impl.OwnerServiceImpl;
import com.diploma.demo.view.utils.CrudController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

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
    void initialize() {
        setTabPane(tabPane);
        read();
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

    void refresh() {
        ObservableList<Owner> plots = FXCollections.observableArrayList(ownerService.getAll());
        this.ownerTableView.getItems().clear();
        this.ownerTableView.getItems().addAll(plots);
    }

    private void updateObjectFromTextField(Owner owner) {
        // setLongValFromTextField(i -> owner.getRegistrationAddress().setApartmentn(i), a);

        setStringValFromTextField(i -> owner.setType(i), tfType);
        setStringValFromTextField(i -> owner.setName(i), tfName);
        setDateFromDatePicker(i -> owner.setRegistrationDate(i), tfBirthDate);
        setStringValFromTextField(i -> owner.setDocType(i), tfDocType);
        setStringValFromTextField(i -> owner.setDocN(i) , tfDocN);
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
