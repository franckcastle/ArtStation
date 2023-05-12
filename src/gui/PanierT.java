package gui;

import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PanierT {

    @FXML
    private Text prixcol;



    @FXML
    private ListView<Produit> ProduitTable;



   ProduitService PS =new ProduitService() ;
    @FXML
    void retourbtn(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("Market.fxml"));

            //Parent root = loader.load();
            ProduitTable.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int s;
    Float t ;

    public static Float getTotalPrice(List<Produit> produits) {
        Float total = 0.0f;
        for (Produit p : produits) {
            total += p.getPrix();
        }
        return total;
    }
    public void display(int i){

i=7;
        try {

            ObservableList<Produit> observableUserList = FXCollections.observableList(PS.getByorderId(7));
            ProduitTable.setItems(observableUserList);
           ProduitTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
           ProduitTable.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {
                @Override
                public ListCell<Produit> call(ListView<Produit> listView) {
                    return new ListCell<Produit>() {
                        @Override
                        protected void updateItem(Produit p, boolean empty) {

                            super.updateItem(p, empty);
                            if (p!= null) {
                                setText(p.getNom() + " - " +p.getQte_stock()+ " - " + p.getPrix());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            Float tot =getTotalPrice(PS.getByorderId(i));
            t =tot ;
            prixcol.setText(String.valueOf(tot));


        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public void buybtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("panierOrder.fxml"));
        Parent root = loader.load();
        PanierOrder controller =loader.getController();
        // Cart c =loader.getController();
        //c.setUsername(t);
        System.out.println(s);
        controller.p=s;
        controller.price=t;
        ProduitTable.getScene().setRoot(root);
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
