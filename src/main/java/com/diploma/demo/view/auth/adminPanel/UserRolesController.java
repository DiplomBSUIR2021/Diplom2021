package com.diploma.demo.view.auth.adminPanel;

import com.diploma.demo.auth.Role;
import com.diploma.demo.auth.User;
import com.diploma.demo.auth.service.RoleService;
import com.diploma.demo.auth.service.UserService;
import com.diploma.demo.view.controllers.CrudController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRolesController extends CrudController<User> {
    UserService userService;
    RoleService roleService;
    User currentUser;

    @FXML private AnchorPane anchorPane;

    @FXML private TableView<Role> tableView;
    @FXML private TableColumn<Role, String> tcRoles;

    private Popup popup;

    @Autowired
    public UserRolesController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @FXML
    void initialize() {
        initializeController();
    }

    @Override
    protected void configurateControllerElements() {
        setTableView(tableView);
        System.out.println("hello we here");
        read();
    }

    private void read() {
        tcRoles.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcRoles.setCellFactory(col -> {
            TableCell<Role, String> cell = new TableCell<Role, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // System.out.println("hohoho : " + item);
                    this.setText(item);
                    if (currentUser != null && currentUser.hasRole(item)) {
                        this.setStyle("-fx-background-color: #00FF00;");
                    } else {
                        this.setStyle("");
                    }

                }
            };
            return cell;
        });
        List<Role> roles = roleService.getAll();
        tableView.getItems().addAll(roles);
    }
    
    protected void initTableView() {
        tableView.setRowFactory(tv -> {
            TableRow<Role> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    activeRowID = row.getItem().getId();
                    System.out.println(activeRowID);
                }
            });
            return row ;
        });
    }

    public void configuratePopUp() {
        if (popup == null) {
            popup = new Popup();
            popup.getContent().add(anchorPane);
            anchorPane.setVisible(true);
            anchorPane.setManaged(true);
        }
    }

    public void showPopup(Node ownerNode, double anchorX, double anchorY, User user) {
        if (popup == null) {
            configuratePopUp();
        }
        currentUser = user;

        updateTable();

        popup.show(ownerNode, anchorX, anchorY);
    }

    @FXML private void closePopup() {
        if (popup != null) {
            popup.hide();
        }
    }

    @FXML private void addRole() {
        System.out.println(activeRowID);
        if (activeRowID != null) {
            int tableViewRoleRowNum = activeRowID.intValue() - 1;
            Role newUserRole = tableView.getItems().get(tableViewRoleRowNum);
            currentUser.addRole(newUserRole);
            userService.update(currentUser);
            updateTable();
        }
    }

    @FXML private void deleteRole() {
        System.out.println(activeRowID);
        if (activeRowID != null) {
            int tableViewRoleRowNum = activeRowID.intValue() - 1;
            Role removedUserRole = tableView.getItems().get(tableViewRoleRowNum);
            System.out.println(removedUserRole.getName());
            System.out.println("эту роль удаляем");
            System.out.println(removedUserRole);
            System.out.println(removedUserRole.getId());
            System.out.println(currentUser.removeRole(removedUserRole));
            userService.update(currentUser);
            updateTable();
        }
    }

    private void updateTable() {
        refreshTableView(FXCollections.observableArrayList(roleService.getAll()));
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
