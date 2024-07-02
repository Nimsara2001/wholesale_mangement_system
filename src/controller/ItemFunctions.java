package controller;

import model.Item;

import javax.swing.text.TableView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemFunctions {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean modifyItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean removeItem(String code) throws SQLException, ClassNotFoundException;
    public Item getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;
    public List<String> getItemIds() throws SQLException, ClassNotFoundException;
}
