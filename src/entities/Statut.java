package entities;

import com.mysql.cj.jdbc.CallableStatement;
import org.w3c.dom.Text;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Statut {

     int id_s;
    private String contenu;
    private String titre;
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

    public int getId_s() {
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {

        this.updated = updated;
    }





    @Override
    public String toString() {
        return "Statut{" +
                "id_s=" + id_s +
                ", contenu='" + contenu + '\'' +
                ", titre='" + titre + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
