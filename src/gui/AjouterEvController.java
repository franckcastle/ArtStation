package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.EvenementService;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterEvController implements Initializable {
    @FXML
    public AnchorPane evaluationField;
    @FXML
    public TextField evaluationIField;
    @FXML
    public TextField titreField;
    @FXML
    public TextField descriptionField;
    @FXML
    public TextField localisationField;
    @FXML
    public TextField nbPlaceField;
    @FXML
    private DatePicker dateDebutField;

    @FXML
    private DatePicker dateFinFiel;
    @FXML
    private TextField prixField;

    EvenementService es = new EvenementService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
        @FXML
    public void ajouterEv(ActionEvent event){
            Evenement e = new Evenement();
           e.setEvaluation(Integer.parseInt(evaluationIField.getText()));
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
            e.setPrix(Float.valueOf(prixField.getText()));
            try {
                es.ajouterEv(e);
                reset();
            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }

        }
    private void reset() {
        evaluationIField.setText("");
        titreField.setText("");
        descriptionField.setText("");
        localisationField.setText("");
        nbPlaceField.setText("");
        //dateDebutField.setAccessibleText("");
        //dateFinFiel.setAccessibleText("");
        prixField.setText("");
    }
}
