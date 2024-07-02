package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;
import tm.cartTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController implements OrderFunctions{

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId += 1;
            if (tempId <= 9) {
                return "O-00"+tempId;
            }else if(tempId<=99) {
                return "O-0" + tempId;
            }else {
                return "O-"+tempId;
            }
        }else {
            return  "O-001";
        }
    }

    @Override
    public boolean placeOrder(Order order) {
        Connection con=null;
        try {
            con=DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?)");
            stm.setObject(1,order.getOrderId());
            stm.setObject(2,order.getCustomerId());
            stm.setObject(3,order.getOrderDate());
            stm.setObject(4,order.getOrderTime());
            stm.setObject(5,order.getCost());

            if (stm.executeUpdate()>0){
                if (saveOrderDetails(order.getOrderId(),order.getItems())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:items
        ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("  INSERT INTO OrderDetail VALUES(?,?,?,?,?)");
            stm.setObject(1,orderId);
            stm.setObject(2,temp.getItemCode());
            stm.setObject(3,temp.getQtyForSell());
            stm.setObject(4,temp.getDiscount());
            stm.setObject(5,temp.getTotalItemCost());

            if (stm.executeUpdate()>0){
                if (updateItemQtyOnHand(temp.getItemCode(),temp.getQtyForSell())){
                }else {
                    return false;
                }

            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateItemQtyOnHand(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Item SET QtyOnHand=QtyOnHand-'"+qty+"' WHERE ItemCode='"+itemCode+"'");

        return stm.executeUpdate()>0;
    }

    @Override
    public List<String> getOrderIdsForCustomerId(String cusId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT OrderID FROM Orders WHERE CustID='" + cusId + "'");
        ResultSet rst = stm.executeQuery();
        List<String> idList = new ArrayList<>();
        while (rst.next()){
            idList.add(rst.getString(1));
        }
        return idList;
    }

    @Override
    public String getSelectedOrderCustId(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT CustID FROM Orders WHERE OrderID='" + orderId + "'");
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            return rst.getString("CustID");
        }
        return null;
    }

    @Override
    public ObservableList<cartTM> getSelectedOrderItems(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT OrderDetail.ItemCode,Item.Description ,OrderDetail.OrderQTY,\n" +
                        "Item.UnitPrice,OrderDetail.Discount,OrderDetail.ItemCost FROM OrderDetail \n" +
                        "INNER JOIN Item ON OrderDetail.ItemCode=Item.ItemCode WHERE OrderID='"+orderId+"'");
        ResultSet rst = stm.executeQuery();
        ObservableList<cartTM> orderItems = FXCollections.observableArrayList();
        while (rst.next()){
            orderItems.add(new cartTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
            ));
        }
        return orderItems;
    }

    @Override
    public double getTotalCostOfOrder(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT TotalCost FROM Orders WHERE OrderID='" + orderId + "' ");
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            return rst.getDouble("TotalCost");
        }
        return 0.00;
    }

    @Override
    public boolean updateOrderMgt(Order order) {
        Connection con=null;
        try {
            con=DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("UPDATE Orders SET OrderDate=? , OrderTime=? ,TotalCost=? WHERE OrderID=?");
            stm.setObject(1,order.getOrderDate());
            stm.setObject(2,order.getOrderTime());
            stm.setObject(3,order.getCost());
            stm.setObject(4,order.getOrderId());

            if (stm.executeUpdate()>0){
                if (updateOrderDetailsMgt(order.getOrderId(),order.getItems())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean updateOrderDetailsMgt(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:items
        ) {
            PreparedStatement stmD = DbConnection.getInstance().getConnection().
                    prepareStatement("DELETE FROM OrderDetail WHERE OrderID='"+orderId+"'");

            if (stmD.executeUpdate()>0 & saveOrderDetails(orderId,items)){
                if (updateItemQtyOnHand(temp.getItemCode(),temp.getQtyForSell())){

                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemQtyOnHand(String itemCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT QtyOnHand FROM Item WHERE ItemCode='" + itemCode + "'");
        ResultSet rst = stm.executeQuery();
        int qty=0;
        while (rst.next()){
            qty=rst.getInt("QtyOnHand");
        }
        return qty;
    }

    @Override
    public boolean deleteOrder(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT ItemCode,OrderQTY  FROM OrderDetail WHERE OrderID='" + orderId + "'");
        ResultSet rst = stm.executeQuery();
        boolean updateQty=true;
        System.out.println(rst.next());
        while (rst.next()){
            PreparedStatement stmUpdate= DbConnection.getInstance().getConnection().
                    prepareStatement("UPDATE Item SET QtyOnHand=QtyOnHand+'"+rst.getInt("OrderQTY")+"' WHERE ItemCode='"+rst.getString("ItemCode")+"'");
            updateQty= stmUpdate.executeUpdate() > 0;
            System.out.println(stmUpdate.executeUpdate()>0);
            System.out.println(rst.getString("ItemCode"));
            System.out.println(rst.getInt("OrderQTY"));

        }
        if (updateQty){
            PreparedStatement stmOrder = DbConnection.getInstance().getConnection().
                    prepareStatement("DELETE FROM Orders WHERE OrderID='" + orderId + "'");
            System.out.println(stmOrder.executeUpdate()>0);
            return stmOrder.executeUpdate()>0 ;
        }
        return false;
    }

    @Override
    public List<String> getOrderIds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT OrderID FROM Orders");
        ResultSet rst = stm.executeQuery();
        List<String> OIds = new ArrayList<>();
        while (rst.next()){
            OIds.add(rst.getString(1));
        }
        return OIds;
    }

}
