package gui;

import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
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
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateView(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

                if(column==4){
                    column=0;
                    ++row;
                }
                eventContainer.add(eventBox,column++,row);
                GridPane.setMargin(eventBox,new  Insets(10));


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
    private void updateView(String searchQuery) throws IOException {
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

            int numColumns = 3; // the number of columns you want to display
            int rowIndex = 1;
            int columnIndex = 0;
            for (Evenement ev : filteredProducts) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Ev.fxml"));
                Parent parent = fxmlLoader.load();
                EvController productController = fxmlLoader.getController();
                productController.setData(ev);
                Node node = parent;
                eventContainer.add(node, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == numColumns) {
                    columnIndex = 0;
                    rowIndex++;
                }
                GridPane.setMargin(node, new Insets(10));
            }
        } else {
            System.out.println("eventContainer is null");
        }
    }
    @FXML
    void events(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void forum(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminForum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomeApp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void orders(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("OrderAffiche.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void products(MouseEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void workshops(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("InterfaceAdminWs.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
    @FXML
    void Admin(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }
}
