package gui;

import com.google.zxing.WriterException;
import entities.Evenement;
import entities.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import org.w3c.dom.ls.LSOutput;
import services.EvenementService;
import services.QrCode;
import services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsDetailsController implements Initializable {
    @FXML
    public Label eventTitre;
    @FXML
    public Label descriptionF;
    @FXML
    public Label dateF;
    @FXML
    public Label nbfield;
    @FXML
    public Label prixFiel;
    @FXML
    private DatePicker dateField;
    @FXML
    private Slider mySlider;
    @FXML
    private Label evalField;
    @FXML
    private Rating ratee;

    public Evenement ee;
    EvenementService es = new EvenementService();
    UserService us =new UserService();
    QrCode qr = new QrCode();
    public int rate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void initialize() {
        System.out.println(ee);
        eventTitre.setText("Titre:"+ee.getTitre());
        descriptionF.setText("Description:"+ee.getDescription());
        //LocalDate localDate = dateField.getValue();
        //Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        ee.getDateDebut();
        //dateF.setText(Date.toString(ee.getDateDebut()));
        nbfield.setText(Integer.toString(ee.getNbPlace()));
        prixFiel.setText(Float.toString(ee.getPrix()));
        evalField.setText(Float.toString(ee.getRating()));



    }


    public UsDetailsController() {
    }

    public UsDetailsController(Evenement ee) {
        this.ee = ee;
    }
    public void setEvent(Evenement ee){
        this.ee=ee;
    }
    @FXML
    public void retour(MouseEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("UsEvent.fxml"));

            //Parent root = loader.load();
            eventTitre.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void qr(ActionEvent event) throws IOException, WriterException, SQLException {
        int idcon = Session.getUserCon().getUserId();
        int nb_motif= -1 ;
        try {
            nb_motif = us.participerEv(idcon,ee);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(nb_motif == 1){
            System.out.print("Vous avez déja résérvé.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Vous avez déja résérvé");

            alert.showAndWait();
            return ;
        }else   if(nb_motif == 2){
            System.out.print("Désolé toute les place sont résérvés ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("Désolé toute les place sont résérvés");

            alert.showAndWait();
            return ;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("succés");
        alert.setHeaderText("vous etes inscrit a cette evenement");
        alert.showAndWait();
        String qrCodeText = ee.getTitre();
        System.out.println(qrCodeText);
        String filePath = "D:/Dev/ArtStation/src/image/JD15.png";
        int size = 100;
        String fileType = "png";
        File qrFile = new File(filePath);

        qr.createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println("DONE");
    }

    public void eval(ActionEvent event) throws SQLException {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Rate Event");
        Rating rating = new Rating();
        rating.setRating(0);
        rating.setMax(10);
        dialog.getDialogPane().setContent(rating);
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<Integer> result = dialog.showAndWait();
        int i =(int)rating.getRating();

        if (result.isPresent() ) {


            es.rateEvent(ee.getId(), i);

        }

    }
    @FXML
    public void annulerReservation (ActionEvent event){
        boolean vb =false ;
        int idcon = Session.getUserCon().getUserId();
        try {
            vb = us.annulerRes(idcon,ee) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (vb){
            System.out.println("Réservation annuler");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("annulation ");
            alert.setHeaderText("Réservation annuler");

            alert.showAndWait();
        }else{
            System.out.println("Vous n'etes pas inscrit dans cette événement");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("annulation ");
            alert.setHeaderText("Vous n'etes pas inscrit dans cette événement");

            alert.showAndWait();

        }
    }
}
