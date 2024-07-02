package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Customer;
import validation.DataValidation;

import java.sql.SQLException;

public class RegisterCustomerFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerTitle;
    public JFXTextField TxtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public Label lblCustomerId;

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DataValidation().checkIsNull(txtCustomerId, lblCustomerId, "Customer ID is Required") &&
                new DataValidation().checkCustomerIdFormat(txtCustomerId, lblCustomerId, "Wrong Customer ID Format..! Use as C-000") &&
                new DataValidation().checkCustomerIdDuplicate(txtCustomerId.getText(), lblCustomerId, "Already Used..! Please Try another One")) {

            Customer c1 = new Customer(
                    txtCustomerId.getText(),
                    txtCustomerTitle.getText(),
                    TxtCustomerName.getText(),
                    txtCustomerAddress.getText(),
                    txtCustomerCity.getText(),
                    txtCustomerProvince.getText(),
                    txtCustomerPostalCode.getText()
            );
            if (new CustomerController().saveCustomer(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!Please Refresh List").show();
                txtCustomerId.clear();
                txtCustomerTitle.clear();
                TxtCustomerName.clear();
                txtCustomerAddress.clear();
                txtCustomerCity.clear();
                txtCustomerProvince.clear();
                txtCustomerPostalCode.clear();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Please Try Again").show();
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtCustomerId.clear();
        txtCustomerTitle.clear();
        TxtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtCustomerPostalCode.clear();
        lblCustomerId.setText("");
    }
}
