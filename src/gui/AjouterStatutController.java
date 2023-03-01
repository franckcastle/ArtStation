package gui;

import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import services.StatutService;
import entities.Statut;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AjouterStatutController implements Initializable {

    @FXML
    private TextField contenuField;

    @FXML
    private TextField titreField;

    StatutService ss = new StatutService();



    @FXML
    void ajouter(ActionEvent event) {
        Statut s = new Statut();
        s.setTitre(titreField.getText());
        s.setContenu(contenuField.getText());
        try {
            if (s.getTitre().length()==0 || s.getContenu().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de publier votre statut ! ");
                alert.setHeaderText("Veuillez remplir tous les champs de votre statut !");
                alert.showAndWait();
                return;
            }
            ss.ajouter(s);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    private void reset() {
        titreField.setText("");
        contenuField.setText("");
    }
    @FXML
    void AfficherStatut (ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherStatut.fxml"));
            titreField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
