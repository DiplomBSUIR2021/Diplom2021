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

    private final String btnEntityHistoryInactiveText = "Get entity history";
    private final String btnEntityHistoryActiveText = "Back to all data";

    private final String btnFullHistoryInactiveTest = "Get full history";
    private final String btnFullHistoryActiveText = "Back to current data";

    private TabPane tabPane;
    private TableView tableView;
    private Button btnEntityHistory;
    private Button btnFullHistory;

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

    protected void setBtnEntityHistory(Button btnEntityHistory) {
        this.btnEntityHistory = btnEntityHistory;
        this.btnEntityHistory.setText(btnEntityHistoryInactiveText);
    }

    public void setBtnFullHistory(Button btnFullHistory) {
        this.btnFullHistory = btnFullHistory;
        this.btnFullHistory.setText(btnFullHistoryInactiveTest);
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

    private List<Object> getObjectsFromRevisions(List revisions) {
        List<Object> result = new ArrayList<>();
        revisions.forEach(audObj -> {
            System.out.println(audObj);
            Object[] audit = (Object[]) audObj;
            System.out.println("HERE");
            System.out.println(audit[0]);
            System.out.println(audit[0].getClass());
            result.add(audit[0]);
        });
        return result;
    }

    protected void getEntityHistory(MyCrudService crudService) {
        if (this.btnEntityHistory.getText().equals(btnEntityHistoryInactiveText)) {
            this.btnEntityHistory.setText(btnEntityHistoryActiveText);
            System.out.println(activeRowID);
            List test =  crudService.getRevisions(activeRowID);
            List<Object> resultOfSearch = getObjectsFromRevisions(test);

            refreshTableView(FXCollections.observableArrayList(resultOfSearch));
        } else {
            this.btnEntityHistory.setText(btnEntityHistoryInactiveText);
            refreshTableView(crudService);
        }
    }

    protected void getFullHistory(MyCrudService crudService) {
        if (this.btnFullHistory.getText().equals(btnFullHistoryInactiveTest)) {
            this.btnFullHistory.setText(btnFullHistoryActiveText);
            List revisions = crudService.getAllRevisions();
            List <Object> resultOfSearch = getObjectsFromRevisions(revisions);
            refreshTableView(FXCollections.observableArrayList(resultOfSearch));

        } else {
            this.btnFullHistory.setText(btnFullHistoryInactiveTest);
            refreshTableView(crudService);
        }
    }
}
