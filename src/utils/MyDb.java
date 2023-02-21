package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDb {
    String url = "jdbc:mysql://localhost:3306/art";
    String username = "root";
    String password = "";

    Connection cnx;
    private static MyDb instance;

    public MyDb() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyDb getInstance() {
        if (instance == null) {
            instance = new MyDb();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }


}
