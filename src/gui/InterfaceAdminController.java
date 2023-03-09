package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class InterfaceAdminController {

    @FXML
    private Button consulterCom;

    @FXML
    private Button consulterStatut;

    @FXML
    private Label titreField;

    @FXML
    void consulterCom(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AffichComAdmin.fxml"));
            titreField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }


    }

    @FXML
    void consulterStatut(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AffchStatutAdmin.fxml"));
            titreField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }

}