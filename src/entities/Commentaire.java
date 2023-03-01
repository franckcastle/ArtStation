package entities;

import java.awt.desktop.UserSessionEvent;
import java.util.Date;

public class Commentaire {
    private int id_c ;
    private String description;
    private Date date_ajout;
    private Statut statut;






    public Commentaire(){
    }

    public Commentaire(int id_c, String description, Statut statut ) {
        this.id_c = id_c;
        this.description = description;
        this.statut = statut;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
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

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id_c=" + id_c +
                ", description='" + description + '\'' +
                ", date_ajout=" + date_ajout +
                ", statut=" + statut +
                '}';
    }
}
