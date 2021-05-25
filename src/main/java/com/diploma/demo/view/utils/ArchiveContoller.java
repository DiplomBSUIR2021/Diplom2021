package com.diploma.demo.view.utils;

import com.diploma.demo.core.MyCrudService;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.time.LocalDate;
import java.util.Optional;

public abstract class ArchiveContoller<T> extends CrudController<T>{

    private TabPane tabPane;
    private Tab tabUpdate;
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

    protected abstract void feelForm(T object);

    @Override
    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setTabUpdate(Tab tabUpdate) {
        this.tabUpdate = tabUpdate;
    }

    @Override
    public void setButtonUpdate(Button buttonUpdate) {
        this.buttonUpdate = buttonUpdate;
        this.buttonUpdate.setText("Обновить");
    }


    protected void update(int rev) {


/*        if (id == null) {
            return;
        }
        Optional<T> object = crudService.findById(id);
        object.ifPresent(val -> {
            updateObjectFromForm(val);
            crudService.update(val);

            refreshTableView(crudService);
        });*/
    }
}
