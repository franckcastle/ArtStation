package gui;


        import entities.Workshop;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.BorderPane;
        import javafx.stage.Stage;
        import javafx.util.Callback;
        import services.WorkshopServices;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

public class affichageController implements Initializable {
    WorkshopServices ws = new WorkshopServices();
    @FXML
    private TextField titreField;

    @FXML
    private TextField dureeField;

    @FXML
    private TextField nom_artisteField;

    @FXML
    private TextField dateField;
    @FXML
    private TextField heure_debutField;
    @FXML
    private TextField heure_finField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField categorieField;
    @FXML
    private TextField nbPlacesField;



    @FXML
    private Button retourAf;



    @FXML
    private ListView<Workshop> AfficherId;
    int i;

    @FXML
    void Modifier(ActionEvent event) throws SQLException{
        Workshop wp = new Workshop();

        String titre = titreField.getText();




       // String heure_debut=heure_debutField.getText();
       // String heure_fin=heure_finField.getText();



        String categorie=categorieField.getText();
        String nom_artiste=nom_artisteField.getText();
        int duree = Integer.parseInt(dureeField.getText());
        int nbPlaces= Integer.parseInt(nbPlacesField.getText());
        int prix = (int) Float.parseFloat(prixField.getText());
        wp.setId(i);
        wp.setTitre(titreField.getText());
        wp.setHeure_debut(heure_debutField.getText());
        wp.setHeure_fin(heure_finField.getText());
        wp.setCategorie(categorieField.getText());
        wp.setNom_artiste(nom_artisteField.getText());
        wp.setDuree(Integer.parseInt(dureeField.getText()));
        wp.setNbPlaces(Integer.parseInt(nbPlacesField.getText()));
        wp.setPrix(Float.parseFloat(prixField.getText()));

        ws.modifierWs(wp);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Modification");

        // Créer le contenu de la boîte de dialogue
        DialogPane dialogPane = new DialogPane();
        dialogPane.getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        dialogPane.setContent(new Label("Votre workshop a été modifié avec succes"));

        // Afficher la boîte de dialogue et attendre la réponse
        dialog.setDialogPane(dialogPane);
        dialog.showAndWait();

        AfficherId.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Workshop> observableUserList = FXCollections.observableList(ws.recuperer());
        AfficherId.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        AfficherId.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        titreField.clear();
        heure_debutField.clear();
        heure_finField.clear();
        categorieField.clear();
        nom_artisteField.clear();
        dureeField.clear();
        nbPlacesField.clear();
        prixField.clear();

    }





   @FXML
    void Supprimer(ActionEvent event) throws SQLException{

       Workshop wp = new Workshop();

       String titre=titreField.getText();
       String heure_debut=heure_debutField.getText();
       String heure_fin=heure_finField.getText();
       String categorie=categorieField.getText();
       String nom_artiste=nom_artisteField.getText();
       int duree = Integer.parseInt(dureeField.getText());
       int nbPlaces= Integer.parseInt(nbPlacesField.getText());
       int prix = (int) Float.parseFloat(prixField.getText());
       wp.setId(i);
       ws.supprimerWs(wp);
       Dialog<String> dialog = new Dialog<>();
       dialog.setTitle("Suppression");

       // Créer le contenu de la boîte de dialogue
       DialogPane dialogPane = new DialogPane();
       dialogPane.getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
       dialogPane.setContent(new Label("Votre workshop a été supprimé avec succes"));

       // Afficher la boîte de dialogue et attendre la réponse
       dialog.setDialogPane(dialogPane);
       dialog.showAndWait();
        AfficherId.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<Workshop> observableUserList = FXCollections.observableList(ws.recuperer());
        AfficherId.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        AfficherId.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
       titreField.clear();
       heure_debutField.clear();
       heure_finField.clear();
       categorieField.clear();
       nom_artisteField.clear();
       dureeField.clear();
       nbPlacesField.clear();
       prixField.clear();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ObservableList<Workshop> observableUserList = FXCollections.observableList(ws.recuperer());
            AfficherId.setItems(observableUserList);
            AfficherId.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            AfficherId.setCellFactory(new Callback<ListView<Workshop>, ListCell<Workshop>>() {
                @Override
                public ListCell<Workshop> call(ListView<Workshop> listView) {
                    return new ListCell<Workshop>() {
                        @Override
                        protected void updateItem(Workshop wp, boolean empty) {
                            super.updateItem(wp, empty);
                            if (wp != null) {
                                setText(wp.getTitre() + " - " +wp.getHeure_debut()+ " - " + wp.getHeure_fin()  + " - " +wp.getCategorie()+ " - " +wp.getNom_artiste()+ " - " +wp.getDuree()+ " - " +wp.getNbPlaces()+ " - " +wp.getPrix()          );
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            AfficherId.setOnMouseClicked(event -> {
               Workshop userSelectionne = AfficherId.getSelectionModel().getSelectedItem();
                if (userSelectionne != null) {

                    titreField.setText(userSelectionne.getTitre());
                    heure_debutField.setText(userSelectionne.getHeure_debut());
                    heure_finField.setText(userSelectionne.getHeure_fin());
                    categorieField.setText(userSelectionne.getCategorie());
                    nom_artisteField.setText(userSelectionne.getNom_artiste());
                    dureeField.setText(String.valueOf(userSelectionne.getDuree()));
                    nbPlacesField.setText(String.valueOf(userSelectionne.getNbPlaces()));
                    prixField.setText(Integer.toString((int) userSelectionne.getPrix()));


i=userSelectionne.getId();
                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    @FXML
    public void retourAf(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterWorkshop.fxml"));

            //Parent root = loader.load();
            AfficherId.getScene().setRoot(loader);
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
