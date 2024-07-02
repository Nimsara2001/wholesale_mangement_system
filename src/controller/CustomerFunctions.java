package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerFunctions {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) ;
    public boolean deleteCustomer(String id) ;
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() ;
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

}
