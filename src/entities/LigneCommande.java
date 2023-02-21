package entities;

public class LigneCommande {

    private int Id_ligne , qte_commande;

    public int getId_ligne() {
        return Id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        Id_ligne = id_ligne;
    }

    public int getQte_commande() {
        return qte_commande;
    }

    public void setQte_commande(int qte_commande) {
        this.qte_commande = qte_commande;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "Id_ligne=" + Id_ligne +
                ", qte_commande=" + qte_commande +
                '}';
    }

    public LigneCommande(int id_ligne, int qte_commande) {
        Id_ligne = id_ligne;
        this.qte_commande = qte_commande;


    }
}
