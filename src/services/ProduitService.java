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
import java.text.ParseException;

public class ProduitService  {
    Connection cnx;
    public ProduitService() {
        cnx = MyDb.getInstance().getCnx();
    }


    public void ajouterProduit(Produit p) throws SQLException, Exception {
        String sql = "INSERT INTO produit (nom,description,image,prix,qte_stock,id_ctg) VALUES (?, ?, ?, ?,?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, p.getNom());
        pstmt.setString(2, p.getDescription());
        pstmt.setString(3, p.getImage());
        pstmt.setFloat(4, p.getPrix());
        pstmt.setInt(5, p.getQte_stock());
        pstmt.setInt(6,p.getId_ctg());
        ;
        pstmt.executeUpdate();
    }

    public List<Produit> getAll() throws SQLException {
        List<Produit> listProduit = new ArrayList<Produit>();
        String req = "select * from produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Produit p = new Produit(rs.getInt("ID_produit"), rs.getString("nom"), rs.getString("description"), rs.getString("image"), rs.getFloat("prix"), rs.getInt("qte_stock"), rs.getInt("id_ctg"));

            listProduit.add(p);
        }
        return listProduit;
    }

    public void supprimerProduit(Integer ID_produit) {
        String sql = "delete from produit where ID_produit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID_produit);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        public void modifierProduit(Produit p) {
            String query = "UPDATE  produit set nom=? Where ID_produit ='" + p.getID_produit() + "'";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setString(1, p.getNom());
                ste.executeUpdate();
                System.out.println("nom produit modifié  ");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }



