package gui;

import entities.Evenement;
import entities.Feedback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import services.FeedService;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FeedEvController implements Initializable {
    @FXML
    public TextArea feedField;

    FeedService fs = new FeedService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public Evenement ee;

    public FeedEvController() {
    }

    public FeedEvController(Evenement ee) {
        this.ee = ee;
    }
    @FXML
    public void feedbackEv(ActionEvent event){
        Feedback f =new Feedback();
        f.setId_Ev(ee.getId());
        f.setText(feedField.getText());
        try {
            fs.ajouter(f);
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
    @FXML
    public void retour(MouseEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));

            //Parent root = loader.load();
            feedField.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
