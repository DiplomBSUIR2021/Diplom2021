package com.diploma.demo.view.auth;

import com.diploma.demo.auth.Role;
import com.diploma.demo.view.utils.CrudUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class profileController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @FXML Label role_label;

    @FXML TextField tfLogin;

    @FXML PasswordField pfPassword;

    @FXML Button btnLogin;
    @FXML Button btnLogout;

    @FXML void initialize() {
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
            //handle
            Authentication result = authManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

            updateInfo();

            tfLogin.clear();
            pfPassword.clear();

            CrudUtils.unhideNode(btnLogout);
            CrudUtils.hideNode(btnLogin);
            CrudUtils.hideNode(tfLogin);
            CrudUtils.hideNode(pfPassword);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    @FXML private void logout() {
        try {
            SecurityContextHolder.clearContext();
            updateInfo();
            CrudUtils.unhideNode(btnLogin);
            CrudUtils.unhideNode(tfLogin);
            CrudUtils.unhideNode(pfPassword);
            CrudUtils.hideNode(btnLogout);
        }  catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }


    private void updateInfo() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth);

            if (auth == null) {
                role_label.setText("anonymous");
            } else {
                System.out.println(auth.getName());
                System.out.println(auth.getAuthorities());
                Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
                for (Object role : roles) {
                    Role curRole = (Role) role;
                    System.out.println(curRole.getName());
                }
                role_label.setText(auth.getName());
            }

        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public profileController() {
    }
}
