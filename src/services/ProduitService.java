/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CartItem;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class ProduitService implements IService<Produit> {

    Connection cnx;

    public ProduitService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Produit t) throws SQLException {
        String req = "INSERT INTO Produit(nom,description,prix,image,qte_stock,Cat_id) VALUES("
                + "'" + t.getNom() + "','" + t.getDescription() + "','" + t.getPrix() + "','" + t.getImage() + "','" + t.getQte_stock() + "'," + t.getCat_id() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Produit t) throws SQLException {
        String req = "UPDATE Produit SET nom = ?,desciption = ?,prix = ? ,image = ?,qte_stock = ? ,Cat_id = ?where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getDescription());
        ps.setFloat(3, t.getPrix());
        ps.setInt(4, t.getId());
        ps.setInt(5, t.getQte_stock());
        ps.setInt(6, t.getCat_id());
        ps.setInt(7, t.getId());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(Produit t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> recuperer() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String s = "select * from Produit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Produit p = new Produit();
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setPrix(rs.getInt("prix"));
            p.setImage(rs.getString("image"));
            p.setQte_stock(rs.getInt("qte_stock"));
            p.setCat_id(rs.getInt("Cat_id"));
            p.setId(rs.getInt("id"));
            produits.add(p);
        }
        return produits;
    }

    @Override
    public Produit getById(int i) throws SQLException {
        String req = "SELECT * FROM produit WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, i);
        ResultSet rs = ps.executeQuery();
        Produit p = new Produit();
        while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
            p.setPrix(rs.getFloat("prix"));
            p.setImage(rs.getString("image"));
            p.setQte_stock(rs.getInt("qte_stock"));
            p.setCat_id(rs.getInt("Cat_id"));

        }
return p;


    }

    @Override
    public List<Produit> getByorderId(int i) throws SQLException {


            List<Produit> prod = new ArrayList<>();
            String sql = "SELECT produit.nom, cartitem.price, cartitem.quantity " +
                    "FROM cartitem " +
                    "JOIN produit ON produit.id = cartitem.id " +
                    "WHERE cartitem.orderId = ?";;
            try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                stmt.setInt(1, i);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {

                        String nom = rs.getString("nom");

                        Float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        Produit item = new Produit( price,quantity,nom);
                       prod.add(item);
                    }
                }
            }
            return prod;


        }
    }