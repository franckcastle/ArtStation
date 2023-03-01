package services;

import entities.Categorie;
import utils.MyDB;

import java.sql.*;
import java.util.List;

public class CategorieService implements CService<Categorie> {
    Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Categorie c) throws SQLException {
        String req = "INSERT INTO categorie (nom) VALUES('" + c.getNom() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Categorie c) throws SQLException {
        String req = "UPDATE Personne SET nom = ? where Cat_id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getNom());
        ps.setInt(2, c.getCat());
        ps.executeUpdate();
    }



    @Override
    public void supprimer(Categorie c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> recuperer(Categorie c) throws SQLException {
        return null;
    }


}
