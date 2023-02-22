package gui;

import entities.User;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherUsersController implements Initializable {
    @FXML
    public TableView<User> usersTv;
    @FXML
    public TableColumn<User,Integer> UserIdTv;
    @FXML
    public TableColumn<User,String> usernameTv;
    @FXML
    public TableColumn<User,String> passwordTv;
    @FXML
    public TableColumn<User,String> emailTv;
    @FXML
    public TableColumn<User,String> role;

    UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<User> users = us.recuperer();
            ObservableList<User> olu = FXCollections.observableArrayList(users);
            usersTv.setItems(olu);
            UserIdTv.setCellValueFactory(new PropertyValueFactory("userId"));
            usernameTv.setCellValueFactory(new PropertyValueFactory("username"));
            passwordTv.setCellValueFactory(new PropertyValueFactory("email"));
            emailTv.setCellValueFactory(new PropertyValueFactory("password"));
            role.setCellValueFactory(new PropertyValueFactory("role"));

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
}
