package com.diploma.demo.view.landplot;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
/*import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;*/
import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@FxmlView("land-plot.fxml")
public class LandPlotControllerCreate {
    LandPlotServiceImpl landPlotService;

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

    @Autowired
    public LandPlotControllerCreate(LandPlotServiceImpl landPlotService) {
        this.landPlotService = landPlotService;
    }

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

    private boolean isNotEmptyField(TextField field) {
        return !field.getText().isEmpty();
    }


}
