package com.diploma.demo.view.controllers;

import com.diploma.demo.archive.abstraction.AbstractRevEntity;
import com.diploma.demo.auth.AuthUtils;
import com.diploma.demo.archive.abstraction.service.ArchiveService;
import com.diploma.demo.core.MyCrudService;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class ArchiveContoller<T extends AbstractRevEntity> extends CrudController<T> {

    protected Integer revId;

    private final static String btnEntityHistoryInactiveText = "История документа";
    private final static String btnEntityHistoryActiveText = "Назад";

    private Button btnEntityHistory;

    private TabPane tabPane;
    private Tab tabUpdate;
    private Tab tabView;
    private Button buttonUpdate;

    @Override
    protected void deleteEntity(MyCrudService<T> crudService) {}


    protected void setBtnEntityHistory(Button btnEntityHistory) {
        this.btnEntityHistory = btnEntityHistory;
        this.btnEntityHistory.setText(btnEntityHistoryInactiveText);
    }

    protected void getEntityHistory(ArchiveService<T> archiveService, LocalDate startDate, LocalDate endDate) {
        if (btnEntityHistory.getText().equals(btnEntityHistoryInactiveText)) {
            btnEntityHistory.setText(btnEntityHistoryActiveText);
            List test = archiveService.getEntityHistory(activeRowID, startDate, endDate);
            refreshTableView(FXCollections.observableArrayList(test));
        } else {
            btnEntityHistory.setText(btnEntityHistoryInactiveText);
            refreshTableView(archiveService);
        }
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

    @Override
    protected void selectTabUpdate(T object) {
        if (tabPane == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set tabPane");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tabUpdate == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set tabUpdate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (buttonUpdate == null) {
            try {
                throw new Exception("you can't use selectTabUpdate function before you set button for update");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        feelForm(object);

        tabPane.getTabs().add(tabUpdate);
        selectTab(tabUpdate);
    }


    @Override
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

        if (tabUpdate!= null) {
            tabPane.getTabs().remove(tabUpdate);
        } else {
            try {
                throw new Exception("you should set tabUpdate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void feelForm(T object);

    @Override
    public void setTabCreate(Tab tabCreate) {
        try {
            throw new Exception("No need tabCreate in archive");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setTabUpdate(Tab tabUpdate) {
        this.tabUpdate = tabUpdate;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }

    @Override
    public void setButtonUpdate(Button buttonUpdate) {
        this.buttonUpdate = buttonUpdate;
        this.buttonUpdate.setText("Обновить");
    }

    protected void refreshTableView(ArchiveService<T> archiveService) {
        List items = archiveService.getAll();
        updateTableView(items);
    }


    protected void update(ArchiveService<T> archiveService) {
        if (!AuthUtils.authorizeRole("ROLE_SUPERVISOR")) {
            return;
        }
        if (revId == null) {
            return;
        }
        Optional<T> object = archiveService.findById(revId);
        object.ifPresent(val -> {
            updateObjectFromForm(val);
            archiveService.update(val);

            refreshTableView(archiveService);
        });
        selectTabView();
    }
}