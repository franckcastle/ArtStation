package gui;


import entities.Reservation_Workshop;
import entities.Workshop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.Reservation_WorkshopServices;

import javax.mail.Message;
import javax.mail.Authenticator;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

public class DetailsWsController implements Initializable {

    @FXML
    private Label NomWs1;
    @FXML
    private Label titreWs1;
    @FXML
    private Label dureeWs;

    @FXML
    private Label heuredWs;

    @FXML
    private Label heurefWs;
    @FXML
    private Label prixWs;
    @FXML
    private Label dessId;

    @FXML
    private Label categorieField;

    private Workshop workshop;
    private Reservation_WorkshopServices reservation;

    @FXML
    private ImageView imageeField;




    public void setWorkshop(Workshop workshop) {
        this.workshop= workshop;
        updateUI();
    }


    private void updateUI() {
        if (workshop != null) {
            NomWs1.setText(workshop.getNom_artiste());
            titreWs1.setText(workshop.getTitre());
            heuredWs.setText(workshop.getHeure_debut());
            heurefWs.setText(workshop.getHeure_fin());
            prixWs.setText(String.format("%.0f DT", workshop.getPrix()));
            dureeWs.setText(String.valueOf(workshop.getDuree()));
            dessId.setText(workshop.getDescription());
            categorieField.setText(workshop.getCategorie());




           String img = workshop.getImage();
            String ch = "../img/";
            String imgF = ch + img;

            // Use getClass().getResource() to get the correct URL for the image
            URL imageURL = getClass().getResource(imgF);
            if (imageURL != null) {
                Image imageF = new Image(imageURL.toString());
                imageeField.setImage(imageF);
            } else {
                System.err.println("Resource not found: " + imgF);
            }


        } else {
            NomWs1.setText("");
            titreWs1.setText("");
            heurefWs.setText("");
            heurefWs.setText("");
            dureeWs.setText("");
            prixWs.setText("");
            imageeField.setImage(null);
            categorieField.setText("");

        }
    }

    public void initialize() {


            updateUI();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void assister(ActionEvent event) throws Exception {

        int id_workshop = workshop.getId();

        String categorie = workshop.getCategorie();


        String selectedCategory =  titreWs1.getText();

        Reservation_Workshop reservation = new Reservation_Workshop();
        reservation.setId_workshop(workshop.getId());
        reservation.setId_user(entities.Session.getUserCon().getUserId());
        reservation.setCategorie(selectedCategory);

        Reservation_WorkshopServices reservationService = new Reservation_WorkshopServices();
        int nombreReservations = reservationService.getNombreReservations(id_workshop);


        if (nombreReservations <= workshop.getNbPlaces()) {

            reservationService.ajouterR(reservation);
            System.out.println("reservation ajoutée");
            // Envoyer un e-mail avec les informations de workshop choisi
            String to = "nour.elghali@esprit.tn";
           // String to = entities.Session.getUserCon().getEmail();
            String subject = "Nouveu Workshop choisi : " + workshop.getTitre();
            String message = "Bonjour,\nVous avez choisi l'atelier \"" + workshop.getTitre() + "\" qui aura lieu le " + workshop.getDate() +" Ceci est votre lien meet: https://meet.google.com/omn-hjwa-quz" +".\n\nCordialement,\nL'équipe de artStation";

            sendEmail(to, subject, message);
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Réservation ajoutée");

            // Créer le contenu de la boîte de dialogue
            DialogPane dialogPane = new DialogPane();
            dialogPane.getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
            dialogPane.setContent(new Label("La réservation a été effectuée avec succès ! Un mail vous a été envoyé contenant le lien Meet pour votre Réservation."));

            // Afficher la boîte de dialogue et attendre la réponse
            dialog.setDialogPane(dialogPane);
            dialog.showAndWait();

            System.out.println("Réservation ajoutée");



            System.out.println("Réservation ajoutée");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'insertion");
            alert.setHeaderText("Impossible de réserver: nombre de réservations maximum atteint");
            alert.showAndWait();
            System.out.println("Impossible de réserver: nombre de réservations maximum atteint");
        }





    }

    private void sendEmail(String to, String subject, String message) {
        String from = "nour.elghali@esprit.tn";
        String password = "223JFT15744";

        // Paramètres de connexion au serveur SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Créer une session d'authentification SMTP
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            // Créer un message électronique
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(from));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            email.setSubject(subject);
            email.setText(message);

            // Envoyer le message électronique
            Transport.send(email);

            System.out.println("E-mail envoyé avec succès à " + to);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));

            //Parent root = loader.load();
            titreWs1.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
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
    void workshops(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

}