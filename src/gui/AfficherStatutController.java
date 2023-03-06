package gui;

import entities.Commentaire;
import entities.Statut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.CommentaireService;
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class AfficherStatutController implements Initializable {
    @FXML
    private VBox statutsVBox;
    @FXML
    private Button ajouterStatutBtn;

    private StatutService s = new StatutService();

    private boolean isLiked;
    CommentaireService cs = new CommentaireService();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Statut> stt = s.recuperer();


            for (Statut statut : stt) {
                statutsVBox.getChildren().add(createStatutVBox(statut));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
    }

    private VBox createStatutVBox(Statut statut) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);


        Label titreLabel = new Label(statut.getTitre());
        titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Text contenuText = new Text(statut.getContenu());
        contenuText.setWrappingWidth(400);

        Label createdLabel = new Label("Publié le " + statut.getCreated());
        //Label updatedLabel = new Label("Modifié le " + statut.getUpdated());
        Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");
        Label comLabel = new Label("Commentaires: ");

        List<Commentaire> commentairesList = null;
        try {
            commentairesList = cs.recupererComByIdStatut(statut.getId_s());
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }
        for (Commentaire commentaire : commentairesList) {
            Text commentaireText = new Text(commentaire.getDescription());
            vbox.getChildren().add(commentaireText);
        }

        Button commenterBtn = new Button("Commenter");
        commenterBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCom.fxml"));
                Parent root = loader.load();

                AjouterComController controller = loader.getController();
                controller.sta = statut;

                statutsVBox.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Button modifierBtn = new Button("modifier");
        modifierBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierStatut.fxml"));
                Parent root = loader.load();

                ModifierStatutController controller = loader.getController();
                controller.s = statut;

                statutsVBox.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button likeBtn = new Button("Like");
        likeBtn.setOnAction(event -> {
            try {
                if (!isLiked) {
                    isLiked = true;
                    s.ajouterLikeStatut(statut);
                    nbrLikeLabel.setText((statut.getNbrLike() + 1) + " personnes aiment ça");
                } else {
                    isLiked = false;
                    s.supprimerLikeStatut(statut);
                    nbrLikeLabel.setText((statut.getNbrLike()) + " personnes aiment ça");
                }
            } catch (SQLException ex) {
                System.out.println("Erreur" + ex.getMessage());
            }
        });


        Button supprimerBtn = new Button("Supprimer");
        supprimerBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce statut?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    s.supprimer(statut.getId_s());
                    statutsVBox.getChildren().remove(vbox);
                } catch (SQLException ex) {
                    System.out.println("Erreur" + ex.getMessage());
                }
            }
        });

        vbox.getChildren().addAll(titreLabel, contenuText, createdLabel, nbrLikeLabel,comLabel,commenterBtn, likeBtn,modifierBtn,supprimerBtn);



        return vbox;
    }

    @FXML
    public void ajouterStatut(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterStatut.fxml"));
            Parent root = loader.load();

            statutsVBox.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
