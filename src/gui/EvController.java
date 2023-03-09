package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.EvenementService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EvController implements Initializable {
    EvenementService es = new EvenementService();
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
    @FXML
    private VBox toj;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public Evenement e ;
    public void setData(Evenement e){
        //System.out.println(e);
        this.e=e;
        String img = e.getImage();
        URL imageURL = getClass().getResource(img);
        if (imageURL != null) {
            Image image = new Image(imageURL.toString());
            evImg.setImage(image);}

       evTitre.setText(e.getTitre());
        evLocal.setText(e.getLocalisation());

    }
    public void modifier(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModiffierEv.fxml"));
        Parent root = loader.load();

        //AnchorPane  = loader.load();


        //AnchorPane pane=loader.load();
        int even= e.getId();
        System.out.println("hetha even"+even);
        ModiffierEvController controller =loader.getController();
        controller.iii=even;
        evTitre.getScene().setRoot(root);
    }
    public void delete(MouseEvent mouseEvent) throws IOException {
       if(es.supprimerEv(e.getId())){
           FXMLLoader loader =  new FXMLLoader(getClass().getResource("Event.fxml"));
           Parent root = loader.load();
           evTitre.getScene().setRoot(root);

       };
        /*Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        // Hide and show the window to refresh the scene
        currentStage.hide();
        currentStage.show();*/


    }
    public void deatils(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("Details.fxml"));
        Parent root = loader.load();
        int aaa = e.getId();
        System.out.println(aaa);
        Evenement even =e;
        /*even.setId(e.getId());
        even.setDateDebut(e.getDateDebut());
        even.setDescription(e.getDescription());
        even.setNbPlace(e.getNbPlace());
        even.setPrix(e.getPrix());*/
        System.out.println("hetha even"+e.getId());
        DetailsController controller =loader.getController();
        controller.ee=even;
        controller.initialize();
        evTitre.getScene().setRoot(root);
    }
    public void commenter(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("FeedEv.fxml"));
        Parent root = loader.load();

        //AnchorPane  = loader.load();


        //AnchorPane pane=loader.load();
        Evenement even= e;
        System.out.println("hetha even"+even.getId());
        FeedEvController controller =loader.getController();
        controller.ee=even;
        evTitre.getScene().setRoot(root);
    }

}
