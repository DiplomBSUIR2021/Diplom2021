package com.diploma.demo.view.equipment;


import com.diploma.demo.utils.JavaMailUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@FxmlView("notifications-window.fxml")
public class Notifications {

    @FXML
    private GridPane notificationsPane;
    @FXML
    private Button returnButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea messageTextField;
    @FXML
    private ChoiceBox recepientChoiceBox;

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        try {
                //JavaMailUtil.sendMail(recepientChoiceBox.getValue().toString(),"Уведомление Системы ТС",messageTextField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Уведомление");
                alert.setHeaderText(null);
                alert.setContentText("Сообщение отправлено.");
                alert.showAndWait();
                } catch (Exception e1) {
                e1.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Уведомление");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка при отправлении.");
                }
    }
}
