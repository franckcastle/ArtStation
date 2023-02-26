package entities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Evenement {
    private int id,evaluation;
    private String titre,description,localisation;
    private Date dateDebut,dateFin;
    private Float prix;

    private byte[] image;
    private int nbPlace;
    //jointure
    private List<Reservation> reservations;

    public Evenement(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    //jointure avec createur(user)
    //liste des participant (user[])
    //private Categorie categorie;


    public Evenement(int id, int evaluation, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, byte[] image, int nbPlace) {
        this.id = id;
        this.evaluation = evaluation;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.image = image;
        this.nbPlace = nbPlace;
    }

    public Evenement() {
    }

    public Evenement(int evaluation, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, int nbPlace) {
        this.evaluation = evaluation;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.nbPlace = nbPlace;
    }

    public Evenement(int evaluation, String titre, String description, String localisation, Float prix, int nbPlace) {
        this.evaluation = evaluation;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.nbPlace = nbPlace;
    }

    public int getId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }


    public BufferedImage getImage() {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(this.image);
            return ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setImage(BufferedImage image) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            this.image = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", evaluation=" + evaluation +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix +
                ", image=" + Arrays.toString(image) +
                ", nbPlace=" + nbPlace +
                '}';
    }
}

