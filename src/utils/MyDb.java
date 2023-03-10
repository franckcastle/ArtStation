package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDb {
    private String url = "jdbc:mysql://localhost:3306/art";
    private String user ="root";
    private String password ="";
    private Connection cnx;
    private static MyDb instance;
    public MyDb (){
        try {
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static MyDb getInstance (){
        if (instance == null){
            instance = new MyDb();
        }
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
}
