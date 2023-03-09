package services;

import entities.Remise;
import entities.ShoppingCart;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemiseService implements RInterface<Remise> {

    Connection cnx;

    public RemiseService() {
        cnx = MyDb.getInstance().getCnx();
    }

    @Override
    public void increment(Remise remise) throws SQLException {
        String req = "UPDATE remise SET nb = ? where code ='" + remise.getCode() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, remise.getNb()+1);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouter(Remise r) throws SQLException {
        String req = "INSERT INTO remise(code,owner) VALUES("
                + r.getCode() + ", '" + r.getOwner() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Remise remise) throws SQLException {

        String req = "UPDATE remise SET owner = ?,nb = ? where code ='" + remise.getCode() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, remise.getOwner());
            ps.setInt(2, remise.getNb());


            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }


    @Override
    public boolean supprimer(int i) throws SQLException {

            boolean ok = false;
            try {
                PreparedStatement req = cnx.prepareStatement("delete from remise where code= ? ");
                req.setInt(1, i);
                req.executeUpdate();
                ok = true;
            } catch (SQLException ex) {
                System.out.println("error in delete " + ex);
            }
            return ok;
        }


    @Override
    public List<Remise> recuperer() throws SQLException {
        List<Remise> remises = new ArrayList<>();
        String s = "select * from remise";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){


            Remise c = new Remise();
            c.setCode(rs.getInt("code"));
            c.setOwner(rs.getString("owner"));

            c.setNb(rs.getInt("nb"));



           remises.add(c);

        }
        return remises;
    }


    @Override
    public Remise getById(int i) throws SQLException {

        String req = "SELECT * FROM remise WHERE code = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, i);
        ResultSet rs = ps.executeQuery();
        Remise rem = new Remise();
        while (rs.next()) {
            rem.setCode(rs.getInt("code"));
            rem.setOwner(rs.getString("owner"));
            rem.setNb(rs.getInt("nb"));
        }
        return rem;
    }
}
