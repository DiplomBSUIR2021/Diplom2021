package com.diploma.demo.view;

import com.diploma.demo.view.equipment.FullMapController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@FxmlView("counter-page-tab.fxml")
public class CounterController {

    @FXML
    private ChoiceBox<?> floorChoiceBox;

    @FXML
    private Button notificationsWindow;

    @FXML
    private Button equipmentWindow;

    @FXML
    private Button installedEquipmentWindow;

    @FXML
    private Button fullMapWindowOpenButton;

    @FXML
    private ChoiceBox<?> mapTypeChoiceBox;

    @FXML
    private ImageView minimapImageView;

    @FXML
    public void openFullMapWindow(javafx.event.ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("equipment/full-map-window.fxml"));
            Parent root = loader.load();
            FullMapController sceneController = loader.getController();
            sceneController.setFullMapImageView(floorChoiceBox.getValue().toString(),mapTypeChoiceBox.getValue().toString());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("[Система ТС] Карта здания");
            stage.setScene(scene);
            stage.show();


        } catch (Exception e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void openNotificationsWindow(javafx.event.ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("equipment/notifications-window.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("[Система ТС] Окно отправки уведомлений");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void openInstalledEquipmentWindow(javafx.event.ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("equipment/installed-equipment-window.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("[Система ТС] Таблица установленного оборудования");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void openEquipmentWindow(javafx.event.ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("equipment/equipment-window.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("[Система ТС] Таблица оборудования");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void setMinimapChanges(javafx.event.ActionEvent actionEvent) {
        Image image = new Image(getClass().getResourceAsStream("/img/building/" + floorChoiceBox.getValue().toString() + " " + mapTypeChoiceBox.getValue().toString() + ".png"));
        minimapImageView.setImage(image);
    }
}
