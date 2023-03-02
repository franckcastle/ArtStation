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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class ModifierStatutController implements Initializable {

    String c,e;
    String titre,contenu;
    StatutService ser = new StatutService();
    public Statut ss;

    public ModifierStatutController () {
    }
    public ModifierStatutController (Statut ss) {
        this.ss=ss;
        //this.titre=titre;
        //this.contenu=contenu;
    }

    public void setTitre (String titre) {
        titreField.setText(titre);
        c = titreField.getText();
    }
    public void setContenu (String contenu) {
        contenuField.setText(contenu);
        e = contenuField.getText();
    }

    @FXML
    private TextField contenuField;

    @FXML
    private TextField titreField;

//    @FXML
//    void modifier(ActionEvent event) {
//       // Statut s= new Statut();
//        String titre = titreField.getText();
//        String contenu = contenuField.getText();
//        try {
//            ser.modifier(titre,contenu,c,e);
//            Parent loader = FXMLLoader.load(getClass().getResource("AfficherStatut.fxml"));
//            titreField.getScene().setRoot(loader);
//            //contenuField.getScene().setRoot(loader);
//
//
//        }catch (IOException ex) {
//            throw new RuntimeException(ex);
//        } catch (SQLException | ParseException ex) {
//            throw new RuntimeException(ex);
//        }
//
//    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
