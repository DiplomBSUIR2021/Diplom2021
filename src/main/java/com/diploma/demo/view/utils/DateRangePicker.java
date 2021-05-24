package com.diploma.demo.view.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;

import java.time.LocalDate;

public class DateRangePicker {

    private final Popup popup;
    private final DatePicker startDatePicker;
    private final DatePicker endDatePicker;

    private final Node parentNode;

    public DateRangePicker(Node parentNode) {
        popup = new Popup();

        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();

        this.parentNode = parentNode;

        Label startLabel = new Label("start date");
        Label endLabel = new Label("end date");

        Button closeButton = new Button("Close");
        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = e -> setting();
        closeButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        gridPane.add(startLabel,0,0);
        gridPane.add(startDatePicker, 1,0);

        gridPane.add(endLabel,0,1);
        gridPane.add(endDatePicker, 1,1);

        gridPane.add(closeButton, 3,2);

        vBox.setStyle(" -fx-background-color: white");
        vBox.getChildren().add(gridPane);
        vBox.setPrefWidth(500);
        vBox.setPrefHeight(300);
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        popup.getContent().add(vBox);
    }

    public void setting() {
        if (!popup.isShowing())
            popup.show(parentNode,
                    Screen.getPrimary().getBounds().getWidth() / 2 - 100,
                    Screen.getPrimary().getBounds().getHeight() / 2 - 100);
        else
            popup.hide();
    }

    public LocalDate getStartDate() {
        return startDatePicker.getValue();
    }

    public LocalDate getEndDate() {
        return endDatePicker.getValue();
    }

}
