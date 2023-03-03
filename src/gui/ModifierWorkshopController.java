package gui;

import entities.Workshop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.WorkshopServices;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ModifierWorkshopController implements Initializable {
    @FXML
    private TextField titreId;
    @FXML
    private TextField descriptionId;
    @FXML
    private TextField dureeId;
    @FXML
    private TextField nom_artisteId;
    @FXML
    private TextField heure_debutId;
    @FXML
    private TextField heure_finId;
    @FXML
    private TextField prixId;
    @FXML
    private DatePicker dateId;

    @FXML
    private TextField nbPlacesId;

    @FXML
    private TextField categorieId;
    WorkshopServices ws = new WorkshopServices();


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public Workshop w;





    public ModifierWorkshopController() {
    }

    public ModifierWorkshopController(Workshop w) {
        this.w = w;
    }



    @FXML
    public void initialize() {
        titreId.setText(w.getTitre());
        descriptionId.setText(w.getDescription());
        nom_artisteId.setText(w.getNom_artiste());
        categorieId.setText(w.getCategorie());
        heure_debutId.setText(w.getHeure_debut());
        heure_finId.setText(w.getHeure_fin());
        prixId.setText(Float.toString(w.getPrix()));
        dureeId.setText(Integer.toString(w.getDuree()));
        nbPlacesId.setText(Integer.toString(w.getNbPlaces()));


    }

    public void modifier(ActionEvent event) {
        if (!titreId.getText().isEmpty() ) {
            Workshop ww= new Workshop();
            ww.setId(ww.getId());
            ww.setTitre(titreId.getText());
            ww.setDescription(descriptionId.getText());
            ww.setDuree(Integer.parseInt(dureeId.getText()));
            ww.setNom_artiste(nom_artisteId.getText());
            ww.setHeure_debut(heure_debutId.getText());
            ww.setHeure_fin(heure_finId.getText());
            ww.setPrix(Integer.parseInt(prixId.getText()));
            ww.setNbPlaces(Integer.parseInt(nbPlacesId.getText()));
            ww.setCategorie(categorieId.getText());
            ws.modifierWs(ww);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }

    }



    /*public void modifier(ActionEvent event) {
        System.out.println(w);


        if (!titreId.getText().isEmpty() && !descriptionId.getText().isEmpty()
                && !dureeId.getText().isEmpty() && !nom_artisteId.getText().isEmpty()
                && !heure_debutId.getText().isEmpty() && !heure_finId.getText().isEmpty()
                && !prixId.getText().isEmpty() && !nbPlacesId.getText().isEmpty()
                && !categorieId.getText().isEmpty()) {
            Workshop e = new Workshop();
            e.setId(w.getId());
            e.setTitre(titreId.getText());
            e.setDescription(descriptionId.getText());
            e.setDuree(Integer.parseInt(dureeId.getText()));
            e.setNom_artiste(nom_artisteId.getText());
            e.setHeure_debut(heure_debutId.getText());
            e.setHeure_fin(heure_finId.getText());
            e.setPrix(Integer.parseInt(prixId.getText()));
            e.setNbPlaces(Integer.parseInt(nbPlacesId.getText()));
            e.setCategorie(categorieId.getText());

            LocalDate localDate = dateId.getValue();
            Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            w.setDate(newDate);


            ws.modifierWs(e);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }


        //e.setTitre(titreId.getText());
        //es.modifierEvent(e);
        //cmnt
    }*/
    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherWorkshop.fxml"));

            //Parent root = loader.load();
            titreId.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWorkshop(Workshop w) {
        this.w=w;
    }
}
