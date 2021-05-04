package com.diploma.demo.view.maintab;

import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.repository.OwnerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@FxmlView("main-page-tab.fxml")
public class MainTabController {

    @Autowired
    private OwnerRepository ownerRepository;


    @FXML private TableView<Owner> individualOwnerTableView;
    @FXML private TableView<Owner> businessOwnerTableView;

    public void openCreateIndividual(ActionEvent event){
    }

    public void openCreateBusiness(ActionEvent event){

    }
}
