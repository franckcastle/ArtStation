package entities;

public class CartItem {
    private int cart_id;
    private int orderId;
    private int id;
    private int quantity;
    private Float price;
    private Float total;

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id=" + cart_id +
                ", orderId=" + orderId +
                ", id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }

    public CartItem(int cart_id, int orderId, int id, int quantity, Float price, Float total) {
        this.cart_id = cart_id;
        this.orderId = orderId;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public CartItem(int cart_id, int orderId, int id, int quantity, Float price) {
        this.cart_id = cart_id;
        this.orderId = orderId;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
