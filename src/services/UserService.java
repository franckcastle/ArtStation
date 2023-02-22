package services;

import entities.User;
import utils.MyDb;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {
    Connection cnx;
    public UserService() {
        cnx = MyDb.getInstance().getCnx();
    }

    @Override
    public void ajouter(User u) throws SQLException {
        String req = "INSERT INTO user(userId,userName,password,email,role)Values("
                + u.getUserId() + ",'" + u.getUsername() + "','" + u.getPassword() + "','"
                + u.getEmail() + "','" + u.getRole() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("done");
    }

    @Override
    public void modifer(User u) throws SQLException {
        String req = "UPDATE user SET userName=?,password=?,email=?,role=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,u.getUsername());
        ps.setString(2,u.getPassword());
        ps.setString(3,u.getEmail());
        ps.setString(4, u.getRole());
        ps.executeUpdate();
    }

    @Override
    public boolean supprimer(User u) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from user where userId= ?");
            req.setInt(1,u.getUserId());
            req.executeUpdate();
            ok=true;
        }catch (SQLException ex){
            System.out.println("error in delete" + ex.getMessage());
        }
        return ok;
    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String s = "Select * from user";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()){
            User u = new User();
            u.setUsername(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));
            users.add(u);
        }
        return users;
    }
}
