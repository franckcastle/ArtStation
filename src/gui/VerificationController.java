package gui;

import entities.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import services.SendSms;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class VerificationController implements Initializable {

    @FXML
    private TextField codeTf;
    private String generator (){
        Random random = new Random();
        int number = random.nextInt(10000);
        String result = String.format("%04d", number);
        return result;
    }
    String code = generator();
@FXML
 void VerifierCode (ActionEvent event ){
     if (code.equals(codeTf.getText())){
         try {

             Parent loader = FXMLLoader.load(getClass().getResource("AfficherUsers.fxml"));
             codeTf.getScene().setRoot(loader);
         }catch (IOException ex){
             System.out.println(ex);
         }
     }else {

     }
 }

    @FXML
    void EnvoyerSms(ActionEvent event){
        SendSms.SendSms("+21627005915","Votre Code de verification ArtStation est :"+code);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
