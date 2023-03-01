package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import services.UserService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierUserController implements Initializable {
    String username;
    String whe;

    UserService us = new UserService();
    public ModifierUserController() {
    }

    public ModifierUserController(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        usernameTf.setText(username);
        whe = usernameTf.getText();
    }

    @FXML
    private TextField emailTf;

    @FXML
    private TextField passwordTf;

    @FXML
    private TextField roleTf;

    @FXML
    private TextField usernameTf;

    @FXML
    void ModifierUtilisateur(ActionEvent event) {
        String username = usernameTf.getText();
        String password = passwordTf.getText();
        String role = roleTf.getText();
        String email = emailTf.getText();
        try {
            us.modifer(username,password,email,role,whe);
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
            usernameTf.getScene().setRoot(loader);
        }catch (SQLException | IOException ex){
            System.out.println(ex);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
