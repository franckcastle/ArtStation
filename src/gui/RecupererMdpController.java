package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecupererMdpController implements Initializable {

    @FXML
    private TextField emailTf;

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
    void RecupererMdp(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
