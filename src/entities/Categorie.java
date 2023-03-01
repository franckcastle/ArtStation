package entities;

public class Categorie {
    int Cat;
    String nom ;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public int getCat() {
        return Cat;
    }

    public void setCat(int cat) {
        Cat = cat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
