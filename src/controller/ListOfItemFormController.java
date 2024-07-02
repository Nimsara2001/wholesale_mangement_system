package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import tm.itemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListOfItemFormController {
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDiscRate;

    public void initialize(){
        try {
            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colDiscRate.setCellValueFactory(new PropertyValueFactory<>("discRate"));

            loadItemsToTable(new ItemController().getAllItems());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadItemsToTable(ArrayList<Item> Items) {
        ObservableList<itemTM> obList = FXCollections.observableArrayList();
        Items.forEach(e->{
            obList.add(new itemTM(e.getItemCode(),e.getDescription(),e.getPackSize(),
                    e.getUnitPrice(),e.getQtyOnHand(),e.getDiscRate()));
        });
        tblItem.setItems(obList);
        tblItem.refresh();
    }
}
