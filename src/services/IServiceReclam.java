package services;


import java.sql.SQLException;
import java.util.List;

public interface IServiceReclam <T>{
    void ajouter(T t) throws SQLException;
    void modifer(T t) throws SQLException;
    boolean supprimer(T t) throws SQLException;
    List<T> recuperer() throws SQLException;
}
