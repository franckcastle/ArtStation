package entities;

public class Produit {
    private int ID_produit, qte_stock;
    private String nom,description,image;
    private Float prix;


    public Produit() {
    }

    public Produit(int ID_produit, int qte_stock, String nom, String description, String image, Float prix) {
        this.ID_produit = ID_produit;
        this.qte_stock = qte_stock;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit(int qte_stock, String nom, String description, String image, Float prix) {
        this.qte_stock = qte_stock;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public int getID_produit() {
        return ID_produit;
    }

    public void setID_produit(int ID_produit) {
        this.ID_produit = ID_produit;
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

    @Override
    public String toString() {
        return "Produit{" +
                "ID_produit=" + ID_produit +
                ", qte_stock=" + qte_stock +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                '}';
    }
}
