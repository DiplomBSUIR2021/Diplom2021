package com.diploma.demo.view.mail;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@FxmlView("incoming-mail-page.fxml")

public class IncomingMailPage {

    @FXML
    public Button registrationBtn;

    @FXML
    public Button journalBtn;


    public void registrationIncomingMails(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("registration/registration-incoming-mails.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Окно регистрации входящей корреспонденции");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void journalIncomingMails(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("journal/journal-incoming-mails.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Журнал входящей корреспонденции");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}


