package com.diploma.demo.view.utils;

import com.diploma.demo.core.MyCrudService;

public abstract class CoreCrudController<T> extends CrudController<T>{
    protected void create(MyCrudService<T> crudService) {
        T entity = createEntity();

        if (entity == null) {
            try {
                selectTabView();
                throw new Exception("Entity is null. You should overrid createEntity method");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            updateObjectFromForm(entity);
            crudService.add(entity);

            refreshTableView(crudService);
            selectTabView();
        }
    }

    protected T createEntity() {
        return null;
    };
}
