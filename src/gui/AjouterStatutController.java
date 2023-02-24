package gui;

import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import services.StatutService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AjouterStatutController {

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

}
