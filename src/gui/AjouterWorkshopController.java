package gui;

import entities.Session;
import entities.Workshop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.WorkshopServices;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
    @FXML
    private TextField imageField;


    WorkshopServices ws = new WorkshopServices();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    @FXML
    private void Afficher(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichage.fxml"));

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

            if (!dureeField.getText().isEmpty()) {
                w.setDuree(Integer.parseInt(dureeField.getText()));
            }

            w.setNom_artiste(nom_artisteField.getText());


            if (dateField.getValue() != null) {
                LocalDate localDate = dateField.getValue();
                Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                w.setDate(newDate);
            }
            /*LocalDate localDate = dateField.getValue();
            Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            w.setDate(newDate);*/


            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date heureDebut = timeFormat.parse(heure_debutField.getText());
            w.setHeure_debut(String.valueOf(new Time(heureDebut.getTime())));

            Date heureFin = timeFormat.parse(heure_finField.getText());
            w.setHeure_fin(String.valueOf(new Time(heureFin.getTime())));


           // w.setHeure_debut(heure_debutField.getText());
           // w.setHeure_fin(heure_finField.getText());


            if (!prixField.getText().isEmpty()) {
                w.setPrix(Float.parseFloat(prixField.getText()));
            }

            if (!nbPlacesField.getText().isEmpty()) {
                w.setNbPlaces(Integer.parseInt(nbPlacesField.getText()));
            }

            w.setCategorie(categorieField.getText());
            w.setImage(imageField.getText());



            reset();

                if (w.getTitre().isEmpty() || w.getDescription().isEmpty() || w.getNom_artiste().isEmpty()
                        || w.getHeure_debut().isEmpty() || w.getHeure_fin().isEmpty() || w.getCategorie().isEmpty()
                        || (w.getDate() == null || dateField.getValue() == null)) {


                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur d'insertion");
                    alert.setHeaderText("Veuillez remplir tous les champs.");
                    alert.showAndWait();

                    return;
                }

                if (w.getNbPlaces() > 50) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur d'insertion");
                    alert.setHeaderText("Le nombre de participants maximal est 50");
                    alert.showAndWait();

                    return;
                }


                ws.ajouterWs(w);


            } catch(SQLException ex){
                System.out.println("error" + ex.getMessage());

                Notifications.create()
                        .title("Nouvelle Réclamation")
                        .text("Une nouvelle Réclamation a été créé")
                        .showInformation();

            } catch(Exception e){
                throw new RuntimeException(e);
            }

        }
    @FXML
    void events(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("UsEvent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void forum(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomeApp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void orders(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("PanierT.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void products(MouseEvent event) throws IOException {
        if(Session.getUserCon().getRole().equals("Client")){


        AnchorPane root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();}
        else {
            AnchorPane root = FXMLLoader.load(getClass().getResource("InterfaceArt.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(500);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();
        }
    }

    @FXML
    void workshops(MouseEvent event)  throws IOException {
        if (Session.getUserCon().getRole().equals("Admin")) {
            AnchorPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(500);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();
        }
    else {
            AnchorPane root = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(500);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();
    }

    }}

