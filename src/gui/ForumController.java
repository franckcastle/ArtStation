package gui;
import entities.Statut;
import entities.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.CommentaireService;
import services.StatutService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ForumController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TextField textfield;

    @FXML
    private VBox statutsVBox;
    @FXML
    private Button afficher;

    @FXML
    private Button ajouter;

    @FXML
    private Button com;

    @FXML
    private Button forum;



    @FXML
    private Button trilike;

    @FXML
    private TextField titreField;
    private StatutService s = new StatutService();
    CommentaireService cs = new CommentaireService();
    @FXML
    private TextArea resultatArea;

    Commentaire commentaire;
    private boolean isLiked;


    @FXML
    void ajouter(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterStatut.fxml"));
            textfield.getScene().setRoot(loader);

        } catch (IOException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }

    }



    @FXML
    void Forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            textfield.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }
    @FXML
    void afficher(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherStatut.fxml"));
            textfield.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateView(newValue);
        });
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
            System.out.println("statuts is null");
        }
    }
    private HBox createStatutHBox(Statut statut) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label titreLabel = new Label(statut.getTitre());
        titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label createdLabel = new Label("Publié le " + statut.getCreated());
        Label nbrLikeLabel = new Label(statut.getNbrLike() + " personnes aiment ça");

        hbox.getChildren().addAll(titreLabel, createdLabel, nbrLikeLabel);
        return hbox;
    }

    private VBox createStatutVBox(Statut statut) throws SQLException {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label titreLabel = new Label(statut.getTitre());
        titreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Text contenuText = new Text(statut.getContenu());
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
    private void updateViewByNbrLike() {
        if (statutsVBox != null) {
            try {
                List<Statut > listStatut = s.getByNbrLike();
                statutsVBox.getChildren().clear();

                for (Statut statut : listStatut) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Statut.fxml"));
                    Parent parent = fxmlLoader.load();
                    StatutController statutController = fxmlLoader.getController();
                    statutController.setStatut(statut);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    statutsVBox.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }
    @FXML
    private void updateViewByTitre() {
        if (statutsVBox != null) {
            try {
                List<Statut > listStatut = s.getByTitre();
                statutsVBox.getChildren().clear();

                for (Statut statut : listStatut) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Statut.fxml"));
                    Parent parent = fxmlLoader.load();
                    StatutController statutController = fxmlLoader.getController();
                    statutController.setStatut(statut);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    statutsVBox.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("productsFlowPane is null");
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



