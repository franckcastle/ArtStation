package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EventController implements Initializable {
    EvenementService es = new EvenementService();
    @FXML
    public GridPane eventContainer;

    @FXML
    private VBox lkbir;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateView(newValue);
        });*/
        try {
            List<Evenement> ev =es.getAll();
            int column=0;
            int row=1;
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

                if(column==3){
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
    /*private void updateView(String searchQuery) {
        if (eventContainer != null) {
            List<Evenement> listEvent = null;
            try {
                listEvent = es.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            eventContainer.getChildren().clear(); // clear the current products

            // filter the list of products based on the search query
            List<Evenement> filteredProducts = listEvent.stream()
                    .filter(e -> e.getTitre().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());

                *//*for (Evenement ev : filteredProducts) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Ev.fxml"));
                    Parent parent = fxmlLoader.load();
                    EvController productController = fxmlLoader.getController();
                    productController.setData(ev);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    eventContainer.getChildren().add(node);
                }*//*
        } else {
            System.out.println("productsFlowPane is null");
        }
    }*/

}
