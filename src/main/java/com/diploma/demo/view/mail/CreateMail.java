package com.diploma.demo.view.mail;

import com.diploma.demo.utils.JavaMailUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@FxmlView("create-mail.fxml")

public class CreateMail {

    @FXML
    public TextField Adressto;

    @FXML
    public TextField Adresscopy;

    @FXML
    public TextArea Textbody;

    @FXML
    public TextField Texttheme;


    public void PushMessage(MouseEvent mouseEvent) throws Exception {
        String to = Adressto.getText();
        String copy = Adresscopy.getText();
        String body = Textbody.getText();
        String theme = Texttheme.getText();
        JavaMailUtil.sendMail(to, copy, theme, body);
    }

    public void saveAs(MouseEvent mouseEvent) {

        ImageView imgView = new ImageView("UIControls/Save.png");
        imgView.setFitWidth(20);
        imgView.setFitHeight(20);
        javafx.scene.control.Menu file = new javafx.scene.control.Menu("File");
        javafx.scene.control.MenuItem item = new MenuItem("Save", imgView);
        file.getItems().addAll(item);
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        //Adding action on the menu item\
             Stage stage = new Stage();

        item.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Opening a dialog box
                fileChooser.showSaveDialog(stage);
            }
        });

        javafx.scene.control.MenuBar menuBar = new MenuBar(file);
        Group root = new Group(menuBar);

        Scene scene = new Scene(root, 595, 355, Color.BEIGE);
        stage.setTitle("File Chooser Example");
        stage.setScene(scene);
        stage.show();
    }
}

