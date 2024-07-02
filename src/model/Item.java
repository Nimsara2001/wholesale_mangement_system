package model;

public class Item {
    private String itemCode;
    private String Description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
    private double discRate;

    public Item() {
    }

    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, double discRate) {
        this.itemCode = itemCode;
        Description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.discRate = discRate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getDiscRate() {
        return discRate;
    }

    public void setDiscRate(double discRate) {
        this.discRate = discRate;
    }

    @Override
    public String toString() {
        return "item{" +
                "itemCode='" + itemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", discRate=" + discRate +
                '}';
    }
}
