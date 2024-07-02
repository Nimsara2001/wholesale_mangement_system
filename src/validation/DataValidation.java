package validation;

import db.DbConnection;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataValidation {
    public boolean checkIsNull(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().isEmpty()){
            inputLabel.setText(text);
            return false;
        }else {
            inputLabel.setText("");
            return true;
        }
    }

    public boolean checkIsInteger(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("[0-9]+")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkIsDouble(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("^[-+]?\\d+(\\.{0,1}(\\d+?))?$")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkIsDoubleOrInt(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("[0-9]+") |
                fieldName.getText().matches("^[-+]?\\d+(\\.{0,1}(\\d+?))?$")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkItemIdFormat(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("I-[0-9]+")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkCustomerIdFormat(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("C-[0-9]+")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkOrderIdFormat(TextField fieldName, Label inputLabel,String text){
        if (fieldName.getText().matches("O-[0-9]+")){
            inputLabel.setText("");
            return true;
        }else {
            inputLabel.setText(text);
            return false;
        }
    }

    public boolean checkItemIdDuplicate(String id,Label label,String lblText) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT ItemCode FROM Item WHERE ItemCode='" +id+ "'");
        if(stm.executeQuery().next()){
            label.setText(lblText);
            return false;
        }else {
            label.setText("");
            return true;
        }
    }

    public boolean checkCustomerIdDuplicate(String id,Label label,String lblText) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT CustID FROM Customer WHERE CustID='" +id+ "'");
        if(stm.executeQuery().next()){
            label.setText(lblText);
            return false;
        }else {
            label.setText("");
            return true;
        }
    }
    public boolean checkOrderIsIn(String oId,Label label,String lblText) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT OrderID FROM Orders WHERE OrderID='" +oId+ "'");
        if(stm.executeQuery().next()){
            label.setText(lblText);
            return false; //Yes
        }else {
            label.setText("");
            return true; //No
        }
    }
}
