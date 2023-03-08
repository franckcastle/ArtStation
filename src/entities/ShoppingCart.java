package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

    private int orderId;
    private String nom,prenom,ville,Adresse;
    private  int code_postale ;
    private Date orderDate;
    private float Total_price;
    private String sta ;
    private List<CartItem> orderItems = new ArrayList<>();

    public ShoppingCart(String nom, String prenom, String ville, String adresse, int code_postale, float total_price) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        Adresse = adresse;
        this.code_postale = code_postale;
        Total_price = total_price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(float total_price) {
        Total_price = total_price;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public List<CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(String nom, String prenom, String ville, String adresse, int code_postale, float total_price, String sta) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        Adresse = adresse;
        this.code_postale = code_postale;
        Total_price = total_price;
        this.sta = sta;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "orderId=" + orderId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ville='" + ville + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", code_postale=" + code_postale +
                ", orderDate=" + orderDate +
                ", Total_price=" + Total_price +
                ", sta='" + sta + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
