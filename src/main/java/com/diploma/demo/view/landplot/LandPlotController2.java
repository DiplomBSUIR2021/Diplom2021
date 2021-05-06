package com.diploma.demo.view.landplot;


import com.diploma.demo.core.act.Contract;
import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import com.diploma.demo.core.restriction.Restriction;
import com.diploma.demo.core.stateregistration.StateRegistration;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("land-plot.fxml")
public class LandPlotController2 {

    @Autowired
    private LandPlotRepository landPlotRepository;

    @FXML
    private Label cadastralNumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label surfaceLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label intendedUseLabel;
    @FXML
    private Label landPlotPurposeLabel;
    @FXML
    private Label currentMarksLabel;
    @FXML
    private Label notesLabel;

    @FXML
    private ListView<Restriction> restrictionListView;
    @FXML
    private ListView<Contract> contractListView;
    @FXML
    private ListView<StateRegistration> registrationListView;


}
