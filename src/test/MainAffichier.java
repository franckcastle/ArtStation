package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAffichier extends Application {
    @Override
    public void start(Stage primaryStage)  {
        try {
            //Ajout
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterEv.fxml"));
            //GetAll+DEl
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Event.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ArtStation ");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
//
}


