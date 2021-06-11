package com.diploma.demo.view.auth.adminPanel;

import com.diploma.demo.auth.User;
import com.diploma.demo.auth.service.UserService;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.view.utils.CrudController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminPanelController extends CrudController<User> {
    UserService userService;

    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, Long> tcId;
    @FXML private TableColumn<User, String> tclUsers;
    @FXML private TableColumn<User, String> tcRoles;

    @Autowired
    public AdminPanelController(UserService userService) {
        this.userService = userService;
    }

    @FXML void initialize() {
        System.out.println("hello we here");
        setTableView(tableView);
        read();
        initTableView();
    }

    @FXML private void updateTableData() {
        List items = userService.getAll();
        updateTableView(items);
    }

    @FXML private void manageRoles() {
        if (activeRowID == null) {
            return;
        }
        System.out.println();
    }

    @FXML private void deleteUser() {
        if (activeRowID == null) {
            return;
        }
        boolean isUserDelete = userService.deleteUser(activeRowID);
        if (isUserDelete) {
            updateTableData();
        } else {
            System.out.println("User does't delete");
        }
    }

    private void read() {
        System.out.println("read");
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tclUsers.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcRoles.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRolesAsString()));

        List<User> users = userService.getAll();
        tableView.getItems().addAll(users);
    }

    private void initTableView() {
        this.tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    activeRowID = row.getItem().getId();
                }
            });
            return row ;
        });
    }

    @Override
    protected void cleanForm() {
        return;
    }

    @Override
    protected void feelForm(User object) {
        return;
    }

    @Override
    protected void updateObjectFromForm(User object) {
        return;
    }
}
