package gui;

import entities.Workshop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.WorkshopServices;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class AjouterWorkshopController implements Initializable {
    @FXML
    public TextField titreField;
    @FXML
    public TextField dureeField;
    @FXML
    public TextField nom_artisteField;

    @FXML
    public TextField heure_debutField;
    @FXML
    public TextField heure_finField;
    @FXML
    public TextField prixField;
    @FXML
    public TextField nbPlacesField;
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField descriptionField;

    WorkshopServices ws = new WorkshopServices();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    private void Afficher(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherWorkshop.fxml"));

            titreField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private void reset() {
        titreField.setText("");
        dureeField.setText("");
        nom_artisteField.setText("");
        //dateField.setText("");
        heure_debutField.setText("");
        heure_finField.setText("");
        prixField.setText("");
        nbPlacesField.setText("");
    }

    @FXML
    void AjouterWorkshop(ActionEvent event) {
        try {
            Workshop w = new Workshop();
            w.setTitre(titreField.getText());
            w.setDescription(descriptionField.getText());
            w.setDuree(Integer.parseInt(dureeField.getText()));
            w.setNom_artiste(nom_artisteField.getText());
            LocalDate localDate = dateField.getValue();
            Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            w.setDate(newDate);

            w.setHeure_debut(heure_debutField.getText());
            w.setHeure_fin(heure_finField.getText());
            w.setPrix(Float.parseFloat(prixField.getText()));
            w.setNbPlaces(Integer.parseInt(nbPlacesField.getText()));
            reset();
            ws.ajouterWs(w);
            System.out.println("Ajout avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}


