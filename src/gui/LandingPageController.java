package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPageController {

    @FXML
    private Button cnxBtn;

    @FXML
    private Button inscriBtn;

    @FXML
    void ConnexionRed(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(600);
            stage.setHeight(400);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();

        }catch (IOException ex){
            System.out.println("xx"+ex.getMessage());
        }
    }

    @FXML
    void InscriptionRed(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("AjouterUser.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(830);
            stage.setHeight(550);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();


        }catch (IOException ex){
            System.out.println("landing-inscri"+ex.getMessage());
        }
    }

}
