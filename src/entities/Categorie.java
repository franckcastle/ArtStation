package entities;

public class Categorie {

 private int Cat_ID;
 private String nom ;

 public Categorie(int cat_ID, String nom) {
  Cat_ID = cat_ID;
  this.nom = nom;
 }

 public int getCat_ID() {
  return Cat_ID;
 }

 public void setCat_ID(int cat_ID) {
  Cat_ID = cat_ID;
 }

 public String getNom() {
  return nom;
 }

 public void setNom(String nom) {
  this.nom = nom;
 }

 @Override
 public String toString() {
  return "Categorie{" +
          "Cat_ID=" + Cat_ID +
          ", nom='" + nom + '\'' +
          '}';
 }
}
