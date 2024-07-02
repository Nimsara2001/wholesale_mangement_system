package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public ComboBox cmbSelectRole;
    public PasswordField txtPassword;
    public TextField txtUserName;
    public Button btnLogIn;
    public Button btnClear;
    public AnchorPane LoginPane;

    private String selectedRole="NoOne";
    public void initialize(){
        cmbSelectRole.getItems().addAll("Administrator","Cashier");
        cmbSelectRole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedRole= (String) newValue;
        });
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        cmbSelectRole.getSelectionModel().clearSelection();
        txtUserName.clear();
        txtPassword.clear();
        selectedRole="NoOne";
        cmbSelectRole.setStyle("-fx-border-color: #368B85");
    }

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        if (selectedRole.equals("Administrator")){
            if (txtUserName.getText().equals("admin")){
                if (txtPassword.getText().equals("1234")){
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/AdminForm.fxml")));
                    Stage primaryStage= (Stage) this.LoginPane.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.centerOnScreen();
                }else {
                    txtPassword.setStyle("-fx-border-color: red");
                }
            }else{
                txtUserName.setStyle("-fx-border-color: red");
            }


        }else if (selectedRole.equals("Cashier")){
            if (txtUserName.getText().equals("cashier")){
                if (txtPassword.getText().equals("1234")){
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml")));
                    Stage primaryStage= (Stage) this.LoginPane.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.centerOnScreen();
                }else {
                    txtPassword.setStyle("-fx-border-color: red");
                }
            }else{
                txtUserName.setStyle("-fx-border-color: red");
            }
            ;

        }else{
            cmbSelectRole.setStyle("-fx-border-color: red");
        }
    }
}
