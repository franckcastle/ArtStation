package test;

import entities.Reclamation;
import services.ReclamService;

import java.sql.SQLException;

public class TestReclam {
    public static void main(String[] args) throws SQLException {
        Reclamation reclamation = new Reclamation(21,"skander","plainte");
        ReclamService rs = new ReclamService();
        rs.supprimer(reclamation);
    }

}
