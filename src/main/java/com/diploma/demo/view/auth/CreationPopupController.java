package com.diploma.demo.view.auth;

import com.diploma.demo.auth.User;
import com.diploma.demo.auth.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreationPopupController {
    UserService userService;
    private Popup creationPopup;

    @FXML private AnchorPane anchorPane;

    @FXML private TextField tfLogin;
    @FXML private PasswordField pfPass;
    @FXML private PasswordField pfPassConfirm;


    public void configuratePopUp() {
        if (creationPopup == null) {
            creationPopup = new Popup();
            creationPopup.getContent().add(anchorPane);
            anchorPane.setVisible(true);
            anchorPane.setManaged(true);
        }
    }

    public void showPopup(Node ownerNode, double anchorX, double anchorY) {
        if (creationPopup == null) {
            configuratePopUp();
        }
        creationPopup.show(ownerNode, anchorX, anchorY);
    }

    @FXML void initialize() {
        System.err.println("CreationPopupController initialize");

    }

    @FXML private void register() {
        System.out.println("HELLO REGISTER");
        if (creationPopup == null) {
            return;
        }
        if (tfLogin.getText().length() == 0) {
            System.out.println("empty login");
            return;
        }

        if (pfPass.getText().length() == 0) {
            System.out.println("empty password");
            return;
        }

        if (pfPassConfirm.getText().length() == 0) {
            System.out.println("empty password confirm");
            return;
        }

        if (pfPass.getText().equals(pfPassConfirm.getText())) {
            User user = new User();

            user.setUsername(tfLogin.getText());
            user.setPassword(pfPass.getText());
            user.setPasswordConfirm(pfPassConfirm.getText());

            userService.save(user, "ROLE_WORKER");

            closePopup();
        } else {
            System.out.println("pass and confirm pass dont equals");
        }
    }

    @FXML private void closePopup() {
        creationPopup.hide();
    }

    @Autowired
    public CreationPopupController(UserService userService) {
        this.userService = userService;
        System.err.println("CreationPopupController");
    }
}
