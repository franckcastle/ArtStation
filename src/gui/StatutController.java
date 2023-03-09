package gui;

import entities.Statut;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import services.CommentaireService;
import services.StatutService;


import java.net.URL;
import java.util.ResourceBundle;



public class StatutController implements Initializable {

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}




