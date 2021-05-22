package com.diploma.demo.view.utils;

import com.diploma.demo.core.landplot.Address;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.function.Consumer;

public class CrudUtils {

    public CrudUtils(){}

    public static String getStringValue(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str;
    }

    public static Long getIdFromTextField(TextField tfID) {
        String idText = getStringValue(tfID.getText());
        if (idText.equals("")) {
            return null;
        }
        return Long.parseLong(idText);
    }

    public static void setTextFieldValue(TextField textField, String value) {
        textField.setText(getStringValue(value));
    }

    public static void setTextFieldValue(TextField textField, Double value) {
        if (value == null || value.isNaN()) {
            textField.setText("");
        } else {
            textField.setText(value.toString());
        }
    }

    public static void hideNode(Node node) {
        node.setVisible(false);
        // node.setManaged(false);
    }

    public static void unhideNode(Node node) {
        node.setVisible(true);
        //node.setManaged(true);
    }

    public static void setComboBoxValue(ComboBox<String> comboBox, String value) {
        if (value != null) {
            comboBox.setValue(value);
        }
    }

    public static boolean isNotEmptyField(TextField field) {
        return !field.getText().isEmpty() && !field.getText().equals("");
    }

    public static void setStringValFromTextField(Consumer<String> action, TextField textField) {
        if (isNotEmptyField(textField)) {
            action.accept(textField.getText());
            textField.setText("");
        }
    }

    public static void setDoubleValFromTextField(Consumer<Double> action, TextField textField) {
        if (isNotEmptyField(textField)) {
            action.accept(Double.parseDouble(textField.getText()));
            textField.setText("");
        }
    }

    public static void setStringValFromComboBox(Consumer<String> action, ComboBox<String> comboBox) {
        // errors
        if (comboBox.getValue() != null) {
            action.accept(comboBox.getValue());
        }
    }

    public static void setDatePicker(DatePicker datePicker, LocalDate date) {
        datePicker.setValue(date);
    }

    public static void setLongValFromTextField(Consumer<Long> action, TextField textField) {
        // handle errors
        if (isNotEmptyField(textField)) {
            action.accept(Long.parseLong(textField.getText()));
            textField.setText("");
        }
    }

    public static void setDateFromDatePicker(Consumer<LocalDate> action, DatePicker datePicker) {
        if (datePicker != null) {
            action.accept(datePicker.getValue());
        }
    }

    public static void eraseDate(Consumer<LocalDate> action) {
        action.accept(null);
    }

    public static void setTextFieldOnlyDigitsInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public static String getFullAddress(Address address) {
        if (address == null) {
            return "";
        }

        String region = getStringValue(address.getRegion());
        String city = getStringValue(address.getCity());
        String street = getStringValue(address.getStreet());
        String homeNumber = getStringValue(address.getHomeNumber());

        Long apartmentL = address.getApartmentn();
        String apartment;
        if (apartmentL == null) {
            apartment = "";
        } else {
            apartment = address.getApartmentn().toString();
        }

        return "Region: " + region + "\nCity: " + city + "\nStreet: " + street + "\nHome number: " +
                homeNumber + "\nApartment: " + apartment;
    }
}
