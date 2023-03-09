package entities;

public class Rating {
    private int id, rating, id_produit;


    public Rating() {
    }

    public Rating(int id, int rating, int id_produit) {
        this.id = id;
        this.rating = rating;
        this.id_produit = id_produit;
    }

    public Rating(int rating, int id_produit) {
        this.rating = rating;
        this.id_produit = id_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", id_produit=" + id_produit +
                '}';
    }
}
