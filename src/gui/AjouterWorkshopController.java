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
import org.controlsfx.control.Notifications;
import services.WorkshopServices;
import org.controlsfx.control.Notifications;
import javax.management.Notification;
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

    @FXML
    private TextField categorieField;

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
        descriptionField.setText("");
        dureeField.setText("");
        nom_artisteField.setText("");
        //dateField.setText("");
        heure_debutField.setText("");
        heure_finField.setText("");
        prixField.setText("");
        nbPlacesField.setText("");
        categorieField.setText("");

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
            w.setCategorie(categorieField.getText());
            reset();


            if (w.getTitre().length()==0 || w.getDescription().length()==0 || w.getNom_artiste().length()==0|| w.getHeure_debut().length()==0|| w.getHeure_fin().length()==0 || w.getCategorie().length()==0)

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'insertion");
                alert.setHeaderText("Veuillez remplir tous les champs.");
                alert.showAndWait();

                return;
            }

            if (w.getNbPlaces()>50 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'insertion");
                alert.setHeaderText("Le nombre de participants maximal est 50");
                alert.showAndWait();

                return;
            }

            if (w.getTitre().length()==0 && w.getDescription().length()==0 && w.getNom_artiste().length()==0&& w.getHeure_debut().length()==0 && w.getHeure_fin().length()==0 && w.getCategorie().length()==0)

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'insertion");
                alert.setHeaderText("Veuillez remplir tous les champs.");
                alert.showAndWait();

                return;
            }

            ws.ajouterWs(w);



        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());

            Notifications.create()
                    .title("Nouvelle Réclamation")
                    .text("Une nouvelle Réclamation a été créé")
                    .showInformation();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }





}


