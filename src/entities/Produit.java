package entities;

public class Produit {
    private int ID_produit, qte_stock, id_ctg;
    private String nom,description,image;
    private Float prix;


    public Produit() {
    }

    public Produit(int ID_produit, String nom, String description, String image, Float prix,int qte_stock, int id_ctg) {
        this.ID_produit = ID_produit;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.id_ctg=id_ctg;
    }

    public Produit( String nom, String description, String image, Float prix,int qte_stock, int id_ctg) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.id_ctg=id_ctg;
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

    public int getId_ctg() {
        return id_ctg;
    }

    public void setId_ctg(int id_ctg) {
        this.id_ctg = id_ctg;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "ID_produit=" + ID_produit +
                ", qte_stock=" + qte_stock +
                ", id_ctg=" + id_ctg +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                '}';
    }
}
