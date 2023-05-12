package services;
import com.itextpdf.text.DocumentException;

import entities.ShoppingCart;

import utils.MyDb;


import com.itextpdf.text.Document;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService implements SService<ShoppingCart> {
    Connection cnx;

    public ShoppingCartService() {
        cnx = MyDb.getInstance().getCnx();
    }
    @Override
    public void ajouter(ShoppingCart s) throws SQLException {

            String req = "INSERT INTO shoppingcart(nom,prenoom,ville,Adresse,code_postale,Total_price) VALUES("
                    + "'" + s.getNom() + "','" + s.getPrenom() + "','" + s.getVille()+ "','"+s.getAdresse()+ "','"  + s.getCode_postale() +"'," +s.getTotal_price()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
    }

    @Override
    public void modifier(ShoppingCart s) throws SQLException {

        String req = "UPDATE shoppingcart SET nom = ?,prenoom = ?,ville = ? ,Adresse = ?,code_postale = ?,Total_price = ? ,sta = ? where id ='" + s.getOrderId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getNom());
            ps.setString(2, s.getPrenom());
            ps.setString(3, s.getVille());

            ps.setString(4, s.getAdresse());
            ps.setInt(5, s.getCode_postale());
            ps.setFloat(6, s.getTotal_price());
            ps.setString(7, s.getSta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static Document createPDF(ShoppingCart s) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell("Nom:");
        table.addCell(s.getNom());

        table.addCell("Prenom:");
        table.addCell(s.getPrenom());

        table.addCell("Ville:");
        table.addCell(s.getVille());

        table.addCell("Adresse:");
        table.addCell(s.getAdresse());

        table.addCell("Code Postal:");
        table.addCell(Integer.toString(s.getCode_postale()));

        table.addCell("Total Price:");
        table.addCell(Float.toString(s.getTotal_price()));

        document.add(table);
        document.close();

        return document;
    }

    @Override
    public  boolean supprimer(int t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from shoppingcart where id= ? ");
            req.setInt(1, t);
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
           c.setOrderId(rs.getInt("id"));
           c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenoom"));
            c.setVille(rs.getString("ville"));
            c.setAdresse(rs.getString("Adresse"));
            c.setCode_postale(rs.getInt("code_postale"));
            c.setTotal_price(rs.getInt("Total_price"));
        //    c.setOrderDate(rs.getDate("order_date"));
            c.setSta(rs.getString("sta"));
            ShoppingCarts.add(c);

        }
        return ShoppingCarts;
    }

    @Override
    public List<ShoppingCart> getById(int id) throws SQLException {
        List<ShoppingCart> ShoppingCarts = new ArrayList<>();
       // String req = "Select * from shoppingcart where orderId = ?";
        String req = "Select * from shoppincart where id = ?";


        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery(req);

        while(rs.next()){
            ShoppingCart c = new ShoppingCart();
            c.setOrderId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenoom"));
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
