package entities;

public class Produit {
    private int id, qte_stock, id_ctg_id, rating;
    private String nom,description,image;
    private Float prix;


    public Produit() {
    }

    public Produit(int id, String nom, String description, String image, Float prix, int qte_stock, int id_ctg_id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.id_ctg_id = id_ctg_id;

    }

    public Produit(int id, int qte_stock, int id_ctg_id, int rating, String nom, String description, String image, Float prix) {
        this.id = id;
        this.qte_stock = qte_stock;
        this.id_ctg_id = id_ctg_id;
        this.rating = rating;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit(String nom, String description, String image, Float prix, int qte_stock, int id_ctg_id) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.id_ctg_id = id_ctg_id;
    }


    public Produit(int qte_stock, String nom, Float prix) {
        this.qte_stock = qte_stock;
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public int getId_ctg_id() {
        return id_ctg_id;
    }

    public void setId_ctg_id(int id_ctg_id) {
        this.id_ctg_id = id_ctg_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", qte_stock=" + qte_stock +
                ", id_ctg_id=" + id_ctg_id +
                ", rating=" + rating +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                '}';
    }
}
