package gui;

import entities.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.CommentaireService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static api.BadWords.checkWords;

public class ModifierComController implements Initializable {

    @FXML
    private TextField descriptionField;


    CommentaireService cs = new CommentaireService();
    public Commentaire c;

    public ModifierComController() {
    }

    public ModifierComController(Commentaire c) {

        this.c = c;
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
public void initialize() {
        descriptionField.setText(c.getDescription());



}
    @FXML
    public void modifierCom(ActionEvent event) throws SQLException {
        if (!descriptionField.getText().isEmpty()) {

             c.setId_c(c.getId_c());
             c.setDescription(descriptionField.getText());
             cs.modifierCom(c);
        } if (checkWords(c.getDescription()).equals("true")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION ! ");
            alert.setHeaderText("Votre commentaire contient des gros mots et ne peut pas être ajouté.");
            alert.showAndWait();
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Vous ne pouvez pas ajouter un commentaire vide !");

            alert.showAndWait();
        }
    }
    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            descriptionField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }
    public void setCommentaire(Commentaire c) {
        this.c=c;
    }
}
