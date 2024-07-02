package model;

public class Customer {
    private String CustomerId;
    private String CustomerTitle;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerCity;
    private String CustomerProvince;
    private String CustomerPostalCode;

    public Customer() {
    }

    public Customer(String customerId, String customerTitle, String customerName,
                    String customerAddress, String customerCity, String customerProvince,
                    String customerPostalCode) {
        CustomerId = customerId;
        CustomerTitle = customerTitle;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        CustomerCity = customerCity;
        CustomerProvince = customerProvince;
        CustomerPostalCode = customerPostalCode;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerTitle() {
        return CustomerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        CustomerTitle = customerTitle;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getCustomerProvince() {
        return CustomerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        CustomerProvince = customerProvince;
    }

    public String getCustomerPostalCode() {
        return CustomerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        CustomerPostalCode = customerPostalCode;
    }
}
