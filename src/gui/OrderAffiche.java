package gui;

import entities.Remise;
import entities.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import services.ShoppingCartService;
import test.FXMmain;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderAffiche implements Initializable {

    @FXML
    private TextField PriceCol;

    @FXML
    private TextField adressecol;

    @FXML
    private TextField dateCol;

    @FXML
    private TextField etaCol;

    @FXML
    private TextField nomcol;

    @FXML
    private ListView<ShoppingCart> orderTab;

    @FXML
    private TextField postaleCol;

    @FXML
    private TextField prenomcol;

    @FXML
    private TextField villecol;

    @FXML
    void AjouterOrder(ActionEvent event) throws SQLException {
        ShoppingCart r =new ShoppingCart();

        r.setNom(nomcol.getText());
        r.setPrenom(prenomcol.getText());
        r.setVille(villecol.getText());
        r.setAdresse(adressecol.getText());
        r.setCode_postale(Integer.parseInt(postaleCol.getText()));
        r.setTotal_price(Integer.parseInt(PriceCol.getText()));

        sc.ajouter(r);
        orderTab.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<ShoppingCart> observableUserList = FXCollections.observableList(sc.recuperer());
        orderTab.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
        orderTab.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        nomcol.clear();
        prenomcol.clear();
        villecol.clear();
        adressecol.clear();
        postaleCol.clear();
        etaCol.clear();
        dateCol.clear();
        PriceCol.clear();
    }

    @FXML
    void ModifierOrder(ActionEvent event) throws SQLException {

        String nom=nomcol.getText();
        String prenom=prenomcol.getText();
        String ville=villecol.getText();
        String Adresse=adressecol.getText();
        int postale=Integer.parseInt(postaleCol.getText());
        float price= Float.parseFloat(PriceCol.getText());
        String etat = etaCol.getText();
        ShoppingCart r =new ShoppingCart();
        r.setOrderId(i);
        r.setNom(nom);
        r.setPrenom(prenom);
        r.setVille(ville);
        r.setAdresse(Adresse);
        r.setCode_postale(postale);
        r.setTotal_price(price);
        r.setSta(etat);


        sc.modifier(r);
        orderTab.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<ShoppingCart> observableUserList = FXCollections.observableList(sc.recuperer());
       orderTab.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
       orderTab.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        nomcol.clear();
        prenomcol.clear();
        villecol.clear();
        adressecol.clear();
        postaleCol.clear();
        etaCol.clear();
        dateCol.clear();
        PriceCol.clear();

    }

    @FXML
    void SupprimerOrder(ActionEvent event) throws SQLException {

        ShoppingCart orderSelectionne = orderTab.getSelectionModel().getSelectedItem();
        sc.supprimer(orderSelectionne.getOrderId());
       orderTab.refresh();
        // Mise à jour de la liste des utilisateurs après suppression
        ObservableList<ShoppingCart> observableUserList = FXCollections.observableList(sc.recuperer());
        orderTab.setItems(observableUserList);
        // Réinitialisation de la sélection de la ListView
      orderTab.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        nomcol.clear();
        prenomcol.clear();
        villecol.clear();
        adressecol.clear();
        postaleCol.clear();
        etaCol.clear();
        PriceCol.clear();



    }
    @FXML
    void clearbtn(ActionEvent event) {
        orderTab.getSelectionModel().clearSelection();
        // Réinitialisation des champs de texte et de la sélection de la ChoiceBox
        nomcol.clear();
        prenomcol.clear();
        villecol.clear();
        adressecol.clear();
        postaleCol.clear();
        etaCol.clear();
        dateCol.clear();
        PriceCol.clear();
    }

    @FXML
    void retour(ActionEvent event) {

    }
    int i;
    ShoppingCart shop=new ShoppingCart();
ShoppingCartService sc =new ShoppingCartService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ObservableList<ShoppingCart> observableUserList = FXCollections.observableList(sc.recuperer());
            orderTab.setItems(observableUserList);
            orderTab.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            orderTab.setCellFactory(new Callback<ListView<ShoppingCart>, ListCell<ShoppingCart>>() {
                @Override
                public ListCell<ShoppingCart> call(ListView<ShoppingCart> listView) {
                    return new ListCell<ShoppingCart>() {
                        @Override
                        protected void updateItem(ShoppingCart shoppingCart, boolean empty) {
                            super.updateItem(shoppingCart, empty);
                            if (shoppingCart != null) {
                                setText(shoppingCart.getOrderId() + " - "+shoppingCart.getNom() + " - " + shoppingCart.getPrenom() + " - " + shoppingCart.getVille() + " - " + shoppingCart.getAdresse() + " - " + shoppingCart.getCode_postale() + " - " + shoppingCart.getOrderDate() + " - " + shoppingCart.getSta());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            orderTab.setOnMouseClicked(event -> {
                ShoppingCart orderSelectionne = orderTab.getSelectionModel().getSelectedItem();
                if (orderSelectionne != null) {

                    nomcol.setText(orderSelectionne.getNom());
                    prenomcol.setText(orderSelectionne.getPrenom());
                    villecol.setText(orderSelectionne.getVille());
                    adressecol.setText(orderSelectionne.getAdresse());
                    postaleCol.setText(Integer.toString(orderSelectionne.getCode_postale()));

                    dateCol.setText(dateToString(orderSelectionne.getOrderDate()));
                    PriceCol.setText(Float.toString(orderSelectionne.getTotal_price()));
                    etaCol.setText(orderSelectionne.getSta());
                    i=orderSelectionne.getOrderId();
                    shop=orderSelectionne;
                    System.out.println(i);

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    @FXML
    void recupererbtn(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("getProducts.fxml"));
        Parent root = loader.load();


        GetProducts controller =loader.getController();
        controller.sc= i;

       orderTab.getScene().setRoot(root);
    }
    @FXML
    void imprimerbtn(ActionEvent event) {

        System.out.println("asslema");
        ShoppingCartService as = new ShoppingCartService();
        try {
            //   as.createPDF(carts_table);
            as.createPDF(shop);
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
    }
    }


