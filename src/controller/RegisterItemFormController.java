package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import tm.itemTM;
import validation.DataValidation;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;


public class RegisterItemFormController {

    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscRate;
    public Label lblItemCode;
    public Label lblPackSize;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQty;
    public Label lblDiscount;

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if ((new DataValidation().checkIsNull(txtItemCode,lblItemCode,"Item Code is Required") &&
                new DataValidation().checkItemIdFormat(txtItemCode,lblItemCode,"Wrong Item Code Format..! Use as I-000") &&
                new DataValidation().checkItemIdDuplicate(txtItemCode.getText(),lblItemCode,"Already Used..! Please Try another One") ) &

                new DataValidation().checkIsInteger(txtQtyOnHand,lblQty,"Hand On Quantity must be an Integer") &
                new DataValidation().checkIsDoubleOrInt(txtUnitPrice,lblUnitPrice,"Unit Price must be a Number") &
                new DataValidation().checkIsDoubleOrInt(txtDiscRate,lblDiscount,"Discount Rate must be a Number")
        ){
            Item i1 = new Item(
                    txtItemCode.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    Double.parseDouble(txtDiscRate.getText())
            );
            if (new ItemController().saveItem(i1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item was Successfully Saved..! Refresh Table").show();
                txtItemCode.clear();
                txtDescription.clear();
                txtPackSize.clear();
                txtUnitPrice.clear();
                txtQtyOnHand.clear();
                txtDiscRate.clear();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Please Try Again").show();
            }
        }
    }


    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtItemCode.clear();txtDescription.clear();txtPackSize.clear();
        txtUnitPrice.clear();txtQtyOnHand.clear();txtDiscRate.clear();

        lblItemCode.setText("");lblQty.setText("");lblDiscount.setText("");lblUnitPrice.setText("");
    }
}
