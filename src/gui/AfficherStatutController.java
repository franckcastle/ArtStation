package gui;

import entities.Commentaire;
import entities.Session;
import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.CommentaireService;
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class AfficherStatutController implements Initializable {
    @FXML
    private VBox statutsVBox;
    @FXML
    private Button ajouterStatutBtn;

    private StatutService s = new StatutService();

    private boolean isLiked;
    @FXML
    private TextField searchField;
    Statut ss ;
    CommentaireService cs = new CommentaireService();
    Separator separator = new Separator();
    Label usernameLabel = new Label(Session.getUserCon().getUsername());


    @FXML
    private VBox com;

    Commentaire commentaire;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateView(newValue);
        });
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

        Label usernameLabel = new Label(statut.getUsername());
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #fac881 ");


        Label titreLabel = new Label(statut.getTitre());
        titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #455771 ");

        Text contenuText = new Text(statut.getContenu());
        contenuText.setWrappingWidth(400);
        contenuText.setStyle("-fx-font-size: 15px");


        Label createdLabel = new Label("Publié le " + statut.getCreated());
        createdLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");

        Label nbrLikeLabel = new Label(statut.getNbr_like() + " personnes aiment ça");
        nbrLikeLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        Label comLabel = new Label("Commentaires: ");
        comLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;-fx-text-fill: #455771");

        FlowPane commentairesFlowPane = new FlowPane();
        commentairesFlowPane.setHgap(25);
        commentairesFlowPane.setVgap(25);
        commentairesFlowPane.getChildren().add(separator);

        List<Commentaire> commentairesList = null;
        try {
            commentairesList = cs.recupererComByIdStatut(statut.getId());
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }for (Commentaire commentaire : commentairesList) {

           Label username2Label = new Label(commentaire.getUsername());
            username2Label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #455771 ");

            Label commentaireLabel = new Label(commentaire.getDescription());
            Label comsLabel = new Label(Integer.toString(commentaire.getId()));

            commentaireLabel.setContentDisplay(ContentDisplay.LEFT);
            commentaireLabel.setOnMouseClicked(event -> {

                try {
                    FXMLLoader loader =new  FXMLLoader(getClass().getResource("ModifierCom.fxml"));
                    Parent root = loader.load();

                    ModifierComController controller = loader.getController();
                    controller.id=commentaire.getId();
                    statutsVBox.getScene().setRoot(root);
                }catch (IOException ex){
                    System.out.println("Erreur"+ex.getMessage());
                }
            });
            Separator separator = new Separator();
            separator.setPrefWidth(1);
            separator.setStyle("-fx-background-color: gray ; -fx-padding: 5px 0px; -fx-background-radius: 50%;");


            commentairesFlowPane.getChildren().addAll(username2Label,commentaireLabel,separator);
        }





            HBox boutons = new HBox();
        boutons.setSpacing(30);
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
                    nbrLikeLabel.setText((statut.getNbr_like() + 1) + " personnes aiment ça");
                } else {
                    isLiked = false;
                    s.supprimerLikeStatut(statut);
                    nbrLikeLabel.setText((statut.getNbr_like()) + " personnes aiment ça");
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
                    s.supprimer(statut.getId());
                    statutsVBox.getChildren().remove(vbox);
                } catch (SQLException ex) {
                    System.out.println("Erreur" + ex.getMessage());
                }
            }
        });
        boutons.getChildren().addAll(supprimerBtn,modifierBtn,likeBtn,commenterBtn);
        vbox.getChildren().addAll(usernameLabel,titreLabel, contenuText, createdLabel, nbrLikeLabel,comLabel,commentairesFlowPane,boutons);



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
        private void updateView(String searchQuery) {
            if (statutsVBox != null) {
                try {
                    List<Statut> listProd = null;
                    try {
                        listProd = s.recuperer();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    statutsVBox.getChildren().clear(); // clear the current products

                    // filter the list of products based on the search query
                    List<Statut> filteredProducts = listProd.stream()
                            .filter(ss -> ss.getTitre().toLowerCase().contains(searchQuery.toLowerCase()))
                            .collect(Collectors.toList());

                    for (Statut statut : filteredProducts) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Statut.fxml"));
                        Parent parent = fxmlLoader.load();
                        StatutController statutController = fxmlLoader.getController();
                        statutController.setStatut(statut);
                        Region region = (Region) parent;
                        Node node = region.getChildrenUnmodifiable().get(0);
                        statutsVBox.getChildren().add(node);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("productsFlowPane is null");
            }
        }

    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            statutsVBox.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
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
