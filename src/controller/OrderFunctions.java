package controller;

import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;
import tm.cartTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderFunctions {
    public String getOrderId() throws SQLException, ClassNotFoundException;
    public boolean placeOrder(Order order);
    public boolean saveOrderDetails(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException;
    public boolean updateItemQtyOnHand(String itemCode, int qty) throws SQLException, ClassNotFoundException;
    public List<String> getOrderIdsForCustomerId(String cusId) throws SQLException, ClassNotFoundException;
    public String getSelectedOrderCustId(String orderId) throws SQLException, ClassNotFoundException;
    public ObservableList<cartTM> getSelectedOrderItems(String orderId) throws SQLException, ClassNotFoundException;
    public double getTotalCostOfOrder (String orderId) throws SQLException, ClassNotFoundException;
    public boolean updateOrderMgt(Order order);
    public boolean updateOrderDetailsMgt(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException;
    public int getItemQtyOnHand(String itemCode) throws SQLException, ClassNotFoundException;
    public boolean deleteOrder(String orderId) throws SQLException, ClassNotFoundException;
    public List<String> getOrderIds() throws SQLException, ClassNotFoundException;

}
