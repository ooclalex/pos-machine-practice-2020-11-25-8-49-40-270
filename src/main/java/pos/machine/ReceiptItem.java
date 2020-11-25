package pos.machine;

public class ReceiptItem {
    private int price, quantity, subtotal;
    private String name, barcode;

    public ReceiptItem(int price, int quantity, String name, String barcode) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.barcode = barcode;
    }

    public void addQuantity() {
        quantity++;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getSubtotal() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
