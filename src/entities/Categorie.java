package entities;

import java.util.Objects;

public class Categorie {

 private int id_ctg;
 private String nom_ctg ;

 public Categorie(int id_ctg, String nom_ctg) {
  this.id_ctg = id_ctg;
  this.nom_ctg = nom_ctg;
 }

 public Categorie(String nom_ctg) {
  this.nom_ctg = nom_ctg;
 }

 public Categorie() {

 }

 public int getId_ctg() {
  return id_ctg;
 }

 public void setId_ctg(int id_ctg) {
  this.id_ctg = id_ctg;
 }


 public String getNom_ctg() {
  return nom_ctg;
 }

 public void setNom_ctg(String nom_ctg) {
 this.nom_ctg = nom_ctg;
 }

 @Override
 public String toString() {
  return "Categorie{" +
          "id_ctg=" + id_ctg +
          ", nom_ctg='" + nom_ctg + '\'' +
          '}';
 }


}
