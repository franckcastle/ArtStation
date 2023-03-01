package services;
import entities.ShoppingCart;
import utils.MyDB;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService implements SService<ShoppingCart> {
    Connection cnx;

    public ShoppingCartService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(ShoppingCart s) throws SQLException {

            String req = "INSERT INTO shoppingcart(nom,prenom,ville,Adresse,code_postale,Total_price) VALUES("
                    + "'" + s.getNom() + "','" + s.getPrenom() + "','" + s.getVille()+ "','"+s.getAdresse()+ "','"  + s.getCode_postale() +"'," +s.getTotal_price()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
    }

    @Override
    public void modifier(ShoppingCart s) throws SQLException {

        String req = "UPDATE shoppingcart SET nom = ?,prenom = ?,ville = ? ,Adresse = ?,code_postale = ?,sta = ? where orderId ='" + s.getOrderId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getNom());
            ps.setString(2, s.getPrenom());
            ps.setString(3, s.getVille());

            ps.setString(4, s.getAdresse());
            ps.setInt(5, s.getCode_postale());
            ps.setString(6, s.getSta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }




    @Override
    public  boolean supprimer(ShoppingCart t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from shoppingcart where orderId= ? ");
            req.setInt(1, t.getOrderId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }




@Override
    public List<ShoppingCart> recuperer() throws SQLException {
        List<ShoppingCart> ShoppingCarts = new ArrayList<>();
        String s = "select * from shoppingcart";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){


                ShoppingCart c = new ShoppingCart();
            c.setOrderId(rs.getInt("orderId"));
           c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setVille(rs.getString("ville"));
            c.setAdresse(rs.getString("Adresse"));
            c.setCode_postale(rs.getInt("code_postale"));
            c.setOrderDate(rs.getDate("orderDate"));
            c.setTotal_price(rs.getInt("Total_price"));
            c.setSta(rs.getString("sta"));
            ShoppingCarts.add(c);

        }
        return ShoppingCarts;
    }

    @Override
    public List<ShoppingCart> getById(int id) throws SQLException {
        List<ShoppingCart> ShoppingCarts = new ArrayList<>();
       // String req = "Select * from shoppingcart where orderId = ?";
        String req = "Select * from shoppincart where orderId = ?";


        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery(req);

        while(rs.next()){
            ShoppingCart c = new ShoppingCart();
            c.setOrderId(rs.getInt("orderId"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setVille(rs.getString("ville"));
            c.setAdresse(rs.getString("Adresse"));
            c.setCode_postale(rs.getInt("code_postale"));
            c.setOrderDate(rs.getDate("orderDate"));
            c.setTotal_price(rs.getInt("Total_price"));
            c.setSta(rs.getString("sta"));
            ShoppingCarts.add(c);

        }
        return ShoppingCarts;
    }


}
