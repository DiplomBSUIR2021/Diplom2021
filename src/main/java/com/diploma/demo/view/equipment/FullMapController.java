package com.diploma.demo.view.equipment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("full-map-window.fxml")
public class FullMapController {

    @FXML
    private GridPane fullMapWindowPane;

    @FXML
    private Button returnButton;

    @FXML
    private ImageView fullMapImageView;

    public void setFullMapImageView(String floor,String type) {
        Image image = new Image(getClass().getResourceAsStream("/img/building/" + floor + " " + type + ".png"));
        fullMapImageView.setImage(image);
    }


    public void openMainWindow(ActionEvent actionEvent) {

    }
}
