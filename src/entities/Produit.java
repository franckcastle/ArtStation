/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Skander
 */
public class Produit {
    
    private int id;
    private float prix;
    private int qte_stock,Cat_id;
    private String nom,description,image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }

    public int getCat_id() {
        return Cat_id;
    }

    public void setCat_id(int cat_id) {
        Cat_id = cat_id;
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

    public Produit() {
    }

    public Produit(int id, float prix, int qte_stock, int cat_id, String nom, String description, String image) {
        this.id = id;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.Cat_id = cat_id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Produit(float prix, int qte_stock, String nom) {
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.nom = nom;
    }

    public Produit(float prix, int qte_stock, int cat_id, String nom, String description, String image) {
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.Cat_id = cat_id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", prix=" + prix +
                ", qte_stock=" + qte_stock +
                ", Cat_id=" + Cat_id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}

   
    
    
    
    
    

