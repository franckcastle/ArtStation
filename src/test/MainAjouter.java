package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAjouter extends Application {





    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Ajout
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterEv.fxml"));
            //GetAll+DEl
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterWorkshop.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root,800,400);
            primaryStage.setScene(scene);


            primaryStage.setTitle("Workshops");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
    public static void main(String[] args)  {
        launch(args);
    }
}
