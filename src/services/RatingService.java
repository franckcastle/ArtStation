package services;

import entities.Categorie;
import entities.Produit;
import entities.Rating;
import utils.MyDb;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RatingService {

    Connection cnx;
    public RatingService() {
        cnx = MyDb.getInstance().getCnx();
    }

    public void rateProduit(int id_produit, int rating) {
        // Update the rating value in the database
        String query ="INSERT INTO rating (id_produit, rating) VALUES (?, ?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, id_produit);
            ste.setInt(2, rating);
            ste.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Rating> getAll() throws SQLException {
        List<Rating> raating = new ArrayList<Rating>();
        String req = "select * from rating";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            System.out.println(rs);
            Rating c = new Rating
                    (rs.getInt("id"), rs.getInt("rating"),rs.getInt("id_produit"));

            raating.add(c);
            System.out.println();
        }
        return raating;
    }

}
