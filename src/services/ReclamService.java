package services;

import entities.Reclamation;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamService implements IServiceReclam<Reclamation>{
    Connection cnx;
    public ReclamService() {
        cnx = MyDb.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {

            String req = "INSERT INTO reclamation (userId, sujet, plainte) VALUES (?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setInt(1, reclamation.getUserId());
            statement.setString(2, reclamation.getSujet());
            statement.setString(3, reclamation.getPlainte());
            statement.executeUpdate();
        System.out.println("reclamation ajouté");
    }

    @Override
    public void modifer(Reclamation reclamation) throws SQLException {
        String req = "UPDATE reclamation SET sujet = ?,plainte = ? where reclamationId = ?";
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, reclamation.getSujet());
        statement.setString(2, reclamation.getPlainte());
        statement.setInt(3, reclamation.getReclamationId());
        statement.executeUpdate();
        System.out.println("reclamation modifié");
    }

    @Override
    public boolean supprimer(Reclamation reclamation) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from reclamation where reclamationId= ?");
            req.setInt(1,reclamation.getReclamationId());
            req.executeUpdate();
            ok=true;
        }catch (SQLException ex){
            System.out.println("error in delete" + ex.getMessage());
        }
        return ok;
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String s = "select * from reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setReclamationId(rs.getInt("reclamationId"));
            r.setUserId(rs.getInt("userId"));
            r.setSujet(rs.getString("sujet"));
            r.setPlainte(rs.getString("plainte"));

            reclamations.add(r);

        }
        return reclamations;
    }
}