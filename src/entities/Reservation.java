package entities;

import java.util.Date;

public class Reservation {
    private int id;
    //jointure
    private Evenement evenement;
    //jointure avec user
    private User user;
    public Reservation(int id, Evenement evenement) {
        this.id = id;
        this.evenement = evenement;
    }

    public Reservation(int id, Evenement evenement, User user) {
        this.id = id;
        this.evenement = evenement;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", evenement=" + evenement +
                '}';
    }
}
