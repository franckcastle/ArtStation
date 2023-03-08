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
    private TextField telTf;

    @FXML
    void ModifierUtilisateur(ActionEvent event) {
        String username = usernameTf.getText();
        String password = passwordTf.getText();
        String email = emailTf.getText();
        int tel = Integer.parseInt(telTf.getText());
        String role = roleTf.getText();
        try {
            us.modifer(username,password,email,tel,role,whe);
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
            usernameTf.getScene().setRoot(loader);
        }catch (SQLException | IOException ex){
            System.out.println(ex);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}