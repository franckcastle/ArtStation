package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventController implements Initializable {
    EvenementService es = new EvenementService();
    @FXML
    public GridPane eventContainer;

    @FXML
    private VBox lkbir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Evenement> ev =es.getAll();
            int column=0;
            int row=0;
            for(int i=0;i<ev.size();i++){

                FXMLLoader loader =  new FXMLLoader(getClass().getResource("Ev.fxml"));
                //loader.setLocation(getClass().getResource("Ev.fxml"));
               VBox eventBox = loader.load();
                //Parent root = loader.load();

                //System.out.println("test");
                EvController controller=loader.getController();
                controller.setData(ev.get(i));

                //System.out.println("test");
                //eventBox.getChildren().add(root);

                if(column==6){
                    column=0;
                    ++row;
                }
                eventContainer.add(eventBox,column++,row);
                GridPane.setMargin(eventBox,new Insets(10));


        }
        }catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ajouterEv(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Aj.fxml"));

            //Parent root = loader.load();
            eventContainer.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
