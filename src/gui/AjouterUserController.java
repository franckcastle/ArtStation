package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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
    public PasswordField passwordTf;
    @FXML
    public TextField emailTf;
    @FXML
    public TextField roleTf;
    @FXML
    private TextField telTf;
    @FXML
    private ChoiceBox<String> roleCb;

    private final String[] roles = {"Artiste","Client"};
    UserService us = new UserService();

    private final String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleCb.getItems().addAll(roles);
    }

    private void reset() {
        usernameTf.setText("");
        passwordTf.setText("");
        emailTf.setText("");
        roleCb.getItems().addAll(roles);
    }

    @FXML
    void AjouterUser(ActionEvent event) {

        try {
            User u = new User();
            u.setUsername(usernameTf.getText());
            u.setPassword( passwordTf.getText());
            u.setEmail(emailTf.getText());
            u.setTel(Integer.parseInt(telTf.getText()));
            u.setRole(roleCb.getValue());
            reset();
            if (u.getUsername().length()==0 || u.getEmail().length()==0||u.getRole().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'inscription");
                alert.setHeaderText("Veuillez remplir tt les champs.");
                alert.showAndWait();
                Parent loader = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
                usernameTf.getScene().setRoot(loader);
                return;
            }
            if (u.getPassword().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'inscription");
                alert.setHeaderText("Le mot de passe doit comporter au moins 8 caractères.");
                alert.showAndWait();
                Parent loader = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
                usernameTf.getScene().setRoot(loader);
                return; // stop the method execution if the password is too short
            }
            if (!u.getEmail().matches(emailRegex)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'inscription");
                alert.setHeaderText("L'e-mail n'est pas valide.");
                alert.showAndWait();
                Parent loader = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
                usernameTf.getScene().setRoot(loader);
                return; // stop the method execution if the email is invalid
            }
            
            us.ajouter(u);
            System.out.println("Inscription avec succes");

            Parent loader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            usernameTf.getScene().setRoot(loader);

        } catch (SQLException ex ) {
            System.out.println("error" + ex.getMessage());
        }catch (IOException e ) {
            System.out.println(e);
        }
    }



}
