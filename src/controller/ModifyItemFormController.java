package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Item;
import validation.DataValidation;

import java.sql.SQLException;


public class ModifyItemFormController {

    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscRate;
    public Label lblItemCode;
    public Label lblUnitPrice;
    public Label lblQty;
    public Label lblDiscount;

    String code="";
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        code=txtItemCode.getText();
        Item i1=new ItemController().getItem(code);
        if (i1==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set..!").show();
        }else {
            setData(i1);
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (! txtItemCode.getText().equals(code)){
            lblItemCode.setText("Sorry..! You can't Change the Item Code");
        }else{
            if (new DataValidation().checkIsNull(txtItemCode,lblItemCode,"Entered Item Code is Required") &
                    new DataValidation().checkIsInteger(txtQtyOnHand,lblQty,"Hand On Quantity must be an Integer") &
                    new DataValidation().checkIsDoubleOrInt(txtUnitPrice,lblUnitPrice,"Unit Price must be a Number") &
                    new DataValidation().checkIsDoubleOrInt(txtDiscRate,lblDiscount,"Discount Rate must be a Number")
            )
            {
                Item i1 = new Item(
                        txtItemCode.getText(),
                        txtDescription.getText(),
                        txtPackSize.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Integer.parseInt(txtQtyOnHand.getText()),
                        Double.parseDouble(txtDiscRate.getText())
                );
                try {
                    if (new ItemController().modifyItem(i1)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Item was Successfully Updated..! Refresh Table").show();
                        txtItemCode.clear();
                        txtDescription.clear();
                        txtPackSize.clear();
                        txtUnitPrice.clear();
                        txtQtyOnHand.clear();
                        txtDiscRate.clear();
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Please Try Again").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void setData(Item i) {
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtDiscRate.setText(String.valueOf(i.getDiscRate()));
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtItemCode.clear(); txtDescription.clear(); txtPackSize.clear();
        txtUnitPrice.clear();txtQtyOnHand.clear();txtDiscRate.clear();

        lblItemCode.setText("");lblQty.setText("");lblDiscount.setText("");lblUnitPrice.setText("");
    }


}
