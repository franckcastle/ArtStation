package services;

import java.sql.SQLException;
import java.util.List;

public interface SService <T>{
    public void ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public boolean supprimer(T t) throws SQLException;
    public List<T> recuperer() throws SQLException;
    public List<T> getById(int i)  throws SQLException;
}
