//package services;
//
//import entities.User;
//import utils.MyDB;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserService implements IServiceUser<User> {
//    Connection cnx;
//    public UserService() {
//        cnx = MyDB.getInstance().getCnx();
//    }
//
//    @Override
//    public void ajouter(User u) throws SQLException {
//        String req = "INSERT INTO user(userId,userName,password,email,role)Values("
//                + u.getUserId() + ",'" + u.getUsername() + "','" + u.getPassword() + "','"
//                + u.getEmail() + "','" + u.getRole() + "')";
//        Statement st = cnx.createStatement();
//        st.executeUpdate(req);
//        System.out.println("done");
//    }
//
//    @Override
//    public void modifer(String s,String s1,String s2,String s3,String s4) throws SQLException {
//        String req = "UPDATE user SET userName=?,password=?,email=?,role=? where username = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setString(1,s);
//        ps.setString(2,s1);
//        ps.setString(3,s2);
//        ps.setString(4, s3);
//        ps.setString(5, s4);
//        ps.executeUpdate();
//    }
//
//    @Override
//    public boolean supprimer(User u) throws SQLException {
//        boolean ok = false;
//        try {
//            PreparedStatement req = cnx.prepareStatement("delete from user where username= ?");
//            req.setString(1,u.getUsername());
//            req.executeUpdate();
//            ok=true;
//        }catch (SQLException ex){
//            System.out.println("error in delete" + ex.getMessage());
//        }
//        return ok;
//    }
//
//    @Override
//    public List<User> recuperer() throws SQLException {
//        List<User> users = new ArrayList<>();
//        String s = "Select * from user";
//        Statement st = cnx.createStatement();
//        ResultSet rs = st.executeQuery(s);
//        while (rs.next()){
//            User u = new User();
//            u.setUsername(rs.getString("userName"));
//            u.setPassword(rs.getString("password"));
//            u.setEmail(rs.getString("email"));
//            u.setRole(rs.getString("role"));
//            users.add(u);
//        }
//        return users;
//    }
//
//    @Override
//    public User GetByUsername(String username) throws SQLException {
//        List<User> users = new ArrayList<>();
//        String req = "Select * from user where username = ?";
//
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setString(1,username);
//        ResultSet rs = ps.executeQuery();
//        User u = new User();
//        rs.next();
//        if (rs.getRow()!=0){
//            u.setUserId(rs.getInt(1));
//            u.setUsername(username);
//            u.setPassword(rs.getString(3));
//            u.setEmail(rs.getString(4));
//            u.setRole(rs.getString(5));
//
//            return u;
//        }
//        else {
//            System.out.println("Utilisateur inexistant");
//            return null;
//        }
//
//    }
//}