package test;

import entities.Workshop;
import services.WorkshopServices;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Test.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Helloxx!");
        stage.setScene(scene);
        stage.show();
    }





    public static void main(String[] args) throws IOException {
        launch();
        try {
            Workshop p = new Workshop(3, 20, 45, new java.util.Date(), "guitare", "dave", "bch yaalamkom guitare", "20h", "22h");
            WorkshopServices ps = new WorkshopServices();
            ps.ajouterWs(p);
            ps.modifierWs(p);
            ps.supprimerWs(2);
            System.out.println(ps.recuperer(p));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}

