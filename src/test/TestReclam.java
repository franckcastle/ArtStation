package test;

import entities.Reclamation;
import services.ReclamService;

import java.sql.SQLException;

public class TestReclam {
    public static void main(String[] args) throws SQLException {
        ReclamService rs = new ReclamService();
        rs.recuperer();
    }

}
