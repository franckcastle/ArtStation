package gui;

import entities.Session;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;




public class ConnexionController implements Initializable {
    @FXML
    private PasswordField passwordTf;

    @FXML
    private TextField usernameTf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    void Connexion(ActionEvent event) {
        UserService us = new UserService();
        User u = new User();
        String username = usernameTf.getText();
            String password = passwordTf.getText();
            try {
                u = us.GetByUsername(username);

                if(u!=null) {
                    if (password.equals(u.getPassword())) {
                        try {
                            Session.setUserCon(u);
                            Parent loader = FXMLLoader.load(getClass().getResource("Verification.fxml"));
                            usernameTf.getScene().setRoot(loader);

                        } catch (IOException ex) {
                            System.out.println("xx" + ex.getMessage());
                        }
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur de connexion");
                        alert.setHeaderText(" mot de passe invalide ");
                        alert.showAndWait();

                    }
                }else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setHeaderText("Utilisateur Introuvable ");

                    alert.showAndWait();
                }

            }catch (SQLException e){
                System.out.println(e);
            }

    }

    @FXML
    void RecupererMdp(MouseEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("RecupererMdp.fxml"));
            usernameTf.getScene().setRoot(loader);
        }catch (IOException ex){
            System.out.println(ex);
        }

    }

}
