package com.diploma.demo.view.equipment;


import com.diploma.demo.core.installedequipment.InstalledEquipment;
import com.diploma.demo.core.installedequipment.repository.InstalledEquipmentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("installed-equipment-window.fxml")
public class InstalledEquipmentTable implements Initializable {

    @Autowired
    InstalledEquipmentRepository installedEquipmentRepository;

    @FXML
    private GridPane generalTablePane;

    @FXML
    private TableView<InstalledEquipment> installedEquipmentTable;

    @FXML
    private TableColumn<InstalledEquipment, Integer> col1;

    @FXML
    private TableColumn<InstalledEquipment, Integer> col2;

    @FXML
    private TableColumn<InstalledEquipment, String> col3;

    @FXML
    private TableColumn<InstalledEquipment, Integer> col4;

    @FXML
    private TableColumn<InstalledEquipment, Integer> col5;

    @FXML
    private TableColumn<InstalledEquipment, LocalDate> col6;

    @FXML
    private TableColumn<InstalledEquipment, LocalDate> col7;

    @FXML
    private Button refreshButton;

    @FXML
    private Button deleteInstalledEquipment;

    @FXML
    private Button editInstalledEquipment;

    @FXML
    private Button addInstalledEquipment;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField5;

    @FXML
    private DatePicker dataField1;

    @FXML
    private Button checkEquipmentUpdateButton;

    private List<InstalledEquipment> installedEquipment;

    @PostConstruct
    public void init(){
        installedEquipment = installedEquipmentRepository.findAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        col1.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,Integer>("placement"));
        col3.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,String>("model"));
        col4.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,Integer>("number"));
        col5.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,Integer>("validity"));
        col6.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,LocalDate>("installationDate"));
        col7.setCellValueFactory(new PropertyValueFactory<InstalledEquipment,LocalDate>("replacementDate"));


       setTable();
    }

    public void setTable(){
        ObservableList<InstalledEquipment> installedEquipmentObservableList = FXCollections.observableArrayList(installedEquipment);
        installedEquipmentTable.setItems(installedEquipmentObservableList);
    }


    public void editInstalledEquipment(ActionEvent actionEvent) {
        InstalledEquipment editedInstalledEquipment= installedEquipmentRepository.findById(Long.valueOf(textField1.getText())).get();
        editedInstalledEquipment.setPlacement(Integer.valueOf(textField2.getText()));
        editedInstalledEquipment.setModel(textField3.getText());
        editedInstalledEquipment.setNumber(Integer.valueOf(textField4.getText()));
        editedInstalledEquipment.setValidity(Integer.valueOf(textField5.getText()));
        editedInstalledEquipment.setInstallationDate(dataField1.getValue());
        editedInstalledEquipment.setReplacementDate(dataField1.getValue().plusDays(Long.parseLong(textField5.getText())));
        installedEquipmentRepository.save(editedInstalledEquipment);
        refreshTable();
    }

    public void addInstalledEquipment(ActionEvent actionEvent) {
        InstalledEquipment newInstalledEquipment = new InstalledEquipment();
        newInstalledEquipment.setPlacement(Integer.valueOf(textField2.getText()));
        newInstalledEquipment.setModel(textField3.getText());
        newInstalledEquipment.setNumber(Integer.valueOf(textField4.getText()));
        newInstalledEquipment.setValidity(Integer.valueOf(textField5.getText()));
        newInstalledEquipment.setInstallationDate(dataField1.getValue());
        newInstalledEquipment.setReplacementDate(dataField1.getValue().plusDays(Long.parseLong(textField5.getText())));
        installedEquipmentRepository.save(newInstalledEquipment);
        refreshTable();
    }

    public void deleteInstalledEquipment(ActionEvent actionEvent) {
        installedEquipmentRepository.deleteById(Long.valueOf(textField1.getText()));
        refreshTable();
    }

    public void equipmentCheckUpdate(ActionEvent actionEvent) {
        InstalledEquipment editedInstalledEquipment= installedEquipmentRepository.findById(Long.valueOf(textField1.getText())).get();
//        editedInstalledEquipment.setPlacement(Integer.valueOf(textField2.getText()));
//        editedInstalledEquipment.setModel(textField2.getText());
//        editedInstalledEquipment.setNumber(Integer.valueOf(textField3.getText()));
//        editedInstalledEquipment.setValidity(Integer.valueOf(textField4.getText()));
        editedInstalledEquipment.setInstallationDate(LocalDate.now());
        editedInstalledEquipment.setReplacementDate(LocalDate.now().plusDays(editedInstalledEquipment.getValidity()));
        installedEquipmentRepository.save(editedInstalledEquipment);
        refreshTable();
    }

    public void refreshTableButtonAction(ActionEvent actionEvent) {
        refreshTable();
    }

    public void refreshTable(){
        installedEquipmentTable.getItems().clear();
        installedEquipment = installedEquipmentRepository.findAll();
        setTable();
    }



}
