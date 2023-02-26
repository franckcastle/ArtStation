package entities;

public class Reclamation {
    private int reclamationId,userId;
    private String sujet,plainte;

    public Reclamation() {
    }

    public Reclamation(int userId, String sujet, String plainte) {
        this.userId = userId;
        this.sujet = sujet;
        this.plainte = plainte;
    }

    public Reclamation(int reclamationId, int userId, String sujet, String plainte) {
        this.reclamationId = reclamationId;
        this.userId = userId;
        this.sujet = sujet;
        this.plainte = plainte;
    }

    public int getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getPlainte() {
        return plainte;
    }

    public void setPlainte(String plainte) {
        this.plainte = plainte;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "reclamationId=" + reclamationId +
                ", userId=" + userId +
                ", sujet='" + sujet + '\'' +
                ", plainte='" + plainte + '\'' +
                '}';
    }
}
