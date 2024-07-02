package model;

public class ItemDetails {
    private String itemCode;
    private double unitPrice;
    private int qtyForSell;
    private double discount;
    private double totalItemCost;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, double unitPrice, int qtyForSell, double discount,double totalItemCost) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qtyForSell = qtyForSell;
        this.discount = discount;
        this.totalItemCost=totalItemCost;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(double totalItemCost) {
        this.totalItemCost = totalItemCost;
    }
}
