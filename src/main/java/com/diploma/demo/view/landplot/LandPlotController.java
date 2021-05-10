package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;
import com.diploma.demo.view.utils.CrudController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Component
@FxmlView("land-plot.fxml")
public class LandPlotController<T> extends CrudController {
    LandPlotServiceImpl landPlotService;


    @FXML
    private TabPane landPlotsTabPane;
    @FXML
    private Tab landPlotsTabView;
    @FXML
    private Tab landPlotsTabCreate;

    @FXML
    private TableView tableView;

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

    @FXML
    private void click(ActionEvent event) {
        LandPlot landPlot = new LandPlot();
        Address address = new Address();
        landPlot.setAddress(address);

        idTextField.setText("");
        updateLandPlotFromTextField(landPlot);
        landPlotService.addLandPlot(landPlot);
        
        refresh();
        selectTab(landPlotsTabView);
    }
    
    @FXML
    private void updateLandPlot(ActionEvent event) {
        String idText = idTextField.getText();
        if (idText == null || idText.isEmpty()) {
            return;
        }
        Long id = Long.parseLong(idTextField.getText());

        Optional<LandPlot> landPlot = landPlotService.getById(id);
        landPlot.ifPresent(val -> {
            idTextField.setText("");
            updateLandPlotFromTextField(val);
            landPlotService.updateLandPlot(val);

            refresh();
        });
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

        updateLandPlot();
        this.tableView.setRowFactory(tv -> {
            TableRow<LandPlot> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    LandPlot clickedRow = row.getItem();
                    if (event.getClickCount() == 2) {
                        activeRowID = row.getItem().getId();
                        feelTextFields(clickedRow);
                        selectTab(landPlotsTabCreate);
                        System.out.println("row clicket");
                        System.out.println(clickedRow.getId());
                    } else if (event.getClickCount() == 1) {
                        activeRowID = row.getItem().getId();
                        System.out.println("1 click");
                        System.out.println(clickedRow.getId());
                    }
                }
            });
            return row ;
        });
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

    private void updateLandPlotFromTextField(LandPlot landPlot) {
        setLongValFromTextField(i -> landPlot.getAddress().setApartmentn(i), apartmentTextField);

        setStringValFromTextField(i -> landPlot.getAddress().setCity(i), cityTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setHomeNumber(i), homeNumberTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setRegion(i), regionTextField);
        setStringValFromTextField(i -> landPlot.getAddress().setStreet(i), streetTextField);

        ////////////////////////////////////////////////////
        setStringValFromTextField(i -> landPlot.setCadastralNumber(i), cadastralNumberTextField);
        setStringValFromTextField(i -> landPlot.setCategory(i), categoryTextField);
        setStringValFromTextField(i -> landPlot.setCurrentMarks(i), currentMarksTextField);
        setStringValFromTextField(i -> landPlot.setIntendedUse(i), intetdedUseTextField);
        setStringValFromTextField(i -> landPlot.setLandPlotPurpose(i), landPlotPurposeTextField);
        setStringValFromTextField(i -> landPlot.setNotes(i) , notesTextField);

        setDoubleValFromTextField(i -> landPlot.setSurface(i), surfaceTextField);
    }

    void updateLandPlot() {
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
}
