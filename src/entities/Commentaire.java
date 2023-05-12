package entities;

import java.util.Date;
import java.util.Objects;

public class Commentaire {
    private String username;
    private int id;
    private int id_s_id;
    private String description;
    private static Date date_ajout;
    private Statut statut;


    public Commentaire(){
    }


    public Commentaire(int id_s_id, String description) {
        this.id_s_id = id_s_id;
        this.description = description;
    }

    public int getId_s_id() {
        return id_s_id;
    }

    public void setId_s_id(int id_s_id) {
        this.id_s_id = id_s_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public static Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return id == that.id && id_s_id == that.id_s_id && Objects.equals(description, that.description) && Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_s_id, description, statut);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", id=" + id_s_id +
                ", description='" + description + '\'' +
                ", statut=" + statut +
                '}';
    }
}
