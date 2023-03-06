package services;

import entities.Rating;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;

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
}
