package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.repository.impl.LandPlotHistoryServiceImpl;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class LandPlotArchiveController {

    LandPlotHistoryServiceImpl landPlotHistoryService;

    @FXML
    private VBox archiveVBox;

    private TableView<LandPlotHistory> tableView = new TableView<>();
    ;

    private TableColumn<LandPlotHistory, Long> tcID = new TableColumn<>("#");
    private TableColumn<LandPlotHistory, String> tcRegion = new TableColumn<>("Область");
    private TableColumn<LandPlotHistory, String> tcCity = new TableColumn<>("Город");
    private TableColumn<LandPlotHistory, String> tcStreet = new TableColumn<>("Улица");
    private TableColumn<LandPlotHistory, String> tcHomeNumber = new TableColumn<>("Дом");
    private TableColumn<LandPlotHistory, Long> tcAppartamentn = new TableColumn<>("Кв.");

    private TableColumn<LandPlotHistory, String> tcCadastralNumber = new TableColumn<>("Кадастровый номер");
    private TableColumn<LandPlotHistory, String> tcCategory = new TableColumn<>("Категория");
    private TableColumn<LandPlotHistory, String> tcCurrentMarks = new TableColumn<>("тек. оценки");
    private TableColumn<LandPlotHistory, String> tcIntendedUse = new TableColumn<>("Целевое назначение");
    private TableColumn<LandPlotHistory, String> tcPurpose = new TableColumn<>("Назначение");
    private TableColumn<LandPlotHistory, String> tcNotes = new TableColumn<>("Примечания");
    private TableColumn<LandPlotHistory, Double> tcSurface = new TableColumn<>("Поверхность");

    private TableColumn<LandPlotHistory, String> tcActionType = new TableColumn<>("Тип действия");
    private TableColumn<LandPlotHistory, String> tcActionDate = new TableColumn<>("Время действия");


    private void configurateTableView() {
        tableView.getColumns().addAll(tcActionType,tcActionDate, tcID, tcRegion, tcCity, tcStreet, tcHomeNumber, tcAppartamentn,
                tcCadastralNumber, tcCategory, tcCurrentMarks, tcIntendedUse, tcPurpose, tcNotes, tcSurface);

        archiveVBox.getChildren().add(tableView);
        read();
    }

    @FXML
    void initialize() {
        configurateTableView();
    }
    
    void read() {
        tcActionType.setCellValueFactory(cellData ->
                new SimpleStringProperty(LandPlotHistory.getRevtypeString(cellData.getValue().getRevtype()))
        );

        tcActionDate.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getRevisionDate().toString())
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

        List<LandPlotHistory> plots = landPlotHistoryService.getLandPlotHistory();
        this.tableView.getItems().addAll(plots);
    }


    @Autowired
    public LandPlotArchiveController() {
        landPlotHistoryService = new LandPlotHistoryServiceImpl();
    }
}
