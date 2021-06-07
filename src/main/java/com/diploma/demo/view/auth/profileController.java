package com.diploma.demo.view.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class profileController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @FXML
    Label role_label;

    @FXML
    TextField tfLogin;

    @FXML
    PasswordField pfPassword;

    @FXML
    void initialize() {
        updateInfo();
    }

    @FXML void login() {
        System.out.println(tfLogin.getText());
        System.out.println(pfPassword.getText());

        final String userName = tfLogin.getText().trim();
        final String userPassword = pfPassword.getText().trim();

        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userName, userPassword);

            System.out.println(request);
            System.out.println(request.getCredentials());
            System.out.println(bCryptPasswordEncoder.encode(userPassword));
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

            updateInfo();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        tfLogin.clear();
        pfPassword.clear();
    }

    private void updateInfo() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth);
            if (auth == null) {
                role_label.setText("anonymous");
            } else {
                role_label.setText(auth.getName());
            }

        } catch (AuthenticationException e) {
            System.err.println(e);
        }
    }

    @Autowired
    public profileController() {
    }
}
