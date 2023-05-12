package services;


import entities.Categorie;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
                    (rs.getInt("id"), rs.getString("Nom_ctg"));

            cat.add(c);
            System.out.println();
        }
        return cat;
    }

    public void ajouterCategorie(Categorie c) throws SQLException, Exception {
        String sql = "INSERT INTO categorie(nom_ctg)VALUES (?)";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, c.getNom_ctg());
        pstmt.executeUpdate();
    }

  public int GetIdByName(String name) throws SQLException {
        String req = "SELECT id FROM categorie where nom_ctg='"+name+"';";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        int i= rs.getInt("id");

        return i;

    }

    public boolean supprimerCategorie(Integer id) {
        String sql = "delete from categorie where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
    public void modifierCategorie(Categorie c ) {
        String query = "UPDATE  categorie set nom_ctg=? Where id ='" + c.getId() + "'";
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
