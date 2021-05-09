package com.diploma.demo.view.utils;

import com.diploma.demo.core.MyCrudService;
import com.diploma.demo.core.landplot.Address;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CrudController {

    protected Long activeRowID = null;

    public final String btnHistoryInactiveText = "Get History";
    public final String btnHistoryActiveText = "Back to all data";

    private TabPane tabPane;
    private TableView tableView;
    private Button btnHistory;

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
        // handle errors
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

    protected void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    protected void setBtnHistory(Button btnHistory) {
        this.btnHistory = btnHistory;
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

    protected void refreshTableView(MyCrudService crudService) {
        // handle errors
        ObservableList plots = FXCollections.observableArrayList(crudService.getAll());
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(plots);
    }

    protected void refreshTableView(ObservableList newData ) {
        // handle errors
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(newData);
    }

    protected void deleteEntity(MyCrudService crudService) {
        if (activeRowID == null) {
            return;
        }
        crudService.delete(activeRowID);
        refreshTableView(crudService);
    }

    protected void getHistory(MyCrudService crudService) {
        if (this.btnHistory.getText().equals(btnHistoryInactiveText)) {
            this.btnHistory.setText(btnHistoryActiveText);
            System.out.println(activeRowID);
            List test =  crudService.getRevisions(activeRowID);
            List<Object> resultOfSearch = new ArrayList<>();
            test.forEach(objAud -> {
                Object[] testobj = (Object[]) objAud;
                System.out.println("Here");
                resultOfSearch.add(testobj[0]);
                System.out.println(testobj[0]);
                System.out.println(testobj[0].getClass());
                System.out.println(testobj[1]);
                System.out.println(testobj[2]);

            });
            refreshTableView(FXCollections.observableArrayList(resultOfSearch));
        } else {
            this.btnHistory.setText(btnHistoryInactiveText);
            refreshTableView(crudService);
        }
    }
}
