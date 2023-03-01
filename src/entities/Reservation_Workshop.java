package entities;

public class Reservation_Workshop {
    private int id_reservation,id_workshop,id_user;
    private String categorie;

    public Reservation_Workshop() {

    }

    public Reservation_Workshop(int id_reservation, int id_workshop, int id_user, String categorie) {
        this.id_reservation = id_reservation;
        this.id_workshop = id_workshop;
        this.id_user = id_user;
        this.categorie=categorie;
    }

    public Reservation_Workshop(int id_workshop, int id_user, String categorie) {
        this.id_workshop = id_workshop;
        this.id_user = id_user;
        this.categorie=categorie;
    }





    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_workshop() {
        return id_workshop;
    }

    public void setId_workshop(int id_workshop) {
        this.id_workshop = id_workshop;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
