package gui;

import entities.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ShoppingCartService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class afficherOrderController implements Initializable {
    @FXML
    private TableColumn<ShoppingCart, String> addresse_column;

    @FXML
    private TableView <ShoppingCart>carts_table;


    @FXML
    private TableColumn  delete;

    //@FXML
   // private TableColumn date_column;

    @FXML
    private TableColumn id_column;

    @FXML
    private TableColumn <ShoppingCart,String>nom_column;

    @FXML
    private TableColumn  postale_column;
    @FXML
    private TableColumn  imprimer;
    @FXML
    private TableColumn  modifier;
    @FXML
    private TableColumn  pruducts ;

    @FXML
    private TableColumn prenom_column;

    @FXML
    private TableColumn  prix_column;

    @FXML
    private TableColumn sta_column;

    @FXML
    private TableColumn ville_column;


    ShoppingCartService ss =new ShoppingCartService();





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // TODO
            List<ShoppingCart> ShoppingCarts = ss.recuperer();
            System.out.println(ShoppingCarts);
            ObservableList<ShoppingCart> olu = FXCollections.observableArrayList(ShoppingCarts);

            carts_table.setItems(olu);
            id_column.setCellValueFactory(new PropertyValueFactory("orderId"));
            nom_column.setCellValueFactory(new PropertyValueFactory("nom"));
            prenom_column.setCellValueFactory(new PropertyValueFactory("prenom"));
            ville_column.setCellValueFactory(new PropertyValueFactory("ville"));
            addresse_column.setCellValueFactory(new PropertyValueFactory("Adresse"));
            postale_column.setCellValueFactory(new PropertyValueFactory("code_postale"));
         //   date_column.setCellValueFactory(new PropertyValueFactory("order_Date"));
            prix_column.setCellValueFactory(new PropertyValueFactory("Total_price"));
            sta_column.setCellValueFactory(new PropertyValueFactory("sta"));
            this.getProducts();
            this.delete();
            this.modifier();
            this.imprimer();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }


    public void getProducts(){

        pruducts.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("getProducts");
                        b.setOnAction(event -> {


                            try {

                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("getProducts.fxml"));
                                Parent root = loader.load();
                                ShoppingCart shop= (ShoppingCart)carts_table.getItems().get(getIndex());

                                GetProducts controller =loader.getController();
                                controller.sc= shop;

                                carts_table.getScene().setRoot(root);



                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Parent root = loader.load();


                        });
                        setGraphic(b);

                    }
                }
            };

        });




    }










    public void modifier(){
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction(event -> {


                            try {

                                FXMLLoader loader =  new FXMLLoader(getClass().getResource("ModifierOrder.fxml"));
                                Parent root = loader.load();

                                //AnchorPane  = loader.load();

                                TableCell cell = (TableCell) ((Button) event.getSource()).getParent();
                                int rowIndexBtnmodifier = cell.getIndex();
                                //AnchorPane pane=loader.load();
                                ShoppingCart shop= (ShoppingCart)carts_table.getItems().get(getIndex());
                                System.out.println("hetha order"+shop.getOrderId());
                                ModifierOrder controller =loader.getController();
                                controller.sc= shop;
                                controller.setUsername(nom_column.getCellData(rowIndexBtnmodifier));
                                carts_table.getScene().setRoot(root);

                                //Stage stage = new Stage();
                                //stage.setScene(new Scene(root));
                                //stage.show();
                                //AnchorPane anchorPane = (AnchorPane) root;

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Parent root = loader.load();



                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }





    public void delete() {

        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ss.supprimer(carts_table.getItems().get(getIndex()))) {
                                    carts_table.getItems().remove(getIndex());
                                    carts_table.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });


    }





    public void imprimer(){
        imprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("imprimer");
                        b.setOnAction(event -> {


                     System.out.println("asslema");
                           ShoppingCartService as = new ShoppingCartService();
                            try {
                             //   as.createPDF(carts_table);
                                as.createPDF(carts_table.getItems().get(getIndex()));
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("PDF créé avec succès !");
                                alert.showAndWait();
                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Dialog");
                                alert.setHeaderText("Une erreur s'est produite");
                                alert.setContentText("Impossible de créer le PDF : " + e.getMessage());
                                alert.showAndWait();
                            }


                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }






    public void ajouterbtn(javafx.event.ActionEvent actionEvent) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ajouterOrder.fxml"));
            carts_table.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
