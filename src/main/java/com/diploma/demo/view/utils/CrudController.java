package com.diploma.demo.view.utils;

import com.diploma.demo.core.MyCrudService;
import com.diploma.demo.core.landplot.Address;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.hibernate.LazyInitializationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class CrudController<T> {

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

    protected void setComboBoxValue(ComboBox<String> comboBox, String value) {
        if (value != null) {
            comboBox.setValue(value);
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

    protected void eraseDate(Consumer<LocalDate> action) {
        action.accept(null);
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

    protected void setStringValFromComboBox(Consumer<String> action, ComboBox<String> comboBox) {
        // errors
        if (comboBox.getValue() != null) {
            action.accept(comboBox.getValue());
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
            tabPane.getSelectionModel().select(tab);
        }
    }

    protected String getFullAddress(Address address) {
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

    protected void refreshTableView(MyCrudService crudService) {
        // handle errors
        List items = crudService.getAll();
        ObservableList plots = FXCollections.observableArrayList(items);
        this.tableView.getItems().clear();
        try {
            this.tableView.getItems().addAll(plots);
        } catch (LazyInitializationException exception) {

        }

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
            Object[] audit = (Object[]) audObj;
            result.add(audit[0]);
        });
        return result;
    }

    protected void getEntityHistory(MyCrudService crudService, LocalDate startDate, LocalDate endDate) {
        if (this.btnEntityHistory.getText().equals(btnEntityHistoryInactiveText)) {
            this.btnEntityHistory.setText(btnEntityHistoryActiveText);
            List test =  crudService.getRevisions(activeRowID, startDate, endDate);
            List<Object> resultOfSearch = getObjectsFromRevisions(test);

            refreshTableView(FXCollections.observableArrayList(resultOfSearch));
        } else {
            this.btnEntityHistory.setText(btnEntityHistoryInactiveText);
            refreshTableView(crudService);
        }
    }

    protected void getEntityHistory(MyCrudService crudService) {
        getEntityHistory(crudService, null, null);
    }

    protected void getFullHistory(MyCrudService crudService, LocalDate startDate, LocalDate endDate) {
        if (this.btnFullHistory.getText().equals(btnFullHistoryInactiveTest)) {
            this.btnFullHistory.setText(btnFullHistoryActiveText);
            List revisions = crudService.getAllRevisions(startDate, endDate);
            List <Object> resultOfSearch = getObjectsFromRevisions(revisions);
            refreshTableView(FXCollections.observableArrayList(resultOfSearch));

        } else {
            this.btnFullHistory.setText(btnFullHistoryInactiveTest);
            refreshTableView(crudService);
        }
    }

    protected void getFullHistory(MyCrudService crudService) {
        getFullHistory(crudService, null, null);
    }

    protected void setTextFieldOnlyDigitsInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    protected abstract  void updateObjectFromTextField(T object);

    protected void update(MyCrudService crudService, Long id) {
        if (id == null) {
            return;
        }
        Optional<T> object = crudService.findById(id);
        object.ifPresent(val -> {
            updateObjectFromTextField(val);
            crudService.update(val);

            refreshTableView(crudService);
        });
    }
}
