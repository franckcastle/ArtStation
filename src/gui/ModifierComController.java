package gui;

import entities.Commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import services.CommentaireService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierComController implements Initializable {
 String x;
 String description;

    CommentaireService  cs = new CommentaireService();
    public ModifierComController() {
    }
    public ModifierComController(String description) {
        this.description=description;
    }
    public void setDescription(String description) {
        descriptionField.setText(description);
        x = descriptionField.getText();
    }



    @FXML
    private TextField descriptionField;
   public Commentaire c1 ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void modifierCom(ActionEvent event) throws SQLException {
        String description = descriptionField.getText();
        try {
            cs.modifierCom(description,x);
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherCom.fxml"));
            descriptionField.getScene().setRoot(loader);
        }catch (SQLException | IOException ex){
            System.out.println(ex);
        }

    }


}
