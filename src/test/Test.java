package test;

import java.sql.SQLException;
import entities.Produit;
import services.ProduitService;
import utils.MyDb;

public class Test {
    public static void main(String[] args) {

        try {
            Produit p = new Produit(4,"tableau","piece unique","osjsia",22.0f,11);
            ProduitService ps = new ProduitService();
            ps.ajouter(p);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
