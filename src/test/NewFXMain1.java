package test;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NewFXMain1 extends Application {
    @Override
    public void start(Stage primaryStage)  {
        try {
      //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterStatut.fxml"));
        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterCom.fxml"));
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherStatut.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AffS.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Forum.fxml"));

            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Ajout Statut");
           // primaryStage.setTitle("Commenter");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }


    }
    public static void main(String[] args) {

        launch(args);
    }
}
