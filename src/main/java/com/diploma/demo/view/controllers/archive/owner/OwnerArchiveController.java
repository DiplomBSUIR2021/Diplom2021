package com.diploma.demo.view.controllers.archive.owner;

import com.diploma.demo.archive.landplot.LandPlotHistory;
import com.diploma.demo.archive.owner.OwnerArchive;
import com.diploma.demo.archive.owner.service.OwnerArchiveServiceImpl;
import com.diploma.demo.view.controllers.ArchiveContoller;
import com.diploma.demo.view.elements.DateRangePicker;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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

    @FXML Button btnEntityHistory;
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
                    // revId = row.getItem().getRev();
                    if (event.getClickCount() == 2) {
                        System.out.println(clickedRow);
                        // selectTabUpdate(clickedRow);
                    }
                }
            });
            return row;
        });
    }

    @Override
    protected void cleanForm() {

    }

    @Override
    protected void feelForm(OwnerArchive object) {

    }

    @Override
    protected void updateObjectFromForm(OwnerArchive object) {

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
}
