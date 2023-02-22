package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.EvenementService;

import java.net.URL;
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
            try {
                es.ajouterEv(e);
            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }
        }
}
