package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    void GererReclam(ActionEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("AfficherReclam.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(820);
        stage.setHeight(550);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void GererUsers(ActionEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(820);
        stage.setHeight(550);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
