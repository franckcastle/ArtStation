package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utils.MyDB;

public class ProduitService  {
    Connection cnx;
    public ProduitService() {
        cnx = MyDB.getInstance().getCnx();
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
    public List<Produit> getByPrice() throws SQLException {
        List<Produit> produits = getAll();
        Collections.sort(produits, Comparator.comparing(Produit::getPrix));
        return produits;
    }
    public List<Produit> getByName() throws SQLException {
        List<Produit> produits = getAll();
        Collections.sort(produits, Comparator.comparing(Produit::getNom));
        return produits;
    }

    public List<Produit> recuperer() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String s = "select * from Produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Produit p = new Produit();
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setPrix( rs.getFloat("prix"));
            p.setImage(rs.getString("image"));
            p.setQte_stock(rs.getInt("qte_stock"));
            p.setId_ctg(rs.getInt("id_ctg"));
            p.setID_produit(rs.getInt("Id_produit"));
            produits.add(p);
        }
        return produits;
    }

   public void supprimer(Produit t) throws SQLException {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Produit> getByorderId(int i) throws SQLException {


        List<Produit> prod = new ArrayList<>();
        String sql = "SELECT produit.nom, cartitem.price, cartitem.quantity " +
                "FROM cartitem " +
                "JOIN produit ON produit.ID_produit = cartitem.id " +
                "WHERE cartitem.orderId = ?";;
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, i);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    String nom = rs.getString("nom");

                    Float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Produit item = new Produit( quantity,nom,price);
                    prod.add(item);
                }
            }
        }
        return prod;


    }
    public boolean supprimerProduit(Integer ID_produit) {
        String sql = "delete from produit where ID_produit=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID_produit);
            ste.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false ;

    }
       public void modifier(Produit p) {
            String query = "UPDATE  produit set nom=?,description=?,prix=?,qte_stock=? Where ID_produit ='" + p.getID_produit() + "'";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setString(1, p.getNom());
                ste.setString(2, p.getDescription());
                ste.setFloat(3, p.getPrix());
                ste.setInt(4, p.getQte_stock());
                ste.executeUpdate();
                System.out.println("nom produit modifié  ");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    public Produit rechercheProduit (int ID_produit) throws SQLException, ParseException {
        Produit p = new Produit();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM statut  where ID_produit ="+ID_produit);
            while(result.next()) {
                p.setID_produit(result.getInt(1));
                p.setNom(result.getString(2));
                p.setDescription(result.getString(3));
                p.setPrix(result.getFloat(4));
                p.setQte_stock(result.getInt(5));
                p.setId_ctg(result.getInt(6));
                p.setImage(result.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

/*    public void modifier(String nom, String description, float prix , int qte_stock) throws SQLException, ParseException {
        String req = "UPDATE produit SET nom = ?,description = ?,prix=?,qte_stock = ? where  = ? "  ;
        PreparedStatement ste = cnx.prepareStatement(req);

        ste.setString(1,nom);
        ste.setString(2,description);
        ste.setFloat(3, prix);
        ste.setInt(4, qte_stock);
        ste.executeUpdate();
        System.out.println("Le produit a été modifié avec succés !");

    }*/
    }



