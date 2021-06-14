package com.diploma.demo.view;

import com.diploma.demo.view.mail.IncomingMailPage;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import liquibase.pro.packaged.T;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@FxmlView("mail-page-tab.fxml")
public class MailController {

    @FXML
    public Tab IncomingMailPage;
}

