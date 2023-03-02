package gui;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import services.ProduitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class ModiffierPdtController implements Initializable {


    @FXML
    public TextField nomTf;
    @FXML
    public TextField descriptionTf;
    @FXML
    public TextField prixTf;
    @FXML
    public TextField qte_stockTf;
    @FXML
    public Button ModifieProduit;

    ProduitService ps = new ProduitService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Produit p;



    public ModiffierPdtController () {
    }

    public ModiffierPdtController (Produit p) {
        this.p=p;

    }




    @FXML
    public void initialize() {
        // Set the text of the text fields with the values of the 'p' object
        nomTf.setText(p.getNom());
        descriptionTf.setText(p.getDescription());
        prixTf.setText(Float.toString(p.getPrix()));
        qte_stockTf.setText(Integer.toString(p.getQte_stock()));
    }





    @FXML
    void modifier(ActionEvent event) {

        if(!nomTf.getText().isEmpty()){
            Produit pp = new Produit();
            pp.setID_produit(p.getID_produit());
            pp.setNom(nomTf.getText());
            pp.setDescription(descriptionTf.getText());
            pp.setPrix(Float.valueOf(prixTf.getText()));
            pp.setQte_stock(Integer.parseInt(qte_stockTf.getText()));
            ps.modifier(pp);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ");
            alert.setHeaderText("toutes les champs sont obligatoires");

            alert.showAndWait();
        }



    /*    System.out.println(p);
        String nom = nomTf.getText();
        String description = descriptionTf.getText();
        float prix = Float.parseFloat(prixTf.getText());
        int qte_stock = Integer.parseInt(qte_stockTf.getText());
        try {
            ps.modifier(nom,description,prix,qte_stock);
            Parent loader = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
            nomTf.getScene().setRoot(loader);*/
            /*descriptionTf.getScene().setRoot(loader);
            prixTf.getScene().setRoot(loader);
            qte_stockTf.getScene().setRoot(loader);*/


    /*    }catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException | ParseException ex) {
            throw new RuntimeException(ex);
        }*/

    }


    public void setProduit(Produit p) {
        this.p=p;
    }
}
