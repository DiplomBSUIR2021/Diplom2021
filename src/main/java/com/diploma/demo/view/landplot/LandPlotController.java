package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;
import com.diploma.demo.view.utils.CrudController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("land-plot.fxml")
public class LandPlotController extends CrudController<LandPlot> {
    LandPlotServiceImpl landPlotService;

    @FXML
    private TabPane landPlotsTabPane;
    @FXML
    private Tab landPlotsTabView;
    @FXML
    private Tab landPlotsTabCreate;

    @FXML
    private TableView<LandPlot> tableView;

    @FXML
    private TableColumn<LandPlot, Long> tcID;
    @FXML
    private TableColumn<LandPlot, Long> tcAppartamentn;
    @FXML
    private TableColumn<LandPlot, String> tcCity;
    @FXML
    private TableColumn<LandPlot, String> tcHomeNumber;
    @FXML
    private TableColumn<LandPlot, String> tcRegion;
    @FXML
    private TableColumn<LandPlot, String> tcStreet;
    @FXML
    private TableColumn<LandPlot, String> tcCadastralNumber;
    @FXML
    private TableColumn<LandPlot, String> tcCategory;
    @FXML
    private TableColumn<LandPlot, String> tcCurrentMarks;
    @FXML
    private TableColumn<LandPlot, String> tcIntendedUse;
    @FXML
    private TableColumn<LandPlot, String> tcPurpose;
    @FXML
    private TableColumn<LandPlot, String> tcNotes;
    @FXML
    private TableColumn<LandPlot, Double> tcSurface;

    @FXML
    private TextField idTextField;
    @FXML
    private TextField apartmentTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField homeNumberTextField;
    @FXML
    private TextField regionTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField cadastralNumberTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField currentMarksTextField;
    @FXML
    private TextField intetdedUseTextField;
    @FXML
    private TextField landPlotPurposeTextField;
    @FXML
    private TextField notesTextField;
    @FXML
    private TextField surfaceTextField;

    @FXML
    private Button btnEntityHistory;
    @FXML
    private Button btnFullHistory;

    private Popup popup;
    private DatePicker startDate;
    private DatePicker endDate;

    @FXML
    private HBox HboxSetting;

    @FXML
    private void create() {
        LandPlot landPlot = new LandPlot();
        Address address = new Address();
        landPlot.setAddress(address);

        idTextField.setText("");
        updateObjectFromTextField(landPlot);
        landPlotService.addLandPlot(landPlot);
        
        refresh();
        selectTab(landPlotsTabView);
    }
    
    @FXML
    private void updateLandPlot() {
        update(landPlotService, getIdFromTextField(idTextField) );
        selectTab(landPlotsTabView);
    }

    @FXML
    protected void refresh() {
        refreshTableView(landPlotService);
    }

    @FXML
    void getEntityHistory() {
        getEntityHistory(landPlotService);
    }

    @FXML
    private void getFullHistory() {
        getFullHistory(landPlotService);
    }

    @FXML
    void initialize() {
        setTabPane(landPlotsTabPane);
        setTableView(tableView);

        setBtnEntityHistory(btnEntityHistory);
        setBtnFullHistory(btnFullHistory);

        setTextFieldOnlyDigitsInput(idTextField);
        setTextFieldOnlyDigitsInput(apartmentTextField);

        read();
        this.tableView.setRowFactory(tv -> {
            TableRow<LandPlot> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    LandPlot clickedRow = row.getItem();
                    activeRowID = row.getItem().getId();
                    if (event.getClickCount() == 2) {
                        feelTextFields(clickedRow);
                        selectTab(landPlotsTabCreate);
                    }
                }
            });
            return row ;
        });

        initSettingsPopup();
    }

    @FXML
    private void delete() {
        deleteEntity(landPlotService);
    }

    void feelTextFields(LandPlot landPlot) {
        setTextFieldValue(idTextField, landPlot.getId().toString());
        if (landPlot.getAddress().getApartmentn() != null) {
            setTextFieldValue(apartmentTextField, landPlot.getAddress().getApartmentn().toString());
        } else {
            setTextFieldValue(apartmentTextField,"");
        }
        setTextFieldValue(cityTextField, landPlot.getAddress().getCity());
        setTextFieldValue(homeNumberTextField, landPlot.getAddress().getHomeNumber());
        setTextFieldValue(regionTextField,landPlot.getAddress().getRegion());
        setTextFieldValue(streetTextField, landPlot.getAddress().getStreet());

        setTextFieldValue(cadastralNumberTextField, landPlot.getCadastralNumber());
        setTextFieldValue(categoryTextField, landPlot.getCategory());
        setTextFieldValue(currentMarksTextField, landPlot.getCurrentMarks());
        setTextFieldValue(intetdedUseTextField, landPlot.getIntendedUse());
        setTextFieldValue(landPlotPurposeTextField, landPlot.getLandPlotPurpose());
        setTextFieldValue(notesTextField, landPlot.getNotes());
        setTextFieldValue(surfaceTextField, landPlot.getSurface());
    }

    @Override
    protected void updateObjectFromTextField(LandPlot landPlot) {
        setLongValFromTextField(i -> landPlot.getAddress().setApartmentn(i), apartmentTextField);

        setStringValFromTextField(i -> landPlot.getAddress().setCity(i), cityTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setHomeNumber(i), homeNumberTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setRegion(i), regionTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setStreet(i), streetTextField);

        setStringValFromTextField(landPlot::setCadastralNumber, cadastralNumberTextField);
        setStringValFromTextField(landPlot::setCategory, categoryTextField);
        setStringValFromTextField(landPlot::setCurrentMarks, currentMarksTextField);
        setStringValFromTextField(landPlot::setIntendedUse, intetdedUseTextField);
        setStringValFromTextField(landPlot::setLandPlotPurpose, landPlotPurposeTextField);
        setStringValFromTextField(landPlot::setNotes, notesTextField);

        setDoubleValFromTextField(landPlot::setSurface, surfaceTextField);
    }

    void read() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcAppartamentn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null && cellData.getValue().getAddress().getApartmentn() != null) {
                return new SimpleLongProperty(cellData.getValue().getAddress().getApartmentn()).asObject();
            }
            return null;
        });

        tcCity.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress() != null) {
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

        List<LandPlot> plots = landPlotService.getAll();
        this.tableView.getItems().addAll(plots);
    }

    @Autowired
    public LandPlotController(LandPlotServiceImpl landPlotService) {
        this.landPlotService = landPlotService;
    }


    private void initSettingsPopup() {
        popup = new Popup();
        startDate = new DatePicker();
        endDate = new DatePicker();

        Label startLabel = new Label("start date");
        Label endLabel = new Label("end date");

        Button closeButton = new Button("Close");
        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                setting();
            }
        };
        closeButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        gridPane.add(startLabel,0,0);
        gridPane.add(startDate, 1,0);

        gridPane.add(endLabel,0,1);
        gridPane.add(endDate, 1,1);

        gridPane.add(closeButton, 3,2);

        vBox.setStyle(" -fx-background-color: white");
        vBox.getChildren().add(gridPane);
        vBox.setPrefWidth(500);
        vBox.setPrefHeight(300);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        popup.getContent().add(vBox);


    }


    @FXML
    private void setting() {
        if (!popup.isShowing())
            popup.show(HboxSetting,
                    Screen.getPrimary().getBounds().getWidth() / 2 - 100,
                    Screen.getPrimary().getBounds().getHeight() / 2 - 100);
        else
            popup.hide();
    }
}
