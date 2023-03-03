package gui;

import entities.Commentaire;
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


    StatutService ser = new StatutService();
    public Statut s;

    public ModifierStatutController () {
    }
    public ModifierStatutController (Statut s) {
   this.s=s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {
        titreField.setText(s.getTitre());
        contenuField.setText(s.getContenu());



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

    @FXML
    public void modifier(ActionEvent event) throws SQLException {
        if (!titreField.getText().isEmpty()) {
            Statut ss = new Statut();
            ss.setId_s(ss.getId_s());
            ss.setTitre(titreField.getText());
            ss.setContenu(contenuField.getText());

            ser.modifier(ss);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Vous ne pouvez pas ajouter un commentaire vide !");

            alert.showAndWait();
        }
    }

    public void setStatut(Statut s) {
        this.s=s;
    }
}





