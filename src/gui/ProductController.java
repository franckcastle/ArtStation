package gui;

import entities.Statut;
import entities.Commentaire;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import services.CommentaireService;
import services.StatutService;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class ProductController implements Initializable {

    private Statut statut;
    @FXML
    private VBox statutContainer;
    @FXML
    private Label statutContenu;
    @FXML
    private Label statutTitre;
    @FXML
    private Label nbrLike;

    @FXML
    private Button affichebtn;

    @FXML
    private Button likebtn;

    @FXML
    private Button modifierbtn;

    StatutService s = new StatutService();
    CommentaireService cs = new CommentaireService();
    VBox statutsVBox = new VBox();

    public void setStatut(Statut statut) {
        this.statut = statut;
        updateUI();
    }


    private void updateUI() {
        if (statut != null) {
            statutTitre.setText(statut.getTitre());
            statutContenu.setText("Publié le : "+ statut.getCreated());
            nbrLike.setText(statut.getNbrLike()+ "  Personnes aiment ça");


            statutTitre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            // Use getClass().getResource() to get the correct URL for the image

        } else {
            statutTitre.setText("");
            statutContenu.setText("");
            nbrLike.setText("");
        }
    }


//    private HBox createStatutHBox(Statut statut) {
//        HBox hbox = new HBox();
//        hbox.setSpacing(10);
//
//        Label titreLabel = new Label(statut.getTitre());
//        titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
//        Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");
//
//        Button afficherBtn = new Button("Afficher");
//        afficherBtn.setOnAction(event -> {
//            try {
//                Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
//               // statutsVBox.getScene().setRoot(loader);
//
//            } catch (IOException ex) {
//                System.out.println("Erreur" + ex.getMessage());
//            }
//
//        });
//        return hbox;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}




