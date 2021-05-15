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

@Component
@FxmlView("owner-page-tab.fxml")
public class OwnerController extends CrudController<Owner> {
    OwnerServiceImpl ownerService;

    @FXML
    private TableView<Owner> ownerTableView;
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
    private TableColumn<Owner, String> tcPostAddress;
    @FXML
    private TableColumn<Owner, String> tcPtn;
    @FXML
    private TableColumn<Owner, String> tcPhoneNumber;
    @FXML
    private TableColumn<Owner, String> tcTelegramNumber;
    @FXML
    private TableColumn<Owner, String> tcViberNumber;
    @FXML
    private TableColumn<Owner, String> tcWhatsappNumber;


    @FXML
    private TextField tfID;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private String individualOwnerCase;
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker tfBirthDate;
    @FXML
    private TextField tfDocType;
    @FXML
    private TextField tfDocN;

    @FXML
    private TextField tfRegion;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfHomeNumber;
    @FXML
    private TextField tfApartment;

    @FXML
    private TextField tfPostRegion;
    @FXML
    private TextField tfPostCity;
    @FXML
    private TextField tfPostStreet;
    @FXML
    private TextField tfPostHomeNumber;
    @FXML
    private TextField tfPostApartment;

    @FXML
    private TextField tfPtn;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfTelegramNumber;
    @FXML
    private TextField tfViberNumber;
    @FXML
    private TextField tfWhatsappNumber;

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

        setTextFieldOnlyDigitsInput(tfID);
        setTextFieldOnlyDigitsInput(tfApartment);
        setTextFieldOnlyDigitsInput(tfPostApartment);

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
        update(ownerService, getIdFromTextField(tfID));
        selectTab(tabView);
    }
    @FXML
    private void delete() {
        deleteEntity(ownerService);
    }

    protected void refresh() {
        refreshTableView(ownerService);
    }

    @Override
    protected void updateObjectFromTextField(Owner owner) {
        setStringValFromComboBox(owner::setType, cbType);

        setStringValFromTextField(owner::setName, tfName);

        if (owner.getType().equals(individualOwnerCase)) {
            setDateFromDatePicker(owner::setBirthDate, tfBirthDate);
            eraseDate(owner::setRegistrationDate);
        } else {
            setDateFromDatePicker(owner::setRegistrationDate, tfBirthDate);
            eraseDate(owner::setBirthDate);
        }

        setStringValFromTextField(owner::setDocType, tfDocType);
        setStringValFromTextField(owner::setDocN, tfDocN);

        updateObjectRegistrationAddressFromTextField(owner);
        updateObjectPostAddressFromTextField(owner);
        updateObjectContactsFromTextField(owner);
    }

    private void updateObjectRegistrationAddressFromTextField(Owner owner) {
        if (owner.getRegistrationAddress() == null) {
            owner.setRegistrationAddress(new Address());
        }
        setStringValFromTextField(i -> owner.getRegistrationAddress().setRegion(i), tfRegion);
        setStringValFromTextField(i -> owner.getRegistrationAddress().setCity(i), tfCity);
        setStringValFromTextField(i -> owner.getRegistrationAddress().setStreet(i), tfStreet);
        setStringValFromTextField(i -> owner.getRegistrationAddress().setHomeNumber(i), tfHomeNumber);
        setLongValFromTextField(i -> owner.getRegistrationAddress().setApartmentn(i), tfApartment);
    }

    private void updateObjectPostAddressFromTextField(Owner owner) {
        if (owner.getPostAddress() == null) {
            owner.setPostAddress(new Address());
        }
        setStringValFromTextField(i -> owner.getPostAddress().setRegion(i), tfPostRegion);
        setStringValFromTextField(i -> owner.getPostAddress().setCity(i), tfPostCity);
        setStringValFromTextField(i -> owner.getPostAddress().setStreet(i), tfPostStreet);
        setStringValFromTextField(i -> owner.getPostAddress().setHomeNumber(i), tfPostHomeNumber);
        setLongValFromTextField(i -> owner.getPostAddress().setApartmentn(i), tfPostApartment);
    }

    private void updateObjectContactsFromTextField(Owner owner) {
        setStringValFromTextField(owner::setPtnN, tfPtn);
        setStringValFromTextField(owner::setPhoneNumber, tfPhoneNumber);
        setStringValFromTextField(owner::setTelegramNumber, tfTelegramNumber);
        setStringValFromTextField(owner::setViberNumber, tfViberNumber);
        setStringValFromTextField(owner::setWhatsappNumber, tfWhatsappNumber);
    }

    void feelTextFields(Owner owner) {
        setTextFieldValue(tfID, owner.getId().toString());
        setComboBoxValue(cbType, owner.getType());
        setTextFieldValue(tfName, owner.getName());

        if (owner.getType().equals(individualOwnerCase)) {
            setDatePicker(tfBirthDate, owner.getBirthDate());
        } else {
            setDatePicker(tfBirthDate, owner.getRegistrationDate());
        }

        setTextFieldValue(tfDocType, owner.getDocType());
        setTextFieldValue(tfDocN, owner.getDocN());

        if (owner.getRegistrationAddress() != null) {
            setTextFieldValue(tfRegion, owner.getRegistrationAddress().getRegion());
            setTextFieldValue(tfCity, owner.getRegistrationAddress().getCity());
            setTextFieldValue(tfStreet, owner.getRegistrationAddress().getStreet());
            setTextFieldValue(tfHomeNumber, owner.getRegistrationAddress().getHomeNumber());

            if (owner.getRegistrationAddress().getApartmentn() != null) {
                setTextFieldValue(tfApartment, owner.getRegistrationAddress().getApartmentn().toString());
            }
        }

        if (owner.getPostAddress() != null) {
            setTextFieldValue(tfPostRegion, owner.getPostAddress().getRegion());
            setTextFieldValue(tfPostCity, owner.getPostAddress().getCity());
            setTextFieldValue(tfPostStreet, owner.getPostAddress().getStreet());
            setTextFieldValue(tfPostHomeNumber, owner.getPostAddress().getHomeNumber());

            if (owner.getPostAddress().getApartmentn() != null) {
                setTextFieldValue(tfPostApartment, owner.getPostAddress().getApartmentn().toString());
            }
        }

        setTextFieldValue(tfPtn, owner.getPtnN());
        setTextFieldValue(tfPhoneNumber, owner.getPhoneNumber());
        setTextFieldValue(tfTelegramNumber, owner.getTelegramNumber());
        setTextFieldValue(tfViberNumber, owner.getViberNumber());
        setTextFieldValue(tfWhatsappNumber, owner.getWhatsappNumber());
    }

    private void read() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        tcDocType.setCellValueFactory(new PropertyValueFactory<>("docType"));
        tcDocN.setCellValueFactory(new PropertyValueFactory<>("docN"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(getFullAddress(cellData.getValue().getRegistrationAddress())));
        tcPostAddress.setCellValueFactory(cellData -> new SimpleStringProperty(getFullAddress(cellData.getValue().getPostAddress())));

        tcPtn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPtnN()));
        tcPhoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tcTelegramNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelegramNumber()));
        tcViberNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getViberNumber()));
        tcWhatsappNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWhatsappNumber()));

        List<Owner> owners = ownerService.getAll();
        this.ownerTableView.getItems().addAll(owners);
    }

    @Autowired
    public OwnerController( OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }
}
