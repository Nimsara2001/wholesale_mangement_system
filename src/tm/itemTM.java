package tm;

public class itemTM {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
    private double discRate;

    public itemTM() {
    }

    public itemTM(String code, String description, String packSize, double unitPrice, int qtyOnHand, double discRate) {
        this.itemCode = code;
        this.description = description;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
