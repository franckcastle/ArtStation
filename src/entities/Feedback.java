package entities;

public class Feedback {
    private int id,id_Ev;
    private String text;
    Evenement event;

    public Feedback(int id, String text, Evenement event) {
        this.id = id;
        this.text = text;
        this.event = event;
    }

    public Feedback(int id, int id_Ev, String text, Evenement event) {
        this.id = id;
        this.id_Ev = id_Ev;
        this.text = text;
        this.event = event;
    }

    public int getId_Ev() {
        return id_Ev;
    }

    public void setId_Ev(int id_Ev) {
        this.id_Ev = id_Ev;
    }

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", id_Ev=" + id_Ev +
                ", text='" + text + '\'' +
                ", event=" + event +
                '}';
    }
}
