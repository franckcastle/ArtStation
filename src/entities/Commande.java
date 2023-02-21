package entities;
import java.util.Date;


public class Commande {

    private int ID_commande ;
    private Date date ;
    private float prix ;

    public Commande() {
    }

    public Commande(int ID_commande, Date date, float prix) {
        this.ID_commande = ID_commande;
        this.date = date;
        this.prix = prix;
    }

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "ID_commande=" + ID_commande +
                ", date=" + date +
                ", prix=" + prix +
                '}';
    }
}
