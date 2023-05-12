package entities;
import java.util.Date;
public class Workshop {


    private int id, duree, nb_places	;
    private float prix;
    private Date date;
    private String titre, nom_artiste, description, heure_debut, heure_fin,categorie,image;

    public Workshop() {
    }

    public Workshop(String titre) {
        this.titre=titre;
    }


    public Workshop(int id, int duree, int nb_places, float prix, String titre, String nom_artiste, String categorie) {
        this.id = id;
        this.duree = duree;
        this.nb_places = nb_places;
        this.prix = prix;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.categorie = categorie;
    }


    public Workshop( int duree, int nb_places, float prix, String titre, String nom_artiste, String categorie) {

        this.duree = duree;
        this.nb_places = nb_places;
        this.prix = prix;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.categorie = categorie;
    }

    public Workshop(int id, int duree, float prix, String titre, String nom_artiste, String description, String image, Date date) {
        this.id = id;
        this.duree = duree;
        this.prix = prix;

        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;

        this.image=image;
        this.date=date;
    }



    public Workshop(int id, int duree, float prix,  String titre, String nom_artiste, String description, String heure_debut, String heure_fin, String image, Date date) {
        this.id = id;
        this.duree = duree;
        this.prix = prix;

        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.image=image;
        this.date=date;
    }




    public Workshop(int id, int duree, int nb_places	, float prix, Date date, String titre, String nom_artiste, String description, String categorie,String image) {
        this.id = id;
        this.duree = duree;
        this.nb_places	 = nb_places	;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;

        this.heure_fin = heure_fin;
        this.categorie=categorie;
        this.image=image;

    }


    public Workshop( int duree, int nb_places	, float prix, Date date, String titre, String nom_artiste, String description, String categorie,String image) {
        this.id = id;
        this.duree = duree;
        this.nb_places	 = nb_places	;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;

        this.categorie=categorie;
        this.image=image;

    }


    public Workshop(int id, int duree, int nb_places	, float prix, Date date, String titre, String nom_artiste, String description, String heure_debut, String heure_fin, String categorie,String image) {
        this.id = id;
        this.duree = duree;
        this.nb_places	 = nb_places	;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.categorie=categorie;
        this.image=image;

    }

    public Workshop(int duree, int nb_places	, float prix, Date date, String titre, String nom_artiste, String description, String heure_debut, String heure_fin,String categorie, String image) {
        this.duree =duree;
        this.nb_places	 = nb_places	;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.categorie=categorie;
        this.image=image;

    }

    public Workshop(String titre, String description, int duree, String nom_artiste, String heure_debut, String heure_fin, int prix,String image) {
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.duree =duree;
        this.titre = titre;
        this.prix=prix;
        this.image=image;

    }

    public Workshop(int id, int duree, float prix, Date date, String titre, String nom_artiste, String description, String heure_debut, String heure_fin, String image) {
        this.id = id;
        this.duree = duree;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.image = image;
    }

    public Workshop(float prix, Date date, String titre, String nom_artiste) {
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.nom_artiste = nom_artiste;
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

    public int getNbPlaces	() {
        return nb_places	;
    }

    public void setNbPlaces	(int nb_places	) {
        this.nb_places	 = nb_places	;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", duree=" + duree +
                ", nb_places	=" + nb_places	 +
                ", prix=" + prix +
                ", date=" + date +
                ", titre='" + titre + '\'' +
                ", nom_artiste='" + nom_artiste + '\'' +
                ", description='" + description + '\'' +
                ", heure_debut='" + heure_debut + '\'' +
                ", heure_fin='" + heure_fin + '\'' +
                ", categorie='" + categorie+ '\'' +
                '}';
    }
}
