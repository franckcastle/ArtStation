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
        String req = "INSERT INTO user(userName,password,email,tel,role)Values('"+ u.getUsername() + "','" + u.getPassword() + "','"
                + u.getEmail() +"',"+u.getTel()+ ",'" + u.getRole() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifer(String s,String s1,String s2,int s3,String s4,String s5) throws SQLException {
        String req = "UPDATE user SET userName=?,password=?,email=?,tel=?,role=? where username = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,s);
        ps.setString(2,s1);
        ps.setString(3,s2);
        ps.setInt(4, s3);
        ps.setString(5, s4);
        ps.setString(6, s5);
        ps.executeUpdate();
    }

    @Override
    public boolean supprimer(User u) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from user where username= ?");
            req.setString(1,u.getUsername());
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
            u.setUserId(rs.getInt("userId"));
            u.setUsername(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setTel(rs.getInt("tel"));
            u.setRole(rs.getString("role"));
            users.add(u);
        }
        return users;
    }

    @Override
    public User GetByUsername(String username) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "Select * from user where username = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            User u = new User();
            rs.next();
            if (rs.getRow()!=0){
                u.setUserId(rs.getInt(1));
                u.setUsername(username);
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setTel(rs.getInt(5));
                u.setRole(rs.getString(6));

                return u;
            }
            else {
                System.out.println("Utilisateur inexistant");
                return null;
        }

    }

    @Override
    public List<User> GetByRole(String role) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "Select * from user where role = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,role);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            User u = new User();
            u.setUserId(rs.getInt("userId"));
            u.setUsername(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setTel(rs.getInt("tel"));
            u.setRole(rs.getString("role"));
            users.add(u);
        }
        return users;
    }

    public User GetByMail(String email) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "Select * from user where email = ?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        User u = new User();
        rs.next();
        if (rs.getRow() != 0) {
            u.setUserId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setEmail(email);
            u.setUserId(rs.getInt(5));
            u.setRole(rs.getString(6));

            return u;
        } else {
            System.out.println("Utilisateur inexistant");
            return null;
        }
    }


    public void updatePwd(String email,String newPassword) throws SQLException {
        String req = "UPDATE user SET password=? where email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,newPassword);
        ps.setString(2,email);

        ps.executeUpdate();
    }


}
