package services;

import java.sql.SQLException;
import java.util.List;

public interface RInterface<T> {

    public void increment(T t) throws SQLException;
    public void ajouter(T t) throws SQLException;

    public void modifier(T t) throws SQLException;
    public boolean supprimer(int t) throws SQLException;
    public List<T> recuperer() throws SQLException;
    public T getById(int i) throws SQLException;
}
