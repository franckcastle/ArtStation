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
    private Label statutCreated;
    @FXML
    private Button affichebtn;

    @FXML
    private Button likebtn;

    @FXML
    private Button modifierbtn;

StatutService s = new StatutService();
CommentaireService cs = new CommentaireService();

    public void setStatut(Statut statut) {
        this.statut = statut;
        updateUI();
    }



    private void updateUI() {
        if (statut != null) {
            statutTitre.setText(statut.getTitre());
            statutContenu.setText(statut.getContenu());




            // Use getClass().getResource() to get the correct URL for the image

        } else {
            statutTitre.setText("");
            statutContenu.setText("");
        }
    }









    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

