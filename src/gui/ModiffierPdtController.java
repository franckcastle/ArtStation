package gui;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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


    String nom, description;
    String n, d;
    float prix;
    float p;
    int qte_stock;
    int q;
    int ID_produit;
    ProduitService ps = new ProduitService();

    public ModiffierPdtController () {
    }

    public ModiffierPdtController (String nom , String description, float prix , int qte_stock) {
        this.ID_produit=ID_produit;
        this.nom=nom;
        this.description=description;
        this.prix=prix;
        this.qte_stock=qte_stock;
    }

    public void setNom (String nom) {
        nomTf.setText(nom);
        n = nomTf.getText();
    }

    public void setDescription (String description) {
        descriptionTf.setText(description);
        d = descriptionTf.getText();
    }

    public void setPrix (float prix) {
        prixTf.setText(String.valueOf(prix));
        p = Float.parseFloat(prixTf.getText());
    }

    public void setQte_stock (int qte_stock) {
        qte_stockTf.setText(String.valueOf(qte_stock));
        q = Integer.parseInt(qte_stockTf.getText());
    }



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


    @FXML
    void modifier(ActionEvent event) {
        String nom = nomTf.getText();
        String description = descriptionTf.getText();
        float prix = Float.parseFloat(prixTf.getText());
        int qte_stock = Integer.parseInt(qte_stockTf.getText());
        try {
            ps.modifier(nom,description,prix,qte_stock);
            Parent loader = FXMLLoader.load(getClass().getResource("AffichagePdt.fxml"));
            nomTf.getScene().setRoot(loader);
            /*descriptionTf.getScene().setRoot(loader);
            prixTf.getScene().setRoot(loader);
            qte_stockTf.getScene().setRoot(loader);*/


        }catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException | ParseException ex) {
            throw new RuntimeException(ex);
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
