package gui;

import entities.CartItem;
import entities.Produit;
import entities.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import services.CItemService;
import services.ProduitService;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Home implements Initializable{

    @FXML
    private TableColumn<?, ?> added;

    @FXML
    private TableView<Produit> hometab;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private Button pan;
    @FXML
    private TableColumn<?, ?> prix;

    @FXML
    private TableColumn<Produit, Integer>  quantity;
CItemService ci =new CItemService();

    ProduitService pp = new ProduitService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            // TODO
            List<Produit> produits = pp.recuperer();
            ObservableList<Produit> olu = FXCollections.observableArrayList(produits);

            hometab.setItems(olu);
            nom.setCellValueFactory(new PropertyValueFactory("nom"));
            prix.setCellValueFactory(new PropertyValueFactory("prix"));
            quantity.setCellValueFactory(new PropertyValueFactory("qte_stock"));
this.addtocartt();


        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }





    public ShoppingCart createEmptyCart() {
        ShoppingCart cart = new ShoppingCart();

        cart.setNom("");
        cart.setPrenom("");
        cart.setVille("");
        cart.setAdresse("");
        cart.setCode_postale(0);

        cart.setTotal_price(0.0f);
        cart.setSta("");
        return cart;
    }






    int a =22;
    public void addtocartt(){


            added.setCellFactory((param) -> {
                return new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        setGraphic(null);
                        if (!empty) {
                            Button b = new Button("ajouter au panier");
                            b.setOnAction((event) -> {
                                Produit p= (Produit) hometab.getItems().get(getIndex());

                             System.out.println(p);
                                try {
                                    addedItem(p,a);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }


                            });
                            setGraphic(b);
                        }
                    }
                };

            });


        }

        public void addedItem(Produit p,int c) throws SQLException {
            CartItem item = new CartItem();

            item.setId(p.getId());
            item.setOrderId(c);
            item.setQuantity(3);
            item.setPrice((float)p.getPrix()*item.getQuantity());
            ci.ajouter(item);
        }


    public void ajouterPanier(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("panier.fxml"));
        Parent root = loader.load();
       Panier controller =loader.getController();
       controller.display(a);
       controller.s=a;
        hometab.getScene().setRoot(root);
    }
}


