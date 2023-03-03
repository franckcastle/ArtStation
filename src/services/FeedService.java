package services;

import entities.Feedback;
import entities.Reclamation;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedService implements IFeed<Feedback> {
    Connection cnx;
    public FeedService(){cnx = MyDb.getInstance().getCnx();}
//
    @Override
    public void ajouter(Feedback feedback) throws SQLException {
        String req = "INSERT INTO feedback ( text,id_Ev) VALUES (?,?)";
        PreparedStatement statement = cnx.prepareStatement(req);

        statement.setString(1, feedback.getText());
        statement.setInt(2,feedback.getId_Ev());


        statement.executeUpdate();
        System.out.println("reclamation ajouté");
    }

    @Override
    public void modifer(Feedback f) throws SQLException {
        String query = "UPDATE  feedback set text=? Where id ='" + f.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, f.getText());
            ste.executeUpdate();
            System.out.println("feedback modifié  ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public boolean supprimer(Feedback feedback) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from feedback where id= ?");
            req.setInt(1,feedback.getId());
            req.executeUpdate();
            ok=true;
        }catch (SQLException ex){
            System.out.println("error in delete" + ex.getMessage());
        }
        return ok;
    }

    @Override
    public List<Feedback> recuperer() throws SQLException {
        List<Feedback> feedback = new ArrayList<>();
        String s = "select * from feedback";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Feedback f = new Feedback();
            f.setId(rs.getInt("id"));

            f.setText(rs.getString("text"));


            feedback.add(f);

        }
        return feedback;
    }
}
