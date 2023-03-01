package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMmain extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        try {
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajouterItem.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/afficherOrder.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Affichier Evennement ");
            stage.show();
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}