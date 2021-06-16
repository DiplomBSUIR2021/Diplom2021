package com.diploma.demo.view.controllers.archive.owner;

import com.diploma.demo.archive.landplot.LandPlotHistory;
import com.diploma.demo.archive.owner.OwnerArchive;
import com.diploma.demo.archive.owner.service.OwnerArchiveServiceImpl;
import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.view.controllers.ArchiveContoller;
import com.diploma.demo.view.elements.DateRangePicker;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class OwnerArchiveController extends ArchiveContoller<OwnerArchive> {

    OwnerArchiveServiceImpl ownerArchiveService;

    @FXML private TableView<OwnerArchive> tableView;
    @FXML private TableColumn<OwnerArchive, Integer> tcRevId;
    @FXML private TableColumn<OwnerArchive, String> tcActionType;
    @FXML private TableColumn<OwnerArchive, String> tcActionDate;
    @FXML private TableColumn<OwnerArchive, Long> tcId;
    @FXML private TableColumn<OwnerArchive, String> tcType;
    @FXML private TableColumn<OwnerArchive, String> tcName;
    @FXML private TableColumn<OwnerArchive, String> tcRegistrationDate;
    @FXML private TableColumn<OwnerArchive, Date> tcBirthDate;
    @FXML private TableColumn<OwnerArchive, String> tcDocType;
    @FXML private TableColumn<OwnerArchive, String> tcDocN;
    @FXML private TableColumn<OwnerArchive, String> tcAddress;
    @FXML private TableColumn<OwnerArchive, String> tcPostAddress;
    @FXML private TableColumn<OwnerArchive, String> tcPtn;
    @FXML private TableColumn<OwnerArchive, String> tcPhoneNumber;
    @FXML private TableColumn<OwnerArchive, String> tcTelegramNumber;
    @FXML private TableColumn<OwnerArchive, String> tcViberNumber;
    @FXML private TableColumn<OwnerArchive, String> tcWhatsappNumber;

    @FXML private TextField tfId;
    @FXML private ComboBox<String> cbType;
    @FXML private String individualOwnerCase;
    @FXML private TextField tfName;
    @FXML private DatePicker tfBirthDate;
    @FXML private TextField tfDocType;
    @FXML private TextField tfDocN;

    @FXML private TextField tfRegion;
    @FXML private TextField tfCity;
    @FXML private TextField tfStreet;
    @FXML private TextField tfHomeNumber;
    @FXML private TextField tfApartment;

    @FXML private TextField tfPostRegion;
    @FXML private TextField tfPostCity;
    @FXML private TextField tfPostStreet;
    @FXML private TextField tfPostHomeNumber;
    @FXML private TextField tfPostApartment;

    @FXML private TextField tfPtn;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfTelegramNumber;
    @FXML private TextField tfViberNumber;
    @FXML private TextField tfWhatsappNumber;

    @FXML private TabPane tabPane;
    @FXML private Tab tabUpdate;
    @FXML private Tab tabView;

    @FXML Button btnEntityHistory;
    @FXML Button btnUpdateEntity;
    @FXML private HBox HBoxSetting;
    private DateRangePicker dateRangePicker;

    @Autowired
    public OwnerArchiveController(OwnerArchiveServiceImpl ownerArchiveService) {
        this.ownerArchiveService = ownerArchiveService;
    }

    @FXML private void initialize() {
        initializeController();
    }

    @FXML private void refresh() {
        refreshTableView(ownerArchiveService);
    }

    @FXML private void setting() {
        dateRangePicker.setting();
    }

    @FXML private void getEntityHistory() {
        tableView.getItems().clear();
        getEntityHistory(ownerArchiveService, dateRangePicker.getStartDate(),dateRangePicker.getEndDate());
    }

    @Override
    protected void configurateControllerElements() {
        System.out.println("hello owner archive controller");
        setTableView(tableView);
        setBtnEntityHistory(btnEntityHistory);
        setButtonUpdate(btnUpdateEntity);

        setTabPane(tabPane);
        setTabUpdate(tabUpdate);
        setTabView(tabView);

        read();
        dateRangePicker = new DateRangePicker(HBoxSetting);
    }

    @Override
    protected void initTableView() {
        tableView.setRowFactory(tv -> {
            TableRow<OwnerArchive> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    OwnerArchive clickedRow = row.getItem();
                    activeRowID = clickedRow.getId();
                    revId = row.getItem().getRev();
                    if (event.getClickCount() == 2) {
                        System.out.println(clickedRow);
                        selectTabUpdate(clickedRow);
                    }
                }
            });
            return row;
        });
    }

    @Override
    protected void cleanForm() {
        setTextFieldValue(tfId, "");
        setTextFieldValue(tfName, "");
        setTextFieldValue(tfDocType, "");
        setTextFieldValue(tfDocN, "");

        setTextFieldValue(tfRegion, "");
        setTextFieldValue(tfCity, "");
        setTextFieldValue(tfStreet, "");
        setTextFieldValue(tfHomeNumber, "");
        setTextFieldValue(tfApartment, "");

        setTextFieldValue(tfPostRegion, "");
        setTextFieldValue(tfPostCity, "");
        setTextFieldValue(tfPostStreet, "");
        setTextFieldValue(tfPostHomeNumber, "");
        setTextFieldValue(tfPostApartment, "");

        setTextFieldValue(tfPtn,"");
        setTextFieldValue(tfPhoneNumber, "");
        setTextFieldValue(tfTelegramNumber, "");
        setTextFieldValue(tfViberNumber, "");
        setTextFieldValue(tfWhatsappNumber, "");

        setDatePicker(tfBirthDate, null);

        String cbTypeDefault = "физлицо";
        setComboBoxValue(cbType, cbTypeDefault);
    }

    @Override
    protected void feelForm(OwnerArchive owner) {
        setTextFieldValue(tfId, owner.getId().toString());
        setComboBoxValue(cbType, owner.getType());
        setTextFieldValue(tfName, owner.getName());

        if (owner.getType().equals(individualOwnerCase)) {
            setDatePicker(tfBirthDate, owner.getBirthDate());
        } else {
            setDatePicker(tfBirthDate, owner.getRegistrationDate());
        }

        setTextFieldValue(tfDocType, owner.getDocType());
        setTextFieldValue(tfDocN, owner.getDocN());

        if (owner.getAddress() != null) {
            setTextFieldValue(tfRegion, owner.getAddress().getRegion());
            setTextFieldValue(tfCity, owner.getAddress().getCity());
            setTextFieldValue(tfStreet, owner.getAddress().getStreet());
            setTextFieldValue(tfHomeNumber, owner.getAddress().getHomeNumber());

            if (owner.getAddress().getApartmentn() != null) {
                setTextFieldValue(tfApartment, owner.getAddress().getApartmentn().toString());
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

    @Override
    protected void updateObjectFromForm(OwnerArchive owner) {
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

    private void updateObjectRegistrationAddressFromTextField(OwnerArchive owner) {
        if (owner.getAddress() == null) {
            owner.setAddress(new Address());
        }
        setStringValFromTextField(i -> owner.getAddress().setRegion(i), tfRegion);
        setStringValFromTextField(i -> owner.getAddress().setCity(i), tfCity);
        setStringValFromTextField(i -> owner.getAddress().setStreet(i), tfStreet);
        setStringValFromTextField(i -> owner.getAddress().setHomeNumber(i), tfHomeNumber);
        setLongValFromTextField(i -> owner.getAddress().setApartmentn(i), tfApartment);
    }

    private void updateObjectPostAddressFromTextField(OwnerArchive owner) {
        if (owner.getPostAddress() == null) {
            owner.setPostAddress(new Address());
        }
        setStringValFromTextField(i -> owner.getPostAddress().setRegion(i), tfPostRegion);
        setStringValFromTextField(i -> owner.getPostAddress().setCity(i), tfPostCity);
        setStringValFromTextField(i -> owner.getPostAddress().setStreet(i), tfPostStreet);
        setStringValFromTextField(i -> owner.getPostAddress().setHomeNumber(i), tfPostHomeNumber);
        setLongValFromTextField(i -> owner.getPostAddress().setApartmentn(i), tfPostApartment);
    }

    private void updateObjectContactsFromTextField(OwnerArchive owner) {
        setStringValFromTextField(owner::setPtnN, tfPtn);
        setStringValFromTextField(owner::setPhoneNumber, tfPhoneNumber);
        setStringValFromTextField(owner::setTelegramNumber, tfTelegramNumber);
        setStringValFromTextField(owner::setViberNumber, tfViberNumber);
        setStringValFromTextField(owner::setWhatsappNumber, tfWhatsappNumber);
    }

    private void read() {
        tcRevId.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getRev()).asObject());

        tcActionType.setCellValueFactory(cellData ->
                new SimpleStringProperty(LandPlotHistory.getRevtypeString(cellData.getValue().getRevtype()))
        );

        tcActionDate.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRevisionEntity().getRevisionDate().toString())
        );

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcType.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getType())
        );

        tcName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName())
        );
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tcDocType.setCellValueFactory(new PropertyValueFactory<>("docType"));
        tcDocN.setCellValueFactory(new PropertyValueFactory<>("docN"));

        tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(getFullAddress(cellData.getValue().getAddress())));
        tcPostAddress.setCellValueFactory(cellData -> new SimpleStringProperty(getFullAddress(cellData.getValue().getPostAddress())));

        tcPtn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPtnN()));
        tcPhoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tcTelegramNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelegramNumber()));
        tcViberNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getViberNumber()));
        tcWhatsappNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWhatsappNumber()));

        List<OwnerArchive> owners = ownerArchiveService.getAll();
        tableView.getItems().addAll(owners);
    }

    @FXML private void update() {
        update(ownerArchiveService);
    }
}
