package entities;

import java.sql.Date;


public class Statut {

    int id;
    private String titre;
    private String contenu;
    private String username;



    private int nbr_like;
    private Date created;
    private Date updated;


    //jointure avec table user pour recuperer le nom d'auteur
    public Statut(String titre, String contenu){
        this.titre = titre;
        this.contenu = contenu;
    }

    public Statut() {

    }

    public Statut(int id, String contenu, String titre) {
        this.id = id;
        this.contenu = contenu;
        this.titre = titre;
    }

    public Statut(int id, String contenu, String titre, Date created, Date updated) {
        this.id = id;
        this.contenu = contenu;
        this.titre = titre;
        this.created = created;
        this.updated = updated;
    }

    public Statut(int id, String titre, String contenu, int nbrlike, Date created, Date updated) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.nbr_like = nbrlike;
        this.created = created;
        this.updated = updated;
    }

    public int getNbr_like() {
        return nbr_like;
    }


    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public  void setNbr_like(int nbr_like) {
        this.nbr_like = nbr_like;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Statut(int id, String titre, String contenu, String username, int nbr_like, Date created, Date updated) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.username = username;
        this.nbr_like = nbr_like;
        this.created = created;
        this.updated = updated;
    }
}