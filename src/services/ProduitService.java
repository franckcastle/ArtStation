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
        String sql = "INSERT INTO produit (nom,description,image,prix,qte_stock,id_ctg) VALUES (?, ?, ?, ?,?,?)";
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
        List<Produit> listProd = new ArrayList<Produit>();
        String req = "select * from produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Produit p = new Produit
                    (rs.getInt("ID_produit"),
                            rs.getString("nom"),
                            rs.getString("description"),
                            rs.getString("image"),
                            rs.getFloat("prix"),
                            rs.getInt("qte_stock"),
                            rs.getInt("id_ctg"));

            listProd.add(p);
            System.out.println(p);
        }
        return listProd;
    }

   /* public List<Produit> recuperer() throws SQLException {
        List<Produit> listProduit = new ArrayList<Produit>();
        String s = "select * from produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Produit p = new Produit();
            p.setNom (rs.getString("nom"));
            p.setDescription(rs.getDescription("description"));
            p.setImage(rs.getImage("image"));
            p.setPrix(rs.getPrix("prix"));
            p.setQte_stock(rs.getQte_stock("qte_stock"));
            p.setId_ctg(rs.getId_ctg("Id_ctg"));
            listeProduit.add(p);
        }
        return listProduit;
    }
*/
    public boolean supprimerProduit(Integer ID_produit) {
        String sql = "delete from produit where ID_produit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID_produit);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false ;

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



