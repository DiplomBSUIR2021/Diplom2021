package com.diploma.demo.view.controllers;

import com.diploma.demo.core.MyCrudService;
import com.diploma.demo.view.utils.CrudUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.hibernate.LazyInitializationException;

import java.util.List;
import java.util.Optional;


public abstract class CrudController<T> extends CrudUtils {
    protected Long activeRowID = null;

    private TabPane tabPane;
    private Tab tabView;
    private Tab tabCreate;

    private Button buttonCreate;
    private Button buttonUpdate;

    private TableView tableView;

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

    protected void updateTableView(List<T> items) {
        ObservableList<T> plots = FXCollections.observableArrayList(items);
        this.tableView.getItems().clear();
        try {
            this.tableView.getItems().addAll(plots);
        } catch (LazyInitializationException exception) {
            exception.printStackTrace();
        }
    }

    protected void refreshTableView(MyCrudService<T> crudService) {
        // handle errors
        List items = crudService.getAll();
        updateTableView(items);
    }

    protected void refreshTableView(ObservableList newData) {
        // handle errors
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(newData);
    }

    protected void deleteEntity(MyCrudService<T> crudService) {
        if (activeRowID == null) {
            return;
        }
        crudService.delete(activeRowID);
        refreshTableView(crudService);
    }

    protected abstract void cleanForm();

    protected abstract void feelForm(T object);

    protected abstract void updateObjectFromForm(T object);


    protected void update(MyCrudService<T> crudService, Long id) {
        if (id == null) {
            return;
        }
        Optional<T> object = crudService.findById(id);
        object.ifPresent(val -> {
            updateObjectFromForm(val);
            crudService.update(val);

            refreshTableView(crudService);
        });
        selectTabView();
    }
    protected void initializeController() {
        configurateControllerElements();
        initTableView();
    }

    protected abstract void configurateControllerElements();
    protected abstract void initTableView();
}
