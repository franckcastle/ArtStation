package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterUserController implements Initializable {
    @FXML
    public TextField usernameTf;
    @FXML
    public TextField passwordTf;
    @FXML
    public TextField emailTf;
    @FXML
    public TextField roleTf;

    UserService us = new UserService();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void reset() {
        usernameTf.setText("");
        passwordTf.setText("");
        emailTf.setText("");
        roleTf.setText("");
    }

    @FXML
    void AjouterUser(ActionEvent event) {
        try {
            User u = new User();
            u.setUsername(usernameTf.getText());
            u.setPassword(passwordTf.getText());
            u.setEmail(emailTf.getText());
            u.setRole(roleTf.getText());
            reset();
            us.ajouter(u);
            System.out.println("Inscription avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    void AfficherUsers (ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
            usernameTf.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("xx"+ex.getMessage());
        }
    }


}
