package gui;

import entities.Produit;
import entities.Categorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.ProduitService;
import services.CategorieService;
import java.lang.Integer;



public class AjouterPdtController  implements Initializable {

    @FXML
    private ComboBox comb;
    @FXML
    public TextField nomTf;
    @FXML
    public TextField descriptionTf;
    @FXML
    public TextField prixTf;
    @FXML
    public TextField qte_stockTf;

    @FXML
    public TextField imageTf;

    @FXML
    public Button retour;

    // instance database Service
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Categorie> cat = cs.getAll();
            ObservableList<String> catNames = FXCollections.observableArrayList();
            for (Categorie c : cat) {
                catNames.add(c.getNom_ctg());}
                comb.setItems(catNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        @FXML
      public void AjouterProduit (ActionEvent event) throws SQLException {

            Produit p = new Produit();
            p.setNom(nomTf.getText());
            p.setDescription (descriptionTf.getText());
            p.setPrix(Float.valueOf(prixTf.getText()));
            p.setQte_stock(Integer.parseInt(qte_stockTf.getText()));
            p.setId_ctg(i);
            p.setImage(imageTf.getText());
            try {

                reset();
                if (p.getNom().length()==0 || p.getDescription().length()==0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur lors del'ajout du produit");
                    alert.setHeaderText("Veuillez remplir les champs afficher.");
                    alert.showAndWait();
                    return;
                }
                ps.ajouterProduit(p);
                System.out.println("L'ajout du produit est effectuer avec succès");

            } catch (Exception ex) {
                System.out.println("error" + ex.getMessage());
            }

        }
    @FXML
    public void retour(ActionEvent event){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherPdt.fxml"));

            //Parent root = loader.load();
            nomTf.getScene().setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
            int i;
            @FXML
            void select (ActionEvent event) throws SQLException {
        String s = comb.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
                List<Categorie> categories = cs.getAll();
                for (Categorie c : categories) {
                    if (c.getNom_ctg().equals(s)) {
                       int categoryId = c.getId_ctg();

                        i=categoryId;
                        break; }
                }

            }

            private void reset() {
                nomTf.setText("");
                descriptionTf.setText("");
                prixTf.setText("");
                qte_stockTf.setText("");
                imageTf.setText("");
            }

}


















