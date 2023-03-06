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
//    @FXML
//    void modifierCom(ActionEvent event) throws SQLException {
//        String description = descriptionField.getText();
//        try {
//            cs.modifierCom(c);
//            Parent loader = FXMLLoader.load(getClass().getResource("AfficherCom.fxml"));
//            descriptionField.getScene().setRoot(loader);
//        }catch (SQLException | IOException ex){
//            System.out.println(ex);
//        }
//
//    }
    @FXML
public void initialize() {

        descriptionField.setText(c.getDescription());


}
    @FXML
    public void modifierCom(ActionEvent event) throws SQLException {
        if (!descriptionField.getText().isEmpty()) {
            Commentaire cc = new Commentaire();
           cc.setId_c(cc.getId_c());
            cc.setDescription(descriptionField.getText());
            cs.modifierCom(cc);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Vous ne pouvez pas ajouter un commentaire vide !");

            alert.showAndWait();
        }
    }
    @FXML
    void forum(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherStatut.fxml"));
            descriptionField.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }
    public void setCommentaire(Commentaire c) {
        this.c=c;
    }
}
