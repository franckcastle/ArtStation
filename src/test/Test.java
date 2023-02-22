package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,700,700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Inscription");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("xerror" + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}