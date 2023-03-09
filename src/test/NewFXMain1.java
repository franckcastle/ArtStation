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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Forum.fxml"));


            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("Forum");
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
