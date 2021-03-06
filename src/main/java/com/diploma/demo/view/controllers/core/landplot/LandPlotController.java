package com.diploma.demo.view.controllers.core.landplot;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;
import com.diploma.demo.view.controllers.CoreCrudController;
import com.diploma.demo.view.elements.DateRangePicker;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("land-plot-page-tab.fxml")
public class LandPlotController extends CoreCrudController<LandPlot> {
    LandPlotServiceImpl landPlotService;

    // add
    // ListView<Restriction> restrictionListView;
    // ListView<Contract> contractListView;
    // ListView<StateRegistration> registrationListView;

    @FXML private TabPane tabPane;
    @FXML private Tab landPlotsTabView;
    @FXML private Tab landPlotsTabCreate;

    @FXML private TableView<LandPlot> tableView;

    @FXML private TableColumn<LandPlot, Long> tcID;
    @FXML private TableColumn<LandPlot, Long> tcAppartamentn;
    @FXML private TableColumn<LandPlot, String> tcCity;
    @FXML private TableColumn<LandPlot, String> tcHomeNumber;
    @FXML private TableColumn<LandPlot, String> tcRegion;
    @FXML private TableColumn<LandPlot, String> tcStreet;
    @FXML private TableColumn<LandPlot, String> tcCadastralNumber;
    @FXML private TableColumn<LandPlot, String> tcCategory;
    @FXML private TableColumn<LandPlot, String> tcCurrentMarks;
    @FXML private TableColumn<LandPlot, String> tcIntendedUse;
    @FXML private TableColumn<LandPlot, String> tcPurpose;
    @FXML private TableColumn<LandPlot, String> tcNotes;
    @FXML private TableColumn<LandPlot, Double> tcSurface;

    @FXML private TextField idTextField;
    @FXML private TextField apartmentTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField homeNumberTextField;
    @FXML private TextField regionTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField cadastralNumberTextField;
    @FXML private TextField categoryTextField;
    @FXML private TextField currentMarksTextField;
    @FXML private TextField intetdedUseTextField;
    @FXML private TextField landPlotPurposeTextField;
    @FXML private TextField notesTextField;
    @FXML private TextField surfaceTextField;

    @FXML private Button buttonCreate;
    @FXML private Button buttonUpdate;

    private DateRangePicker dateRangePicker;

    @FXML
    private HBox HBoxSetting;

    @FXML
    void initialize() {
        initializeController();
    }

    @Override
    protected void configurateControllerElements() {
        setTabPane(tabPane);
        setTableView(tableView);

        setTabView(landPlotsTabView);
        setTabCreate(landPlotsTabCreate);

        setButtonCreate(buttonCreate);
        setButtonUpdate(buttonUpdate);

        setTextFieldOnlyDigitsInput(idTextField);
        setTextFieldOnlyDigitsInput(apartmentTextField);

        read();
        tabPane.getTabs().remove(landPlotsTabCreate);
    }

    @Override
    protected void initTableView() {
        this.tableView.setRowFactory(tv -> {
            TableRow<LandPlot> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    LandPlot clickedRow = row.getItem();
                    activeRowID = row.getItem().getId();
                    if (event.getClickCount() == 2) {
                        selectTabUpdate(clickedRow);
                    }
                }
            });
            return row ;
        });

        dateRangePicker = new DateRangePicker(HBoxSetting);
    }

    @FXML
    private void create() {
        create(landPlotService);
    }

    @Override
    protected LandPlot createEntity() {
        LandPlot landPlot = new LandPlot();
        Address address = new Address();
        landPlot.setAddress(address);

        return landPlot;
    }

    @FXML
    private void update() {
        update(landPlotService, getIdFromTextField(idTextField));
    }

    @FXML
    protected void refresh() {
        refreshTableView(landPlotService);
    }

    @FXML
    private void delete() {
        deleteEntity(landPlotService);
    }

    @Override
    protected void cleanForm() {
        setTextFieldValue(idTextField, "");
        setTextFieldValue(apartmentTextField,"");
        setTextFieldValue(cityTextField, "");
        setTextFieldValue(homeNumberTextField, "");
        setTextFieldValue(regionTextField,"");
        setTextFieldValue(streetTextField, "");

        setTextFieldValue(cadastralNumberTextField, "");
        setTextFieldValue(categoryTextField, "");
        setTextFieldValue(currentMarksTextField, "");
        setTextFieldValue(intetdedUseTextField, "");
        setTextFieldValue(landPlotPurposeTextField, "");
        setTextFieldValue(notesTextField, "");
        setTextFieldValue(surfaceTextField, "");
    }

    protected void feelForm(LandPlot landPlot) {
        setTextFieldValue(idTextField, landPlot.getId().toString());
        if (landPlot.getAddress() != null) {
            if (landPlot.getAddress().getApartmentn() != null) {
                setTextFieldValue(apartmentTextField, landPlot.getAddress().getApartmentn().toString());
            } else {
                setTextFieldValue(apartmentTextField,"");
            }
            setTextFieldValue(cityTextField, landPlot.getAddress().getCity());
            setTextFieldValue(homeNumberTextField, landPlot.getAddress().getHomeNumber());
            setTextFieldValue(regionTextField,landPlot.getAddress().getRegion());
            setTextFieldValue(streetTextField, landPlot.getAddress().getStreet());
        } else {
            setTextFieldValue(apartmentTextField,"");
            setTextFieldValue(cityTextField, "");
            setTextFieldValue(homeNumberTextField, "");
            setTextFieldValue(regionTextField,"");
            setTextFieldValue(streetTextField, "");
        }

        setTextFieldValue(cadastralNumberTextField, landPlot.getCadastralNumber());
        setTextFieldValue(categoryTextField, landPlot.getCategory());
        setTextFieldValue(currentMarksTextField, landPlot.getCurrentMarks());
        setTextFieldValue(intetdedUseTextField, landPlot.getIntendedUse());
        setTextFieldValue(landPlotPurposeTextField, landPlot.getLandPlotPurpose());
        setTextFieldValue(notesTextField, landPlot.getNotes());
        setTextFieldValue(surfaceTextField, landPlot.getSurface());
    }

    @Override
    protected void updateObjectFromForm(LandPlot landPlot) {
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

    @FXML
    private void setting() {
        dateRangePicker.setting();
    }

    @FXML
    private void openCreate() {
        selectTabCreate();
    }
}