package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AjController implements Initializable {
    @FXML
    public TextField titreField;
    @FXML
    public TextField descriptionField;
    @FXML
    public TextField localisationField;
    @FXML
    public TextField nbPlaceField;
    @FXML
    public DatePicker dateDebutField;
    @FXML
    public DatePicker dateFinFiel;
    @FXML
    public TextField prixField;
    @FXML
    public Button retourId;
    @FXML
    public TextField imgField;
    EvenementService es = new EvenementService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void ajouter(ActionEvent event){
        Evenement e = new Evenement();

        if(!titreField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !localisationField.getText().isEmpty() && !nbPlaceField.getText().isEmpty()  && !prixField.getText().isEmpty()) {

            e.setTitre(titreField.getText());
            e.setDescription(descriptionField.getText());
            e.setLocalisation(localisationField.getText());
            e.setNbPlace(Integer.parseInt(nbPlaceField.getText()));
            //e.setDateDebut(dateDebutField.getValue().format());
            LocalDate localDate = dateDebutField.getValue();
            Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            e.setDateDebut(newDate);
            LocalDate localDate1 = dateFinFiel.getValue();
            Date newDate1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
            e.setDateFin(newDate1);
            e.setImage(imgField.getText());
            e.setPrix(Float.valueOf(prixField.getText()));
            try {
                es.ajouter(e);
                reset();
            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }





    }
    private void reset() {

        titreField.setText("");
        descriptionField.setText("");
        localisationField.setText("");
        nbPlaceField.setText("");
        //dateDebutField.setAccessibleText("");
        //dateFinFiel.setAccessibleText("");
        prixField.setText("");
    }
    @FXML
    public void retour(MouseEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));

            //Parent root = loader.load();
            titreField.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));

            //Parent root = loader.load();
            titreField.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
