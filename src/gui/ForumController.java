package gui;

import entities.Commentaire;
import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import services.CommentaireService;
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ForumController implements Initializable {


    @FXML
    private TextField textfield;

    @FXML
    private VBox statutsVBox;
    @FXML
    private Button ajouterStatutBtn;

    private StatutService s = new StatutService();

    @FXML
    private Button ajouter;

    @FXML
    void ajouter(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterStatut.fxml"));
            //textfield.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }



    private boolean isLiked;
    CommentaireService cs = new CommentaireService();
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                List<Statut> stt = s.recuperer();

                for (Statut statut : stt) {
                    HBox hbox = createStatutHBox(statut);
                    statutsVBox.getChildren().add(hbox);

                }
            } catch (SQLException ex) {
                System.out.println("Erreur" + ex.getMessage());
            }
        }

        private HBox createStatutHBox(Statut statut) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            Label titreLabel = new Label(statut.getTitre());
            titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            Label createdLabel = new Label("Publié le " + statut.getCreated());
            Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");

            Button afficherBtn = new Button("Afficher");
            afficherBtn.setOnAction(event -> {
//    VBox vbox = createStatutVBox(statut);
//                Scene scene = new Scene(vbox);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
                try {
                    Parent loader = FXMLLoader.load(getClass().getResource("AfficherStatut.fxml"));
                    statutsVBox.getScene().setRoot(loader);

                }catch (IOException ex){
                    System.out.println("Erreur"+ex.getMessage());
                }

            });

            hbox.getChildren().addAll(titreLabel, createdLabel, nbrLikeLabel, afficherBtn);
            return hbox;
        }

        private VBox createStatutVBox(Statut statut) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);

            Label titreLabel = new Label(statut.getTitre());
            titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            javafx.scene.text.Text  contenuText = new javafx.scene.text.Text(statut.getContenu());
            contenuText.setWrappingWidth(400);
            Label createdLabel = new Label("Publié le " + statut.getCreated());
            Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");

            List<Commentaire> commentairesList = null;
            try {
                commentairesList = cs.recupererComByIdStatut(statut.getId_s());
            } catch (SQLException e) {
                System.out.println("Erreur" + e.getMessage());
            }
            for (Commentaire commentaire : commentairesList) {
                javafx.scene.text.Text commentaireText = new javafx.scene.text.Text(commentaire.getDescription());
                vbox.getChildren().add(commentaireText);
            }

            Button commenterBtn = new Button("Commenter");
            commenterBtn.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCom.fxml"));
                    Parent root = loader.load();

                    AjouterComController controller = loader.getController();
                    controller.sta = statut;

                    vbox.getScene().setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Button modifierBtn = new Button("Modifier");
            modifierBtn.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierStatut.fxml"));
                    Parent root = loader.load();

                    ModifierStatutController controller = loader.getController();
                    controller.s = statut;

                    vbox.getScene().setRoot(root);

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


                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
           });
            return vbox;
        }
            /*VBox vbox = new VBox();
            vbox.getStyleClass().add("statut-vbox");
            vbox.setSpacing(10);

            Label titreLabel = new Label(statut.getTitre());
            titreLabel.getStyleClass().add("statut-titre");

            javafx.scene.text.Text contenuText = new javafx.scene.text.Text(statut.getContenu());
            contenuText.setWrappingWidth(400);

            Label createdLabel = new Label("Publié le " + statut.getCreated());

            Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");

            Separator separator = new Separator();

            Button detailsBtn = new Button("Voir les détails");
            detailsBtn.setOnAction(event -> {
                // Code pour afficher les détails du statut
            });

            vbox.getChildren().addAll(titreLabel, createdLabel, nbrLikeLabel, separator, detailsBtn);

            return vbox;
        }*/



}

