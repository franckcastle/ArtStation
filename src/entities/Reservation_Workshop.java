package entities;

public class Reservation_Workshop {
    private int id,id_workshop,id_user;

    public Reservation_Workshop() {

    }

    public Reservation_Workshop(int id, int id_workshop, int id_user) {
        this.id = id;
        this.id_workshop = id_workshop;
        this.id_user = id_user;
    }

    public Reservation_Workshop(int id_workshop, int id_user) {
        this.id_workshop = id_workshop;
        this.id_user = id_user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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





}
