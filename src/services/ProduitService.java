package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;

public class ProduitService implements IService<Produit> {
    Connection cnx;
    public ProduitService() {
        cnx = MyDb.getInstance().getCnx();
    }

    @Override
    public void ajouter(Produit t) throws SQLException {
        String req = "INSERT INTO produit(nom,description,prix,image,qte_stock,id_ctg) VALUES("
                + "'" + t.getNom() + "','" + t.getDescription() + "','" + t.getPrix() + "','" + t.getImage() + "','"+ t.getQte_stock() + "',"+ t.getId_ctg()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("done");


    }
}
