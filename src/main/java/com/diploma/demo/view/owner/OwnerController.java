package com.diploma.demo.view.owner;

import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.service.impl.OwnerServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@FxmlView("owner-page-tab.fxml")
public class OwnerController {
    OwnerServiceImpl ownerService;

    @FXML
    private TableView ownerTableView;

    @FXML
    private TableColumn<Owner, Long> tcID;
    @FXML
    private TableColumn<Owner, String> tcType;
    @FXML
    private TableColumn<Owner, String> tcName;
    @FXML
    private TableColumn<Owner, Date> tcBirthDate;
    @FXML
    private TableColumn<Owner, String> tcDocType;
    @FXML
    private TableColumn<Owner, String> tcDocN;
    @FXML
    private TableColumn<Owner, String> tcRegistrationDate;

    @FXML
    void initialize() {
        read();
    }

    private void read() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        tcDocType.setCellValueFactory(new PropertyValueFactory<>("docType"));
        tcDocN.setCellValueFactory(new PropertyValueFactory<>("docN"));
        tcRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        List<Owner> owners = ownerService.getAll();
        this.ownerTableView.getItems().addAll(owners);
    }

    @Autowired
    public OwnerController( OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }
}
