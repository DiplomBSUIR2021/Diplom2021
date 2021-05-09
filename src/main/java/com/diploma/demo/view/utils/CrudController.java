package com.diploma.demo.view.utils;

import com.diploma.demo.core.landplot.Address;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.function.Consumer;

public class CrudController {

    protected Long activeRowID = null;

    public final String btnHistoryInactiveText = "Get History";
    public final String btnHistoryActiveText = "Back to all data";

    private TabPane tabPane;

    private String getStringValue(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return str;
    }

    protected Long getIdFromTextField(TextField tfID) {
        String idText = getStringValue(tfID.getText());
        if (idText.equals("")) {
            return null;
        }
        return Long.parseLong(idText);
    }

    protected void setTextFieldValue(TextField textField, String value) {
        textField.setText(getStringValue(value));
    }

    protected void setTextFieldValue(TextField textField, Double value) {
        if (value == null || value.isNaN()) {
            textField.setText("");
        } else {
            textField.setText(value.toString());
        }
    }

    protected void setDatePicker(DatePicker datePicker,LocalDate date) {
            datePicker.setValue(date);
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

    protected void setDateFromDatePicker(Consumer<LocalDate> action, DatePicker datePicker) {
        if (datePicker != null) {
            action.accept(datePicker.getValue());
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

    protected String getFullAddress(Address address) {
        if (address == null) {
            System.out.println(" null address");
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

        return "Region: " + region + " .City: " + city + " .Street: " + street + " .Home number: " +
                homeNumber + " .Apartment: " + apartment;
    }
}
