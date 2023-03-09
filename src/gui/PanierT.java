package gui;

import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
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


        try {

            ObservableList<Produit> observableUserList = FXCollections.observableList(PS.getByorderId(i));
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
}
