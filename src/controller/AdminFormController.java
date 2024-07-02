package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;
import tm.itemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFormController {

    public AnchorPane paneManageItems;
    public JFXButton btnSystemReports;
    public JFXButton btnManageItems;
    public AnchorPane paneSystemReports;
    public JFXButton btnCusWiseIncome;
    public AnchorPane AdminPane;
    public JFXButton btnRegisterItem;
    public JFXButton btnModifyItem;
    public JFXButton btnRemoveItem;
    public JFXButton btnSearchItem;
    public TableView<itemTM> tblItem;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colItemCode;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDiscRate;

    public void initialize(){
        paneSystemReports.setVisible(false);
        paneManageItems.setVisible(true);
        btnManageItems.setStyle("-fx-background-color: #798777");
        btnCusWiseIncome.setText("Customer Wise\n       Income");

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


    public void btnRegisterItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/RegisterItemForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnModifyItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/ModifyItemForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/DeleteItemForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/SearchItemForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        /*btnRegisterItem.setStyle("-fx-background-color: #368B85");
        btnModifyItem.setStyle("-fx-background-color: #368B85");
        btnRemoveItem.setStyle("-fx-background-color: #368B85");
        btnSearchItem.setStyle("-fx-background-color: #368B85");*/

    }

    public void btnManageItemsOnAction(ActionEvent actionEvent)  {
        btnSystemReports.setStyle("-fx-background-color: #368B85");
        paneSystemReports.setVisible(false);
        paneManageItems.setVisible(true);
        btnManageItems.setStyle("-fx-background-color: #798777");
    }

    public void btnSystemReportsOnAction(ActionEvent actionEvent) {
        btnManageItems.setStyle("-fx-background-color: #368B85");
        paneSystemReports.setVisible(true);
        paneManageItems.setVisible(false);
        btnSystemReports.setStyle("-fx-background-color: #798777");
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.
                CONFIRMATION,"Do you want to sure Log Out ?", ButtonType.NO,ButtonType.YES);
        if (alert.showAndWait().get()==ButtonType.YES){
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml")));
            Stage primaryStage= (Stage) this.AdminPane.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        loadItemsToTable(new ItemController().getAllItems());
        tblItem.refresh();
    }
}
