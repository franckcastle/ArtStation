package gui;

import entities.Commentaire;
import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierStatutController implements Initializable {


    public Statut statut;

    StatutService ser = new StatutService();
    public Statut s;
    public Commentaire c;

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


    @FXML
    public void modifier(ActionEvent event) throws SQLException {
        if (!titreField.getText().isEmpty()) {
            s.setId_s(s.getId_s());
            s.setTitre(titreField.getText());
            s.setContenu(contenuField.getText());

            ser.modifier(s);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Vous ne pouvez pas ajouter un statut vide !");

            alert.showAndWait();
        }
    }
    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            titreField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }


    public void setStatut(Statut s) {
        this.s=s;
    }
}





