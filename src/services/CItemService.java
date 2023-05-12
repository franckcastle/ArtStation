package services;

import entities.CartItem;
import entities.ShoppingCart;
import utils.MyDb;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CItemService implements ItemService<CartItem> {
    Connection cnx;

    public CItemService() {
        cnx = MyDb.getInstance().getCnx();
    }

    @Override
    public void ajouter(CartItem t) throws SQLException {
        String req = "INSERT INTO cartitem(panier_id,produit_id,quantity,price) VALUES("
                + "'" + t.getOrderId() + "','" + t.getId() + "','" + t.getQuantity() + "',"+ t.getPrice()  + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    @Override
    public void modifier(CartItem t) throws SQLException {
        String req = "UPDATE cartitem SET panier_id = ?,produit_id= ?,quantity = ?,price = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getOrderId());
        ps.setInt(2, t.getId());
        ps.setInt(3, t.getQuantity());
        ps.setFloat(4, t.getPrice());
        ps.setInt(5, t.getCart_id());
        ps.executeUpdate();

    }
    public void supprimer(int cart_id) {
        String sql = "delete from cartitem where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,cart_id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<CartItem> recuperer() throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String s = "select * from cartitem";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            CartItem p = new CartItem();

            p.setCart_id(rs.getInt("id"));
            p.setOrderId(rs.getInt("panier_id"));
            p.setId(rs.getInt("produit_id"));
            p.setQuantity(rs.getInt("quantity"));
            p.setPrice(rs.getFloat("price"));
            cartItems.add(p);

        }
        return cartItems;
    }

    @Override
    public List<CartItem> getById(int i) throws SQLException {
        List<CartItem> CartItems = new ArrayList<>();
        String req = "SELECT * FROM cartitem WHERE panier_id = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, i);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            CartItem c = new CartItem();

            c.setCart_id(rs.getInt("id"));
            c.setId(rs.getInt("produit_id"));
            c.setOrderId(rs.getInt("panier_id"));
            c.setQuantity(rs.getInt("quantity"));
            c.setPrice(rs.getFloat("price"));
            c.setTotal(rs.getFloat("total"))   ;
            CartItems.add(c);
System.out.print(CartItems);
        }
        return CartItems;

        }
    }


