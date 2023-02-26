package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class LandingPageController {

    @FXML
    private Button cnxBtn;

    @FXML
    private Button inscriBtn;

    @FXML
    void ConnexionRed(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            cnxBtn.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("xx"+ex.getMessage());
        }
    }

    @FXML
    void InscriptionRed(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterUser.fxml"));
            inscriBtn.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("landing-inscri"+ex.getMessage());
        }
    }

}
