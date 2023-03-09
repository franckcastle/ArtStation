package entities;

public class Remise {
    private int code ;
    private String owner;
    private int nb;

    @Override
    public String toString() {
        return "Remise{" +
                "code=" + code +
                ", owner='" + owner + '\'' +
                ", nb=" + nb +
                '}';
    }

    public Remise() {
    }

    public Remise(int code, String owner) {
        this.code = code;
        this.owner = owner;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Remise(int code, String owner, int nb) {
        this.code = code;
        this.owner = owner;
        this.nb = nb;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
