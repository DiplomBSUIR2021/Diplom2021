package com.diploma.demo.view.utils;

import com.diploma.demo.core.ArchiveService;
import com.diploma.demo.core.MyCrudService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.hibernate.LazyInitializationException;
import org.reflections.vfs.SystemDir;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class ArchiveContoller<T> extends CrudController<T>{

    private TabPane tabPane;
    private Tab tabUpdate;
    private Tab tabView;
    private Button buttonUpdate;

    @Override
    public void setBtnFullHistory(Button btnFullHistory) {
        return;
    }

    @Override
    protected void deleteEntity(MyCrudService crudService) {
        return;
    }

    @Override
    protected void getFullHistory(MyCrudService crudService) {
        return;
    }

    @Override
    protected void getFullHistory(MyCrudService crudService, LocalDate startDate, LocalDate endDate) {
        return;
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


    protected void update(ArchiveService<T> archiveService, Integer revId) {
        if (revId == null) {
            return;
        }

        System.out.println("HERE"  + revId);
        Optional<T> object = archiveService.findById(revId);
        System.out.println("FIND");
        System.out.println(object);
        object.ifPresent(val -> {
            System.out.println("hoho");
            updateObjectFromForm(val);
            archiveService.update(val);

            refreshTableView(archiveService);
        });


    }
}