package test;



import entities.Evenement;
import services.EvenementService;
import services.UserService;
import utils.MyDb;

import java.sql.SQLException;
import java.util.Date;
//TEST
public class Test {
    public static void main(String[] args) throws Exception {


            MyDb db= new MyDb();
        EvenementService es= new EvenementService();
        UserService us= new UserService();
        Evenement e=new Evenement();
        e.setId(5);
        e.setNbPlace(1);
        boolean vb =us.annulerRes(20,e);
       //boolean vb =us.participerEv(14,e);
        System.out.println(vb);


        try {
            Evenement e1= new Evenement(4,"aslema","event","raddes",new java.util.Date(),new java.util.Date(),12.66F,6 ) ;
            //es.ajouterEv(e1);
            //Evenement e1=new Evenement();
            //e1.setTitre("haja modifié");
            //e1.setId(2);
            //es.supprimerEv(6);
            //es.modifierEvent(e1);
            System.out.println(es.getAll());

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        }

}
/*=======
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LandingPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,700,700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ArtStation");
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("xerror" + ex);
        }
    }

    /**
     * @param args the command line arguments
     */


