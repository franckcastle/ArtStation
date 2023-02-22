package entities;
import java.util.Date;
public class Workshop {


    private int id, duree, nbPlaces;
    private float prix;
    private Date date;
    private String titre, nom_artiste, description, heure_debut, heure_fin;

    public Workshop() {
    }

    public Workshop(int id, int duree, int nbPlaces, float prix, Date date, String titre, String nom_artiste, String description, String heure_debut, String heure_fin) {
        this.id = id;
        this.duree = duree;
        this.nbPlaces = nbPlaces;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    public Workshop(int duree, int nbPlaces, float prix, Date date, String titre, String nom_artiste, String description, String heure_debut, String heure_fin) {
        this.duree =duree;
        this.nbPlaces = nbPlaces;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", duree=" + duree +
                ", nbPlaces=" + nbPlaces +
                ", prix=" + prix +
                ", date=" + date +
                ", titre='" + titre + '\'' +
                ", nom_artiste='" + nom_artiste + '\'' +
                ", description='" + description + '\'' +
                ", heure_debut='" + heure_debut + '\'' +
                ", heure_fin='" + heure_fin + '\'' +
                '}';
    }
}