package test;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApp extends Application  {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherCtg.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,600,400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Affichier Les produits");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        launch(args);

    }
}
