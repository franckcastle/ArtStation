package gui;

import com.google.zxing.WriterException;
import entities.Evenement;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
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


    public DetailsController() {
    }

    public DetailsController(Evenement ee) {
        this.ee = ee;
    }
    public void setEvent(Evenement ee){
        this.ee=ee;
    }
    @FXML
    public void retour(MouseEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Event.fxml"));

            //Parent root = loader.load();
            eventTitre.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void qr(ActionEvent event) throws IOException, WriterException {
        String qrCodeText = ee.getDescription();
        System.out.println(qrCodeText);
        String filePath = "C:/Users/MSI/Desktop/ArtStation/src/image/JD7.png";
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
}
