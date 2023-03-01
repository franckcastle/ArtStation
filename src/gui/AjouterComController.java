package gui;

import entities.Commentaire;
import entities.Statut;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import services.CommentaireService;
import services.StatutService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

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
       c.setDescription(descriptionField.getText());
        c.setId_s(sta.getId_s());
           try {
              if (c.getDescription().length()==0 ) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Erreur Commentaire ! ");
                  alert.setHeaderText("Veuillez ajouter votre commentaire !");
                   alert.showAndWait();
                  return;
             }
             cms.ajouterCom(c);
             reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void reset() {

        descriptionField.setText("");
    }

    @FXML
    void AfficherCommentaire(ActionEvent event){
        try {

            Parent loader = FXMLLoader.load(getClass().getResource("AfficherCom.fxml"));
            descriptionField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur "+ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
