package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.SQLException;


public class SearchItemFormController {

    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscRate;

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1=new ItemController().getItem(txtItemCode.getText());
        if (i1==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set..!").show();
        }else {
            setData(i1);
        }
    }

    private void setData(Item i) {
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtDiscRate.setText(String.valueOf(i.getDiscRate()));
    }
}
