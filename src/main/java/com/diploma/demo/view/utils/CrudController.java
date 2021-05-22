package com.diploma.demo.view.utils;

import com.diploma.demo.core.MyCrudService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.hibernate.LazyInitializationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class CrudController<T> extends CrudUtils {
    protected Long activeRowID = null;

    private final String btnEntityHistoryInactiveText = "История документа";
    private final String btnEntityHistoryActiveText = "Назад";

    private final String btnFullHistoryInactiveTest = "Вся история";
    private final String btnFullHistoryActiveText = "Назад"; // к актуальной информации

    private TabPane tabPane;
    private Tab tabView;
    private Tab tabCreate;

    private Button buttonCreate;
    private Button buttonUpdate;


    private TableView tableView;
    private Button btnEntityHistory;
    private Button btnFullHistory;

    public void setButtonCreate(Button buttonCreate) {
        this.buttonCreate = buttonCreate;
        this.buttonCreate.setText("Создать");
    }

    public void setButtonUpdate(Button buttonUpdate) {
        this.buttonUpdate = buttonUpdate;
        this.buttonUpdate.setText("Обновить");
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }

    public void setTabCreate(Tab tabCreate) {
        this.tabCreate = tabCreate;
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

    protected void selectTabView() {
        if (tabView == null) {
            try {
                throw new Exception("you can't use selectTabView function before you set tabView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selectTab(tabView);
        cleanForm();

        if (tabCreate != null) {
            tabPane.getTabs().remove(tabCreate);
        } else {
            try {
                throw new Exception("selectTabView: you should set tabCreate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void selectTabCreate() {
        if (tabCreate == null) {
            try {
                throw new Exception("you can't use selectTabCreate function before you set tabCreate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if (buttonCreate == null || buttonUpdate == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set buttons for update and create");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tabCreate.setText("Создание документа");
        cleanForm();

        hideNode(buttonUpdate);
        unhideNode(buttonCreate);

        tabPane.getTabs().add(tabCreate);
        selectTab(tabCreate);
    }

    protected void selectTabUpdate(T object) {
        if (tabCreate == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set tabCreate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (buttonCreate == null || buttonUpdate == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set buttons for update and create");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        feelForm(object);
        tabCreate.setText("Обновление документа");

        unhideNode(buttonUpdate);
        hideNode(buttonCreate);

        tabPane.getTabs().add(tabCreate);
        selectTab(tabCreate);
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

    protected void refreshTableView(ObservableList newData) {
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
            List test = crudService.getRevisions(activeRowID, startDate, endDate);
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
            List<Object> resultOfSearch = getObjectsFromRevisions(revisions);
            refreshTableView(FXCollections.observableArrayList(resultOfSearch));

        } else {
            this.btnFullHistory.setText(btnFullHistoryInactiveTest);
            refreshTableView(crudService);
        }
    }

    protected void getFullHistory(MyCrudService crudService) {
        getFullHistory(crudService, null, null);
    }

    protected abstract void cleanForm();

    protected abstract void feelForm(T object);

    protected abstract void updateObjectFromForm(T object);


    protected void update(MyCrudService crudService, Long id) {
        if (id == null) {
            return;
        }
        Optional<T> object = crudService.findById(id);
        object.ifPresent(val -> {
            updateObjectFromForm(val);
            crudService.update(val);

            refreshTableView(crudService);
        });
    }
}
