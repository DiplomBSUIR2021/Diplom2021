package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.service.impl.LandPlotHistoryServiceImpl;
import com.diploma.demo.view.utils.ArchiveContoller;
import com.diploma.demo.view.utils.DateRangePicker;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LandPlotArchiveController extends ArchiveContoller<LandPlotHistory> {

    LandPlotHistoryServiceImpl landPlotHistoryService;

    private Integer revId;

    @FXML
    private VBox archiveVBox;

    @FXML
    private TabPane tabPane;
    @FXML private Tab tabUpdate;
    @FXML private Tab tabView;

    @FXML
    private Button buttonUpdate;

    private Button btnEntityHistory;

    private final TableView<LandPlotHistory> tableView = new TableView<>();

    private final TableColumn<LandPlotHistory, Long> tcID = new TableColumn<>("#");
    private final TableColumn<LandPlotHistory, String> tcRegion = new TableColumn<>("Область");
    private final TableColumn<LandPlotHistory, String> tcCity = new TableColumn<>("Город");
    private final TableColumn<LandPlotHistory, String> tcStreet = new TableColumn<>("Улица");
    private final TableColumn<LandPlotHistory, String> tcHomeNumber = new TableColumn<>("Дом");
    private final TableColumn<LandPlotHistory, Long> tcAppartamentn = new TableColumn<>("Кв.");

    private final TableColumn<LandPlotHistory, String> tcCadastralNumber = new TableColumn<>("Кадастровый номер");
    private final TableColumn<LandPlotHistory, String> tcCategory = new TableColumn<>("Категория");
    private final TableColumn<LandPlotHistory, String> tcCurrentMarks = new TableColumn<>("тек. оценки");
    private final TableColumn<LandPlotHistory, String> tcIntendedUse = new TableColumn<>("Целевое назначение");
    private final TableColumn<LandPlotHistory, String> tcPurpose = new TableColumn<>("Назначение");
    private final TableColumn<LandPlotHistory, String> tcNotes = new TableColumn<>("Примечания");
    private final TableColumn<LandPlotHistory, Double> tcSurface = new TableColumn<>("Поверхность");

    private final TableColumn<LandPlotHistory, String> tcActionType = new TableColumn<>("Тип действия");
    private final TableColumn<LandPlotHistory, String> tcActionDate = new TableColumn<>("Время действия");
    private final TableColumn<LandPlotHistory, Integer> tcActionRev = new TableColumn<>("id");

    @FXML
    private TextField tfRegion, tfCity, tfStreet, tfHome, tfAppart,
            tfCadastralNumber, tfCategory, tfCurrentMarks, tfIntendenUse,
            tfPurpose, tfNotes, tfSurface;


    private void configureTableView() {
        tableView.getColumns().addAll(tcActionRev, tcActionType, tcActionDate, tcID, tcRegion, tcCity, tcStreet,
                tcHomeNumber, tcAppartamentn, tcCadastralNumber, tcCategory, tcCurrentMarks,
                tcIntendedUse, tcPurpose, tcNotes, tcSurface);

        archiveVBox.getChildren().add(tableView);
        read();
    }

    private void refresh() {
        List<LandPlotHistory> plots = landPlotHistoryService.getAll();
        refreshTableView(FXCollections.observableArrayList(plots));
    }

    private void configureControlPanel() {
        HBox hbox = new HBox();

        Button refresh = new Button("Обновить");
        Button settings = new Button("Настройки");

        btnEntityHistory = new Button();

        hbox.getChildren().addAll(refresh, settings, btnEntityHistory);
        archiveVBox.getChildren().add(hbox);

        DateRangePicker dateRangePicker = new DateRangePicker(hbox);

        settings.setOnAction(event -> dateRangePicker.setting());

        refresh.setOnAction(event -> refresh());

        tabPane.getTabs().remove(tabUpdate);

        btnEntityHistory.setOnAction(event -> {
            this.tableView.getItems().clear();
            getEntityHistory(landPlotHistoryService, dateRangePicker.getStartDate(),dateRangePicker.getEndDate());
        });
    }

    @FXML
    void initialize() {
        setTableView(tableView);

        setTabPane(tabPane);

        setTabUpdate(tabUpdate);
        setTabView(tabView);

        setButtonUpdate(buttonUpdate);

        configureControlPanel();
        configureTableView();

        setBtnEntityHistory(btnEntityHistory);

        tableView.setRowFactory(tv -> {
            TableRow<LandPlotHistory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    LandPlotHistory clickedRow = row.getItem();
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

    void read() {
        tcActionRev.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getRev()).asObject());

        tcActionType.setCellValueFactory(cellData ->
                new SimpleStringProperty(LandPlotHistory.getRevtypeString(cellData.getValue().getRevtype()))
        );

        tcActionDate.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRevisionEntity().getRevisionDate().toString())
        );

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcAppartamentn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null && cellData.getValue().getAddress().getApartmentn() != null) {
                return new SimpleLongProperty(cellData.getValue().getAddress().getApartmentn()).asObject();
            }
            return null;
        });

        tcCity.setCellValueFactory(cellData -> {
            if (cellData.getValue() != null && cellData.getValue().getAddress() != null) {
                return new SimpleStringProperty(cellData.getValue().getAddress().getCity());
            }
            return null;
        });
        tcHomeNumber.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null) {
                return new SimpleStringProperty(cellData.getValue().getAddress().getHomeNumber());
            }
            return null;
        });
        tcRegion.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null) {
                return new SimpleStringProperty(cellData.getValue().getAddress().getRegion());
            }
            return null;
        });
        tcStreet.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null) {
                return new SimpleStringProperty(cellData.getValue().getAddress().getStreet());
            }
            return null;
        });

        tcCadastralNumber.setCellValueFactory(new PropertyValueFactory<>("cadastralNumber"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tcCurrentMarks.setCellValueFactory(new PropertyValueFactory<>("currentMarks"));
        tcIntendedUse.setCellValueFactory(new PropertyValueFactory<>("intendedUse"));
        tcPurpose.setCellValueFactory(new PropertyValueFactory<>("landPlotPurpose"));
        tcNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        tcSurface.setCellValueFactory(new PropertyValueFactory<>("surface"));

        List<LandPlotHistory> plots = landPlotHistoryService.getAll();
        this.tableView.getItems().addAll(plots);
    }

    @FXML
    private void update() {
        update(landPlotHistoryService, revId);
        selectTabView();
    }

    @Autowired
    public LandPlotArchiveController(LandPlotHistoryServiceImpl landPlotService) {
        this.landPlotHistoryService = landPlotService;
    }

    @Override
    protected void cleanForm() {
        tfRegion.setText("");
        tfCity.setText("");
        tfStreet.setText("");
        tfHome.setText("");
        tfAppart.setText("");

        tfCadastralNumber.setText("");
        tfCategory.setText("");
        tfCurrentMarks.setText("");
        tfIntendenUse.setText("");
        tfPurpose.setText("");
        tfNotes.setText("");
        tfSurface.setText("");
    }

    protected void feelForm(LandPlotHistory object) {
        if (object.getAddress() != null) {
            tfRegion.setText(object.getAddress().getRegion());
            tfStreet.setText(object.getAddress().getStreet());
            tfHome.setText(object.getAddress().getHomeNumber());

            if (object.getAddress().getApartmentn() != null) {
                tfAppart.setText(object.getAddress().getApartmentn().toString());
            }
        }

        tfCadastralNumber.setText(object.getCadastralNumber());
        tfCategory.setText(object.getCategory());
        tfCurrentMarks.setText(object.getCurrentMarks());
        tfIntendenUse.setText(object.getIntendedUse());
        tfPurpose.setText(object.getLandPlotPurpose());
        tfNotes.setText(object.getNotes());
        if (object.getSurface() != null) {
            tfSurface.setText(object.getSurface().toString());
        }

    }

    protected void updateObjectFromForm(LandPlotHistory object) {
        setStringValFromTextField(i -> object.getAddress().setRegion(i), tfRegion);
        setLongValFromTextField(i -> object.getAddress().setApartmentn(i), tfAppart);
    }
}
