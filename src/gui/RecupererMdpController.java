package gui;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.SendMail;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecupererMdpController implements Initializable {

    @FXML
    private TextField emailTf;
    private final String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    UserService us = new UserService();


    public static String Randompwd()
    {
        //choisissez un caractére au hasard à partir de cette chaîne
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder s = new StringBuilder(9);

        for (int i = 0; i < 9; i++) {
            int index = (int)(str.length() * Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }



    @FXML
    void Annuler(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            emailTf.getScene().setRoot(loader);
        }catch (IOException ex){
            System.out.println("Annuler erreur"+ex);
        }
    }

    @FXML
    void RecupererMdp(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        if (!emailTf.getText().matches(emailRegex) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'inscription");
            alert.setHeaderText("L'e-mail n'est pas valide.");
            alert.showAndWait();
            Parent loader = null;
            try {
                loader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
                emailTf.getScene().setRoot(loader);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            User u = us.GetByMail(emailTf.getText());
            System.out.println(u.getEmail());
            if(u != null){
                String newPwd =Randompwd();
                us.updatePwd(u.getEmail(),newPwd);
                SendMail.sendMail(emailTf.getText(),"Recuperer votre compte","Bonjour "+u.getUsername() +"\nvotre nouveau mot de passe est :"+newPwd);
                try{      Parent loader = null;

                    loader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
                    emailTf.getScene().setRoot(loader);
                }catch (IOException ex){
                    System.out.println(ex);
                }

            }
            else {
                System.out.println("email inexisant");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
