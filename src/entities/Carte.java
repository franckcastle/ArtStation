package entities;

public class Carte {

private int Num_carte, code ;
private float montant_commande,  solde;
private Date Date_reg;
private String type_carte;

    public Carte(int num_carte, int code, float montant_commande, float solde, Date date_reg, String type_carte) {
        Num_carte = num_carte;
        this.code = code;
        this.montant_commande = montant_commande;
        this.solde = solde;
        Date_reg = date_reg;
        this.type_carte = type_carte;
    }

    public int getNum_carte() {
        return Num_carte;
    }

    public void setNum_carte(int num_carte) {
        Num_carte = num_carte;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getMontant_commande() {
        return montant_commande;
    }

    public void setMontant_commande(float montant_commande) {
        this.montant_commande = montant_commande;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Date getDate_reg() {
        return Date_reg;
    }

    public void setDate_reg(Date date_reg) {
        Date_reg = date_reg;
    }

    public String getType_carte() {
        return type_carte;
    }

    public void setType_carte(String type_carte) {
        this.type_carte = type_carte;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "Num_carte=" + Num_carte +
                ", code=" + code +
                ", montant_commande=" + montant_commande +
                ", solde=" + solde +
                ", Date_reg=" + Date_reg +
                ", type_carte='" + type_carte + '\'' +
                '}';
    }
}
