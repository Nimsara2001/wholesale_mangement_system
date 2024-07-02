package controller;

import db.DbConnection;
import model.Item;

import javax.swing.text.TableView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemFunctions {



    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO Item VALUES(?,?,?,?,?,?)");
        stm.setObject(1, i.getItemCode());
        stm.setObject(2, i.getDescription());
        stm.setObject(3, i.getPackSize());
        stm.setObject(4, i.getUnitPrice());
        stm.setObject(5, i.getQtyOnHand());
        stm.setObject(6, i.getDiscRate());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean modifyItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=?,DiscRate=? WHERE ItemCode=?");
        stm.setObject(1,i.getDescription());
        stm.setObject(2,i.getPackSize());
        stm.setObject(3,i.getUnitPrice());
        stm.setObject(4,i.getQtyOnHand());
        stm.setObject(5,i.getDiscRate());
        stm.setObject(6,i.getItemCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean removeItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("DELETE FROM Item WHERE ItemCode='" + code + "' ");
        return stm.executeUpdate() > 0;
    }

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE ItemCode='" + code + "'");
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            ));
        }
        return items;
    }

    @Override
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM Item").executeQuery();
            List<String> ids=new ArrayList<>();
            while (rst.next()){
                ids.add(rst.getString(1));
            }
            return ids;
    }
}


