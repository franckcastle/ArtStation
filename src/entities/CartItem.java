package entities;

public class CartItem {
    private int cart_id;
    private int orderId;
    private int id;
    private int quantity;
    private Float price;

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id=" + cart_id +
                ", orderId=" + orderId +
                ", id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public CartItem() {
    }

    public CartItem(int orderId, int id, int quantity, float price) {
        this.orderId = orderId;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(int cart_id, int orderId, int id, int quantity, float price) {
        this.cart_id = cart_id;
        this.orderId = orderId;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(int quantity, float price) {
        this.quantity = quantity;
        this.price = price;
    }
}
