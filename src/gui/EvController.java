package gui;

import entities.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class EvController {
    @FXML
    public ImageView evImg;
    @FXML
    public Label evTitre;
    @FXML
    public Label evLocal;
    @FXML
    public ImageView evDetails;
    @FXML
    public ImageView evDel;
    @FXML
    public ImageView evMod;

    public void setData(Evenement e){
        System.out.println(e);
        File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        ImageView evImg = new ImageView(image);

        //e.setImage("@..\image/eminem.jpg");
       // Image image=new Image(getClass().getResourceAsStream(e.getImage()));
      //  evImg.setImage(image);
       evTitre.setText(e.getTitre());
        evLocal.setText(e.getLocalisation());
    }
}
