package services;


import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Produit;
import utils.MyDb;
import java.text.ParseException;


public class CategorieService {

    Connection cnx;
    public CategorieService() {
        cnx = MyDb.getInstance().getCnx();
    }

    public List<Categorie> getAll() throws SQLException {
        List<Categorie> cat = new ArrayList<Categorie>();
        String req = "select * from categorie";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Categorie c = new Categorie
                    (rs.getInt("Id_ctg"), rs.getString("Nom_ctg"));

            cat.add(c);
            System.out.println();
        }
        return cat;
    }

    public void ajouterCategorie(Categorie c) throws SQLException, Exception {
        String sql = "INSERT INTO categorie(id_ctg,nom_ctg)VALUES (?,?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setInt(1, c.getId_ctg());
        pstmt.setString(2, c.getNom_ctg());
        pstmt.executeUpdate();
    }

  public int GetIdByName(String name) throws SQLException {
        String req = "SELECT id_ctg FROM categorie where nom_ctg='"+name+"';";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        int i= rs.getInt("id_ctg");

        return i;

    }

    public void supprimerCategorie(Integer id_ctg) {
        String sql = "delete from categorie where id_ctg=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id_ctg);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifierCategorie(Categorie c ) {
        String query = "UPDATE  categorie set nom_ctg=? Where id_ctg ='" + c.getId_ctg() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, c.getNom_ctg());
            ste.executeUpdate();
            System.out.println("categorie  modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
