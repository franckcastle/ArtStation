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
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public Workshop w;

    public ModifierWorkshopController() {
    }

    public ModifierWorkshopController(Workshop w) {
        this.w = w;
    }



    public void modifier(ActionEvent event){
        System.out.println(w);



        if(!titreId.getText().isEmpty() && !descriptionId.getText().isEmpty()
                && !dureeId.getText().isEmpty() && !nom_artisteId.getText().isEmpty()
                && !heure_debutId.getText().isEmpty() && !heure_finId.getText().isEmpty()
                && !prixId.getText().isEmpty() && !nbPlacesId.getText().isEmpty()
                && !categorieId.getText().isEmpty()){
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
    }
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
}
