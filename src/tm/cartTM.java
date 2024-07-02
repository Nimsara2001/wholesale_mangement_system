package tm;

public class cartTM {
    private String itemCode;
    private String description;
    private int qty;
    private double unitPrice;
    private double discount;
    private double totalPrice;

    public cartTM() {
    }

    public cartTM(String itemCode, String description, int qty, double unitPrice, double discount, double totalPrice) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "cartTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
