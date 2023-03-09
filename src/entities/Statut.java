package entities;

import java.sql.Date;


public class Statut {

    int id_s;
    private String titre;
    private String contenu;
    private String username;



    private int nbrLike  ;
    private Date created;
    private Date updated;


    //jointure avec table user pour recuperer le nom d'auteur
    public Statut(String titre, String contenu){
        this.titre = titre;
        this.contenu = contenu;
    }

    public Statut() {

    }

    public Statut(int id_s, String contenu, String titre) {
        this.id_s = id_s;
        this.contenu = contenu;
        this.titre = titre;
    }

    public Statut(int id_s, String contenu, String titre, Date created, Date updated) {
        this.id_s = id_s;
        this.contenu = contenu;
        this.titre = titre;
        this.created = created;
        this.updated = updated;
    }

    public Statut(int id_s, String titre, String contenu, int nbrlike, Date created, Date updated) {
        this.id_s = id_s;
        this.titre = titre;
        this.contenu = contenu;
        this.nbrLike = nbrlike;
        this.created = created;
        this.updated = updated;
    }

    public int getNbrLike() {
        return nbrLike;
    }


    public  int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public  Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {

        this.updated = updated;
    }

    public  void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Statut(int id_s, String titre, String contenu, String username, int nbrLike, Date created, Date updated) {
        this.id_s = id_s;
        this.titre = titre;
        this.contenu = contenu;
        this.username = username;
        this.nbrLike = nbrLike;
        this.created = created;
        this.updated = updated;
    }
}