package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@FxmlView("land-plot.fxml")
public class LandPlotController {
    LandPlotServiceImpl landPlotService;


    @PersistenceContext
    private EntityManager em;

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
    private Button createButton;

    @FXML
    private Button btnHistory;
    private String btnHistoryInactiveText = "Get History";
    private String btnHistoryActiveText = "Back to all data";
    private Long activeRowID = null;

    @FXML
    private void click(ActionEvent event) {
        LandPlot landPlot = new LandPlot();
        Address address = new Address();
        landPlot.setAddress(address);

        idTextField.setText("");
        updateLandPlotFromTextField(landPlot);
        landPlotService.addLandPlot(landPlot);
        
        refresh();
        landPlotsTabPane.getSelectionModel().select(landPlotsTabView);
    }
    
    @FXML
    private void updateLandPlot(ActionEvent event) {
        Long id = Long.parseLong(idTextField.getText());
        if (id == null) {
            return;
        }
        Optional<LandPlot> landPlot = landPlotService.getById(id);
        landPlot.ifPresent(val -> {
            idTextField.setText("");
            updateLandPlotFromTextField(val);
            landPlotService.updateLandPlot(val);

            refresh();
        });
        landPlotsTabPane.getSelectionModel().select(landPlotsTabView);
    }

    @FXML
    void refresh() {
        ObservableList<LandPlot> plots = FXCollections.observableArrayList(landPlotService.getAll());
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(plots);
    }

    @FXML
    @Transactional
    void getHistory() {
        if (this.btnHistory.getText().equals(btnHistoryInactiveText)) {
            this.btnHistory.setText(btnHistoryActiveText);
            System.out.println(activeRowID);
            List test = getHistoryById(activeRowID);
            List<LandPlot> resultOfSearch = new ArrayList<>();
            test.forEach(objAud -> {
                Object[] testobj = (Object[]) objAud;
                System.out.println("Here");
                resultOfSearch.add((LandPlot) testobj[0]);
                System.out.println(testobj[0]);
                System.out.println(testobj[0].getClass());
                System.out.println(testobj[1]);
                System.out.println(testobj[2]);

            });

            ObservableList<LandPlot> plots = FXCollections.observableArrayList(resultOfSearch);
            this.tableView.getItems().clear();
            this.tableView.getItems().addAll(plots);
        } else {
            this.btnHistory.setText(btnHistoryInactiveText);
            refresh();
        }

    }

    @FXML
    void initialize() {
        updateLandPlot();
        this.tableView.setRowFactory(tv -> {
            TableRow<LandPlot> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    LandPlot clickedRow = row.getItem();
                    if (event.getClickCount() == 2) {
                        activeRowID = row.getItem().getId();
                        feelTextFields(clickedRow);
                        landPlotsTabPane.getSelectionModel().select(landPlotsTabCreate);
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

    public List getHistoryById(long id) {
        return landPlotService.getRevisions(id);
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
    }

    private void updateLandPlotFromTextField(LandPlot landPlot) {
        if (isNotEmptyField(apartmentTextField)) {
            landPlot.getAddress().setApartmentn(Long.parseLong(apartmentTextField.getText()));
            apartmentTextField.setText("");
        }

        if (isNotEmptyField(cityTextField)) {
            landPlot.getAddress().setCity(cityTextField.getText());
            cityTextField.setText("");
        }

        if (isNotEmptyField(homeNumberTextField)) {
            landPlot.getAddress().setHomeNumber(homeNumberTextField.getText());
            homeNumberTextField.setText("");
        }

        if (isNotEmptyField(regionTextField)) {
            landPlot.getAddress().setRegion(regionTextField.getText());
            regionTextField.setText("");
        }

        if (isNotEmptyField(streetTextField)) {
            landPlot.getAddress().setStreet(streetTextField.getText());
            streetTextField.setText("");
        }


        ////////////////////////////////////////////////////
        if (isNotEmptyField(cadastralNumberTextField)) {
            landPlot.setCadastralNumber(cadastralNumberTextField.getText());
            cadastralNumberTextField.setText("");
        }

        if (isNotEmptyField(categoryTextField)) {
            landPlot.setCategory(categoryTextField.getText());
            categoryTextField.setText("");
        }

        if (isNotEmptyField(currentMarksTextField)) {
            landPlot.setCurrentMarks(currentMarksTextField.getText());
            currentMarksTextField.setText("");
        }

        if (isNotEmptyField(intetdedUseTextField)) {
            landPlot.setIntendedUse(intetdedUseTextField.getText());
            intetdedUseTextField.setText("");
        }

        if (isNotEmptyField(landPlotPurposeTextField)) {
            landPlot.setLandPlotPurpose(landPlotPurposeTextField.getText());
            landPlotPurposeTextField.setText("");
        }

        if (isNotEmptyField(notesTextField)) {
            landPlot.setNotes(notesTextField.getText());
            notesTextField.setText("");
        }
    }


    private boolean isNotEmptyField(TextField field) {
        return !field.getText().isEmpty() && !field.getText().equals("");
    }

    private void setTextFieldValue(TextField textField, String value) {
        if (value == null || value.isEmpty()) {
            textField.setText("");
        } else {
            textField.setText(value);
        }
    }


    void updateLandPlot() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcAppartamentn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress().getApartmentn() != null) {
                return new SimpleLongProperty(cellData.getValue().getAddress().getApartmentn()).asObject();
            }
            return null;
        });

        tcCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCity()));
        tcHomeNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getHomeNumber()));
        tcRegion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getRegion()));
        tcStreet.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getStreet()));

        tcCadastralNumber.setCellValueFactory(new PropertyValueFactory<>("cadastralNumber"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tcCurrentMarks.setCellValueFactory(new PropertyValueFactory<>("currentMarks"));
        tcIntendedUse.setCellValueFactory(new PropertyValueFactory<>("intendedUse"));
        tcPurpose.setCellValueFactory(new PropertyValueFactory<>("landPlotPurpose"));
        tcNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        List<LandPlot> plots = landPlotService.getAll();
        this.tableView.getItems().addAll(plots);
    }

    @Autowired
    public LandPlotController(LandPlotServiceImpl landPlotService) {
        this.landPlotService = landPlotService;
    }
}
