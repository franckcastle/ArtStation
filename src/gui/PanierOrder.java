package gui;

import entities.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.ShoppingCartService;

import java.io.IOException;
import java.sql.SQLException;

public class PanierOrder {

    @FXML
    private TextField addresse_col;

    @FXML
    private Button modifier_button;

    @FXML
    private TextField nom_col;

    @FXML
    private TextField postale_id;

    @FXML
    private TextField prenom_col;

    @FXML
    private Button retourId;

    @FXML
    private TextField ville_col;

    ShoppingCartService ss =new ShoppingCartService();


    public int p;

    public float price;

    @FXML
    void btnModifier(ActionEvent event) throws SQLException, IOException {
    System.out.println(p);

        if(/*!nom_col.getText().isEmpty()||!prenom_col.getText().isEmpty()||!prenom_col.getText().isEmpty()||!*//*ville_col.getText().isEmpty()||*/!addresse_col.getText().isEmpty()){
            ShoppingCart s = new ShoppingCart();
            s.setOrderId(p);
            s.setNom(nom_col.getText());
            s.setPrenom(prenom_col.getText());
            s.setVille(ville_col.getText());
            s.setAdresse(addresse_col.getText());
            s.setCode_postale(Integer.parseInt(postale_id.getText()));

            ss.modifier(s);
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();
             Cart c =loader.getController();
            c.setPrice(price);
            c.amount=price;
            nom_col.getScene().setRoot(root);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }
    }

    @FXML
    void retourAf(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("PanierT.fxml"));

            //Parent root = loader.load();
            nom_col.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void events(MouseEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("UsEvent.fxml"));
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
        BorderPane root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
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
        AnchorPane root = FXMLLoader.load(getClass().getResource("PanierT.fxml"));
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
    void workshops(MouseEvent event)  throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("ChoisirWorkshop.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(500);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

}
