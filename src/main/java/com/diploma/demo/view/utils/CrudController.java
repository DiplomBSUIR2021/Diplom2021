package com.diploma.demo.view.utils;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.util.function.Consumer;

public class CrudController {

    protected Long activeRowID = null;

    public final String btnHistoryInactiveText = "Get History";
    public final String btnHistoryActiveText = "Back to all data";

    private TabPane tabPane;

    protected void setTextFieldValue(TextField textField, String value) {
        if (value == null || value.isEmpty()) {
            textField.setText("");
        } else {
            textField.setText(value);
        }
    }

    protected void setTextFieldValue(TextField textField, Double value) {
        if (value == null || value.isNaN()) {
            textField.setText("");
        } else {
            textField.setText(value.toString());
        }
    }

    protected boolean isNotEmptyField(TextField field) {
        return !field.getText().isEmpty() && !field.getText().equals("");
    }

    protected void setLongValFromTextField(Consumer<Long> action, TextField textField) {
        if (isNotEmptyField(textField)) {
            action.accept(Long.parseLong(textField.getText()));
            textField.setText("");
        }
    }

    protected void setStringValFromTextField(Consumer<String> action, TextField textField) {
        if (isNotEmptyField(textField)) {
            action.accept(textField.getText());
            textField.setText("");
        }
    }

    protected void setDoubleValFromTextField(Consumer<Double> action, TextField textField) {
        if (isNotEmptyField(textField)) {
            action.accept(Double.parseDouble(textField.getText()));
            textField.setText("");
        }
    }

    protected void setTabPane(TabPane newTabPane) {
        this.tabPane = newTabPane;
    }

    protected void selectTab(Tab tab) {
        if (tabPane == null) {
            try {
                throw new Exception("you can't use selectTabPane function before you set tab pane");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("we here");
            tabPane.getSelectionModel().select(tab);
        }
    }
}
