package gui;

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
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AjouterStatutController implements Initializable {

    @FXML
    private TextField contenuField;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField titreField;

    StatutService ss = new StatutService();



    @FXML
    void ajouter(ActionEvent event) {
        Statut s = new Statut();
        s.setUsername(Session.getUserCon().getUsername());

        s.setTitre(titreField.getText());
        s.setContenu(contenuField.getText());
        try {
            if (s.getTitre().length()==0 || s.getContenu().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de publier votre statut ! ");
                alert.setHeaderText("Veuillez remplir tous les champs de votre statut !");
                alert.showAndWait();
                return;
            }
            ss.ajouter(s);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    private void reset() {
        titreField.setText("");
        contenuField.setText("");
    }

    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            titreField.getScene().setRoot(loader);

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
