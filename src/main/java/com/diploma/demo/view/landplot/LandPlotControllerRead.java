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

import java.util.List;


@Component
@FxmlView("land-plot.fxml")
public class LandPlotControllerRead {
    LandPlotServiceImpl landPlotService;

    @FXML
    private TableView tableView;



    @FXML
    private TextField appartmentTextField;
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
    private TextField LandPlotPurposeTextField;
    @FXML
    private TextField notesTextField;
    @FXML
    private TextField surfaceTextField;

    @FXML
    private Button createButton;

    @FXML
    private void click(ActionEvent event) {
        LandPlot landPlot = new LandPlot();
        Address address = new Address();

        landPlot.setAddress(address);

        if (isNotEmptyField(appartmentTextField)) {
            address.setApartmentn(Long.parseLong(appartmentTextField.getText()));
            appartmentTextField.setText("");
        }

        if (isNotEmptyField(cityTextField)) {
            address.setCity(cityTextField.getText());
            cityTextField.setText("");
        }

        if (isNotEmptyField(homeNumberTextField)) {
            address.setHomeNumber(homeNumberTextField.getText());
            homeNumberTextField.setText("");
        }

        if (isNotEmptyField(regionTextField)) {
            address.setRegion(regionTextField.getText());
            regionTextField.setText("");
        }

        if (isNotEmptyField(streetTextField)) {
            address.setStreet(streetTextField.getText());
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

        if (isNotEmptyField(LandPlotPurposeTextField)) {
            landPlot.setLandPlotPurpose(LandPlotPurposeTextField.getText());
            LandPlotPurposeTextField.setText("");
        }

        if (isNotEmptyField(notesTextField)) {
            landPlot.setNotes(notesTextField.getText());
            notesTextField.setText("");
        }

        landPlotService.addLandPlot(landPlot);

        createButton.setText("You've clicked!");
    }

    @FXML
    void refresh() {
        ObservableList<LandPlot> plots = FXCollections.observableArrayList(landPlotService.getAll());
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(plots);
    }

    @FXML
    void initialize() {
        update();
        this.tableView.setRowFactory(tv -> {
            TableRow<LandPlot> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    LandPlot clickedRow = row.getItem();
                    System.out.println("row clicket");
                    System.out.println(clickedRow.getId());
                }
            });
            return row ;
        });
    }

    private boolean isNotEmptyField(TextField field) {
        return !field.getText().isEmpty();
    }

    void update() {
        TableColumn<LandPlot, Long> nameColumn = new TableColumn<>("ID");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tableView.getColumns().add(nameColumn);

        TableColumn<LandPlot, Long> appart = new TableColumn<>("Appartamentn");
        appart.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAddress().getApartmentn() != null) {
                return new SimpleLongProperty(cellData.getValue().getAddress().getApartmentn()).asObject();
            }
            return null;
        });
        this.tableView.getColumns().add(appart);


        TableColumn<LandPlot, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCity()));
        this.tableView.getColumns().add(cityColumn);


        TableColumn<LandPlot, String> homeNumber =   new TableColumn<>("Home number");
        homeNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getHomeNumber()));
        this.tableView.getColumns().add(homeNumber);


        TableColumn<LandPlot, String> region  = new TableColumn<>("Region");
        region.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getRegion()));
        this.tableView.getColumns().add(region);

        TableColumn<LandPlot, String> street  = new TableColumn<>("Street");
        street.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getStreet()));
        this.tableView.getColumns().add(street);

        addStringValueInTable("cadastral_number", "cadastralNumber");
        addStringValueInTable("Category", "category");
        addStringValueInTable("Current marks", "currentMarks");
        addStringValueInTable("Intended use", "intendedUse");
        addStringValueInTable("Purpose", "landPlotPurpose");
        addStringValueInTable("Notes", "notes");

        List<LandPlot> plots = landPlotService.getAll();
        this.tableView.getItems().addAll(plots);
    }

    private void addStringValueInTable(String name, String nameInDatabase) {
        TableColumn<LandPlot, String> columm = new TableColumn<>(name);
        columm.setCellValueFactory(new PropertyValueFactory<>(nameInDatabase));
        this.tableView.getColumns().add(columm);
    }


    @Autowired
    public LandPlotControllerRead(LandPlotServiceImpl landPlotService) {
        this.landPlotService = landPlotService;
    }
}
