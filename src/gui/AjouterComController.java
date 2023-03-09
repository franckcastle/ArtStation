package gui;


import entities.Commentaire;
import entities.Session;
import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.CommentaireService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static api.BadWords.checkWords;


public class AjouterComController implements Initializable {

    @FXML
    private TextField descriptionField;

    CommentaireService cms = new CommentaireService();
    public Statut sta ;
    public AjouterComController(){}


    @FXML
    void ajouterCom(ActionEvent event) {
        // System.out.println(sta.getId_s());
        Commentaire c = new Commentaire();
        c.setUsername(Session.getUserCon().getUsername());
        c.setDescription(descriptionField.getText());
        c.setId_s(sta.getId_s());
        System.out.println(c);
        try {
            if (c.getDescription().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur Commentaire ! ");
                alert.setHeaderText("Veuillez ajouter votre commentaire !");
                alert.showAndWait();
                return;
            }

            if (checkWords(c.getDescription()).equals("true")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ATTENTION ! ");
                alert.setHeaderText("Votre commentaire contient des gros mots et ne peut pas être ajouté.");
                alert.showAndWait();
                return;
            }
            cms.ajouterCom(c);
            reset();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }



    private void reset() {
        descriptionField.setText("");
    }

    //    @FXML
//    void AfficherCommentaire(ActionEvent event){
//        try {
//
//            Parent loader = FXMLLoader.load(getClass().getResource("AfficherCom.fxml"));
//            descriptionField.getScene().setRoot(loader);
//
//        }catch (IOException ex){
//            System.out.println("Erreur "+ex);
//        }
//    }
    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            descriptionField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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