package com.diploma.demo.view.equipment;

import com.diploma.demo.core.equipment.Equipment;
import com.diploma.demo.core.equipment.repository.EquipmentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
@FxmlView("equipment-window.fxml")
public class EquipmentTable implements Initializable {

    @Autowired
    EquipmentRepository equipmentRepository;

    @FXML
    private GridPane generalTablePane;

    @FXML
    private TableView<Equipment> equipmentTable;

    @FXML
    private TableColumn<Equipment, Integer> col1;

    @FXML
    private TableColumn<Equipment, String> col2;

    @FXML
    private TableColumn<Equipment, Integer> col3;

    @FXML
    private TableColumn<Equipment, Integer> col4;

    @FXML
    private Button editEquipmentButton;

    @FXML
    private Button addEquipmentButton;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private Button deleteEquipmentButton;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private Button refreshButton;

    private List<Equipment> equipment;

    @PostConstruct
    public void init(){
        equipment = equipmentRepository.findAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        col1.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Equipment, String>("model"));
        col3.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("number"));
        col4.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("validity"));


        setTable();
    }

    public void setTable(){
        ObservableList<Equipment> actObservableList = FXCollections.observableArrayList(equipment);
        equipmentTable.setItems(actObservableList);
    }

    public void addEquipment(ActionEvent actionEvent) {
        Equipment newEquipment = new Equipment();
        //newEquipment.setId(Integer.valueOf(textField4.getText()));
        newEquipment.setModel(textField2.getText());
        newEquipment.setNumber(Integer.valueOf(textField3.getText()));
        newEquipment.setValidity(Integer.valueOf(textField4.getText()));
        equipmentRepository.save(newEquipment);
        refreshTable();
    }


    public void editEquipment(ActionEvent actionEvent) {
        Equipment editedEquipment=equipmentRepository.findById(Long.valueOf(textField1.getText())).get();
        editedEquipment.setModel(textField2.getText());
        editedEquipment.setNumber(Integer.valueOf(textField3.getText()));
        editedEquipment.setValidity(Integer.valueOf(textField4.getText()));
        equipmentRepository.save(editedEquipment);
        refreshTable();
    }



    public void deleteEquipment(ActionEvent actionEvent) {
        equipmentRepository.deleteById(Long.valueOf(textField1.getText()));
        refreshTable();
    }

    public void refreshTableButtonAction(ActionEvent actionEvent) {
        refreshTable();
    }

    public void refreshTable(){
        equipmentTable.getItems().clear();
        equipment = equipmentRepository.findAll();
        setTable();
    }







}
