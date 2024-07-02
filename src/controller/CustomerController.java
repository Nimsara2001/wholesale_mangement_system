package controller;

import db.DbConnection;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerFunctions {

    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)");
        stm.setObject(1,c.getCustomerId());
        stm.setObject(2,c.getCustomerTitle());
        stm.setObject(3,c.getCustomerName());
        stm.setObject(4,c.getCustomerAddress());
        stm.setObject(5,c.getCustomerCity());
        stm.setObject(6,c.getCustomerPostalCode());
        stm.setObject(7,c.getCustomerPostalCode());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer c) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer WHERE CustID='" + id + "'");
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() {
        return null;
    }

    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
}
