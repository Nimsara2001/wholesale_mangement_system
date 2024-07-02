package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import tm.cartTM;
import validation.DataValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierFormController{
    public AnchorPane cashierPane;
    public AnchorPane paneMakeOrder;
    public TableView<cartTM> tblCart;

    public TableColumn colDescription;
    public TableColumn colItemCode;
    public TableColumn colQuantity;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTime;
    public Label lblDate;
    public JFXButton btnAddCustomer;
    public JFXButton btnListItems;
    public AnchorPane paneManageOrder;
    public JFXButton btnEditCustomer;
    public JFXButton btnMakeOrder;
    public JFXButton btnManageOrder;
    public JFXButton btnConfirmOrder;
    public JFXButton btnCancelOrder;
    public ComboBox cmbCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public ComboBox cmbItemCode;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemPackSize;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQtyOnHand;
    public JFXTextField txtItemDiscountRate;
    public Button btnAddToCart;
    public Button btnRemoveItemFromCart;
    public JFXTextField txtQtyForCustomer;
    public Label lblQtyForCustomer;
    public Label lblOrderId;
    public Label lblTotalCost;
    public TextField txtSearchCustomerId;
    public Button btnSearchCustomer;
    public ComboBox cmbOrderIdFromSearch;
    public TextField txtSearchOrder;
    public Button btnSearchOrder;
    public JFXTextField txtCustomerNameMgt;
    public JFXTextField txtCustomerAddressMgt;
    public JFXTextField txtCustomerCityMgt;
    public TableView<cartTM> tblCartMgt;
    public TableColumn colItemCodeMgt;
    public TableColumn colDescriptionMgt;
    public TableColumn colQuantityMgt;
    public TableColumn colUnitPriceMgt;
    public TableColumn colDiscountMgt;
    public TableColumn colTotalMgt;
    public ComboBox cmbItemCodeMgt;
    public JFXTextField txtItemDescriptionMgt;
    public JFXTextField txtItemUnitPriceMgt;
    public JFXTextField txtItemPackSizeMgt;
    public JFXTextField txtItemQtyOnHandMgt;
    public Button btnAddToCartMgt;
    public JFXTextField txtItemDiscountRateMgt;
    public Button btnRemoveItemFromCartMgt;
    public Label lblQtyForCustomerMgt;
    public JFXTextField txtQtyForCustomerMgt;
    public Label lblOrderIdMgt;
    public Label lblTotalCostMgt;
    public JFXTextField txtCustomerIdMgt;
    public Label lblTimeMgt;
    public Label lblDateMgt;
    public Label lblTempValidation;
    public Button btnEditQtyMgt;
    public ComboBox cmbOrderIds;

    private int cartSelectedRowForRemove=-1;
    private int cartSelectedRowForRemoveMgt=-1;
    private int cartRowForEditMgt=-1;

    public void initialize(){
        paneManageOrder.setVisible(false);
        paneMakeOrder.setVisible(true);
        paneMakeOrder.setDisable(false);
        btnMakeOrder.setStyle("-fx-background-color: #798777");
        btnRemoveItemFromCartMgt.setText("Remove\nItem");
        btnAddToCartMgt.setText("Add To\nCart");
        btnEditQtyMgt.setText("Edit\nQuantity");
        txtQtyForCustomer.requestFocus();
        txtQtyForCustomerMgt.requestFocus();

        setOrderIdToLabel();
        loadDateAndTime();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        try {
           loadCustomerIds();
           loadItemIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove= (int) newValue;
        });
       //--------------------------------Manage Orders-----------------------------------------------------------
       cmbOrderIdFromSearch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           try {
               setOrderDetailsToDown((String)newValue);
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });

       cmbOrderIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           try {
               setOrderDetailsToDown((String) newValue);
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });

        try {
            setItemIdsToCmbItemMgt();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbItemCodeMgt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemDataMgt((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        try {
            setAllOrderIdsToCmbMgt();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCodeMgt.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescriptionMgt.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantityMgt.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPriceMgt.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscountMgt.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotalMgt.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tblCartMgt.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemoveMgt= (int) newValue;
            cartRowForEditMgt= (int) newValue;
        });
    }

    //End Of Initialize-------------------------------
    private void setAllOrderIdsToCmbMgt() throws SQLException, ClassNotFoundException {
        List<String> orderIds = new OrderController().getOrderIds();
        cmbOrderIds.getItems().addAll(orderIds);

    }
    private void setItemIdsToCmbItemMgt() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemIds();
        cmbItemCodeMgt.getItems().addAll(itemIds);
    }
    ObservableList<cartTM> obListMgt;
    ObservableList<cartTM> onlyOrderItemObListMgt;
    private void setOrderDetailsToDown(String oId) throws SQLException, ClassNotFoundException {
        String orderCustId = new OrderController().getSelectedOrderCustId(oId);
        if (null != orderCustId){
            Customer c1 = new CustomerController().getCustomer(orderCustId);
            txtCustomerIdMgt.setText(c1.getCustomerId());
            txtCustomerNameMgt.setText(c1.getCustomerName());
            txtCustomerAddressMgt.setText(c1.getCustomerAddress());
            txtCustomerCityMgt.setText(c1.getCustomerCity());
        }
        obListMgt = new OrderController().getSelectedOrderItems(oId);
        onlyOrderItemObListMgt = new OrderController().getSelectedOrderItems(oId);
        tblCartMgt.setItems(obListMgt);
        lblOrderIdMgt.setText(oId);
        lblTotalCostMgt.setText(String.valueOf(new OrderController().getTotalCostOfOrder(oId)));
    }

    private void setItemData(String newValue) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(newValue);
        if (! cmbItemCode.getSelectionModel().isEmpty()){
            txtItemDescription.setText(i1.getDescription());
            txtItemPackSize.setText(i1.getPackSize());
            txtItemUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            if (i1.getQtyOnHand()<=0) {
                txtItemQtyOnHandMgt.setText("0");
            }else {
                txtItemQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()-getAddedQty(newValue,tblCart,obList)));
            }
            txtItemDiscountRate.setText(String.valueOf(i1.getDiscRate()));
        }
    }

    private void setItemDataMgt(String newValue) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(newValue);
        if (! cmbItemCodeMgt.getSelectionModel().isEmpty()){
            txtItemDescriptionMgt.setText(i1.getDescription());
            txtItemPackSizeMgt.setText(i1.getPackSize());
            txtItemUnitPriceMgt.setText(String.valueOf(i1.getUnitPrice()));
            if (i1.getQtyOnHand()<=0) {
                txtItemQtyOnHandMgt.setText("0");
            }else {
                txtItemQtyOnHandMgt.setText(String.valueOf(i1.getQtyOnHand()-getAddedQtyMgt(newValue)));
            }
            txtItemDiscountRateMgt.setText(String.valueOf(i1.getDiscRate()));
            txtQtyForCustomerMgt.clear();
        }
    }

    private void setCustomerData(String newValue) throws SQLException, ClassNotFoundException {
        if ( ! cmbCustomerId.getSelectionModel().isEmpty()){
            Customer c1=new CustomerController().getCustomer(newValue);
            txtCustomerName.setText(c1.getCustomerTitle()+". "+c1.getCustomerName());
            txtCustomerAddress.setText(c1.getCustomerAddress());
            txtCustomerCity.setText(c1.getCustomerCity());
       }
    }

    public void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemIds();
        cmbItemCode.getItems().addAll(itemIds);
    }

    public void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/RegisterCustomerForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add New Customer");
        stage.show();
    }

    public void btnListItemsOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/ListOfItemForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("List of Items");
        stage.show();
    }

    ObservableList<cartTM> obList = FXCollections.observableArrayList();
    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        if (cmbItemCode.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Item").show();
            return;
        }
        if (new DataValidation().checkIsNull(txtQtyForCustomer,lblQtyForCustomer,"Required") &&
            new DataValidation().checkIsInteger(txtQtyForCustomer,lblQtyForCustomer,"Only Int")){

            int qtyForCustomer=Integer.parseInt(txtQtyForCustomer.getText());
            String itemCode= (String) cmbItemCode.getValue();
            String description=txtItemDescription.getText();
            double unitPrice=Double.parseDouble(txtItemUnitPrice.getText());
            int qtyOnHand=Integer.parseInt(txtItemQtyOnHand.getText());
            double discRate=Double.parseDouble(txtItemDiscountRate.getText());

            if (qtyForCustomer>qtyOnHand || qtyForCustomer<=0) {
                new Alert(Alert.AlertType.ERROR, "Invalid Quantity..").show();
                return;
            }
            DecimalFormat df=new DecimalFormat("###.##");
            double discount= Double.parseDouble(df.format(qtyForCustomer*unitPrice*(discRate/100)));
            double total=(unitPrice*qtyForCustomer)-discount;
            System.out.println(discount);
            System.out.println(total);

            cartTM tm=new cartTM(
                    itemCode,
                    description,
                    qtyForCustomer,
                    unitPrice,
                    discount,
                    total
            );
            int rowNumber=isExists(tm,obList);
            if (rowNumber==-1){
                obList.add(tm);

            }else {
                cartTM temp=obList.get(rowNumber);
                cartTM newTm=new cartTM(
                        temp.getItemCode(),
                        temp.getDescription(),
                        temp.getQty()+qtyForCustomer,
                        unitPrice,
                        temp.getDiscount()+discount,
                        temp.getTotalPrice()+total
                );

                obList.remove(rowNumber);
                obList.add(newTm);

            }

            tblCart.setItems(obList);
            afterRemoveOrAddToCart();
            calculateTotal(obList,lblTotalCost);

        }
    }

    private void calculateTotal(ObservableList<cartTM> observableList,Label label) {
        double total=0;
        for (cartTM tm: observableList
        ) {
            total+=tm.getTotalPrice();
        }
        label.setText(total+" /=");
    }

    private int isExists(cartTM tm,ObservableList<cartTM> obList) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    private int getAddedQty(String itemCode,TableView tableView,ObservableList<cartTM> obList){
        ObservableList<cartTM> cartItems = tableView.getItems();
        for (int i = 0; i < obList.size(); i++) {
            if (itemCode.equals(cartItems.get(i).getItemCode())){
                return cartItems.get(i).getQty();
            }
        }
        return 0;
    }
    private int getAddedQtyMgt(String itemCode){
        int x=0;
        ObservableList<cartTM> cartItems = tblCartMgt.getItems();
        for (int i = 0; i < cartItems.size(); i++) {
            if (itemCode.equals(cartItems.get(i).getItemCode())){
                if (itemCode.equals(onlyOrderItemObListMgt.get(i).getItemCode())){
                    x=cartItems.get(i).getQty()+onlyOrderItemObListMgt.get(i).getQty();
                    break;
                }else {
                    x=cartItems.get(i).getQty();
                    break;
                }
            }
        }
        return x;
    }
    public void btnRemoveItemFromCartOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row..").show();
        }else {
            obList.remove(cartSelectedRowForRemove);
            calculateTotal(obList,lblTotalCost);
            afterRemoveOrAddToCart();
            tblCart.getSelectionModel().clearSelection();
        }
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
        if (tblCart.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Add Some Items..").show();
            return;
        }else {

            String custId="No-ID";
            if (! cmbCustomerId.getSelectionModel().isEmpty())  custId= (String) cmbCustomerId.getValue();

            ArrayList<ItemDetails> items=new ArrayList<>();
            double total=0;
            for (cartTM tempTm:obList
            ) {
                total+=tempTm.getTotalPrice();
                items.add(
                        new ItemDetails(
                                tempTm.getItemCode(),
                                tempTm.getUnitPrice(),
                                tempTm.getQty(),
                                tempTm.getDiscount(),
                                tempTm.getTotalPrice()
                        )
                );
            }
            Order order=new Order(
                    lblOrderId.getText(),
                    custId,
                    lblDate.getText(),
                    lblTime.getText(),
                    total,
                    items
            );

            if (new OrderController().placeOrder(order)){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Placed Order..!").show();
                setOrderIdToLabel();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
            afterPlacedOrCancelOrder();
        }


    }

    private void setOrderIdToLabel(){
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.
                CONFIRMATION,"Do you want to Cancel this Order?",ButtonType.NO,ButtonType.YES);
        if (alert.showAndWait().get()==ButtonType.YES){
            afterPlacedOrCancelOrder();
        }
    }

    public void btnEditCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnMakeOrderOnAction(ActionEvent actionEvent) {
        btnManageOrder.setStyle("-fx-background-color: #368B85");
        paneManageOrder.setVisible(false);
        paneMakeOrder.setVisible(true);
        btnMakeOrder.setStyle("-fx-background-color: #798777");
    }

    public void btnManageOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnMakeOrder.setStyle("-fx-background-color: #368B85");
        paneManageOrder.setVisible(true);
        paneMakeOrder.setVisible(false);
        btnManageOrder.setStyle("-fx-background-color: #798777");
        afterPlacedOrCancelOrderMgt();
        cmbOrderIds.getItems().clear();
        setAllOrderIdsToCmbMgt();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.
                CONFIRMATION,"Do you want to sure Log Out ?", ButtonType.NO,ButtonType.YES);
        if (alert.showAndWait().get()==ButtonType.YES){
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml")));
            Stage primaryStage= (Stage) this.cashierPane.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }
    }

    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("      yyyy-MM-dd");
        lblDate.setText(f.format(date));
        lblDateMgt.setText(f.format(date));

        Timeline time =new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime=LocalTime.now();
            String sec="00";
            String min="00";
            String hr="00";

            sec= (currentTime.getSecond()<=9) ? "0"+currentTime.getSecond(): String.valueOf(currentTime.getSecond()) ;
            min= (currentTime.getMinute()<=9) ? "0"+currentTime.getMinute():String.valueOf(currentTime.getMinute()) ;
            hr=(currentTime.getHour()<=9) ? "0"+currentTime.getHour() :String.valueOf(currentTime.getHour()) ;

            lblTime.setText(hr+":"+min+":"+sec);
            lblTimeMgt.setText(hr+":"+min+":"+sec);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void btnRefreshCustomerCmbOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            cmbCustomerId.getItems().clear();
            loadCustomerIds();
            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtCustomerCity.clear();
    }

    private void afterRemoveOrAddToCart(){
        tblCart.refresh();
        txtItemDescription.clear();
        txtItemPackSize.clear();
        txtItemQtyOnHand.clear();
        txtItemUnitPrice.clear();
        txtItemDiscountRate.clear();
        txtQtyForCustomer.clear();
        lblQtyForCustomer.setText("");
        cmbItemCode.getSelectionModel().clearSelection();
    }
    private void afterRemoveOrAddToCartMgt(){
        tblCartMgt.refresh();
        txtItemDescriptionMgt.clear();
        txtItemPackSizeMgt.clear();
        txtItemQtyOnHandMgt.clear();
        txtItemUnitPriceMgt.clear();
        txtItemDiscountRateMgt.clear();
        txtQtyForCustomerMgt.clear();
        lblQtyForCustomerMgt.setText("");
        cmbItemCodeMgt.getSelectionModel().clearSelection();
    }

    private void afterPlacedOrCancelOrder(){
        cmbCustomerId.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        cmbItemCode.getSelectionModel().clearSelection();
        txtItemDescription.clear();
        txtItemPackSize.clear();
        txtItemUnitPrice.clear();
        txtItemQtyOnHand.clear();
        txtItemDiscountRate.clear();
        txtQtyForCustomer.clear();
        tblCart.getItems().clear();
        lblQtyForCustomer.setText("");
        lblTotalCost.setText("0.00 /=");
    }

    private void afterPlacedOrCancelOrderMgt(){
        txtSearchCustomerId.clear();
        txtSearchOrder.clear();
        cmbOrderIdFromSearch.getItems().clear();
        cmbOrderIds.getSelectionModel().clearSelection();
        txtCustomerIdMgt.clear();
        txtCustomerNameMgt.clear();
        txtCustomerAddressMgt.clear();
        txtCustomerCityMgt.clear();
        cmbItemCodeMgt.getSelectionModel().clearSelection();
        txtItemDescriptionMgt.clear();
        txtItemPackSizeMgt.clear();
        txtItemUnitPriceMgt.clear();
        txtItemQtyOnHandMgt.clear();
        txtItemDiscountRateMgt.clear();
        txtQtyForCustomerMgt.clear();
        tblCartMgt.getItems().clear();
        lblQtyForCustomerMgt.setText("");
        lblTotalCostMgt.setText("0.00 /=");
        lblOrderIdMgt.setText("O-000");
    }

    public void viewOrderListOnAction(ActionEvent actionEvent) {

    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (! new DataValidation().checkIsNull(txtSearchCustomerId,lblTempValidation,"")){
            new Alert(Alert.AlertType.ERROR,"Please.. Enter Customer ID").show();
        }else if (! new DataValidation().checkCustomerIdFormat(txtSearchCustomerId,lblTempValidation,"")){
            new Alert(Alert.AlertType.ERROR,"Wrong Format..! Use as C-000").show();
        }else if ( new DataValidation().checkCustomerIdDuplicate(txtSearchCustomerId.getText(),lblTempValidation,"")){
            new Alert(Alert.AlertType.ERROR,"Entered ID Could not be Found..!").show();
        }else{
            List<String> orderIdList = new OrderController().getOrderIdsForCustomerId(txtSearchCustomerId.getText());
            if (orderIdList.isEmpty()){
                new Alert(Alert.AlertType.ERROR,"The entered customer has no any Orders..").show();
            }else {
                cmbOrderIdFromSearch.getItems().clear();
                if (cmbOrderIdFromSearch.getItems().isEmpty()){
                    cmbOrderIdFromSearch.getItems().addAll(orderIdList);
                }
                new Alert(Alert.AlertType.INFORMATION,"Order IDs were entered to ComboBox..").show();
            }
        }

    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (! new DataValidation().checkIsNull(txtSearchOrder,lblTempValidation,"")){
            new Alert(Alert.AlertType.ERROR,"Please.. Enter Order ID").show();
        }else if(! new DataValidation().checkOrderIdFormat(txtSearchOrder,lblTempValidation,"")){
            new Alert(Alert.AlertType.ERROR,"Wrong Order ID Format ! Use as O-111").show();
        }else{
            String oId=txtSearchOrder.getText();
            if (new DataValidation().checkOrderIsIn(oId,lblTempValidation," ")){
                new Alert(Alert.AlertType.ERROR,"The entered order is not in the database..").show();
            }else {
                setOrderDetailsToDown(oId);
            }
        }
    }

    //ObservableList<cartTM> obListMgt = FXCollections.observableArrayList();
    public void btnAddToCartOnActionMgt(ActionEvent actionEvent) {
        if (cmbItemCodeMgt.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Item").show();
            return;
        }
        if (new DataValidation().checkIsNull(txtQtyForCustomerMgt,lblQtyForCustomerMgt,"Required") &&
                new DataValidation().checkIsInteger(txtQtyForCustomerMgt,lblQtyForCustomerMgt,"Only Int")){

            int qtyForCustomer=Integer.parseInt(txtQtyForCustomerMgt.getText());
            String itemCode= (String) cmbItemCodeMgt.getValue();
            String description=txtItemDescriptionMgt.getText();
            double unitPrice=Double.parseDouble(txtItemUnitPriceMgt.getText());
            int qtyOnHand=Integer.parseInt(txtItemQtyOnHandMgt.getText());
            double discRate=Double.parseDouble(txtItemDiscountRateMgt.getText());

            if (qtyForCustomer>qtyOnHand || qtyForCustomer<=0) {
                new Alert(Alert.AlertType.ERROR, "Invalid Quantity..").show();
                return;
            }

            DecimalFormat df=new DecimalFormat("###.##");
            double discount= Double.parseDouble(df.format(qtyForCustomer*unitPrice*(discRate/100)));
            double total=(unitPrice*qtyForCustomer)-discount;
            System.out.println(discount);
            System.out.println(total);

            cartTM tm=new cartTM(
                    itemCode,
                    description,
                    qtyForCustomer,
                    unitPrice,
                    discount,
                    total
            );
            int rowNumber=isExists(tm,obListMgt);
            if (rowNumber==-1){
                obListMgt.add(tm);

            }else {
                cartTM temp=obListMgt.get(rowNumber);
                cartTM newTm=new cartTM(
                        temp.getItemCode(),
                        temp.getDescription(),
                        temp.getQty()+qtyForCustomer,
                        unitPrice,
                        temp.getDiscount()+discount,
                        temp.getTotalPrice()+total
                );

                obListMgt.remove(rowNumber);
                obListMgt.add(newTm);

            }

            tblCartMgt.setItems(obListMgt);
            afterRemoveOrAddToCartMgt();
            calculateTotal(obListMgt,lblTotalCostMgt);

        }

    }

    public void btnRemoveItemFromCartOnActionMgt(ActionEvent actionEvent) {
        if (cartSelectedRowForRemoveMgt==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row..").show();
        }else {
            obListMgt.remove(cartSelectedRowForRemoveMgt);
            calculateTotal(obListMgt,lblTotalCostMgt);
            afterRemoveOrAddToCartMgt();
            tblCartMgt.refresh();
            tblCartMgt.getSelectionModel().clearSelection();
        }
    }

    public void btnEditQtyMgtOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartRowForEditMgt==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row..").show();
        }else {
            String iId=obListMgt.get(cartRowForEditMgt).getItemCode();
            Item i1 = new ItemController().getItem(iId);
            cmbItemCodeMgt.setValue(i1.getItemCode());
            txtItemDescriptionMgt.setText(i1.getDescription());
            txtItemPackSizeMgt.setText(i1.getPackSize());
            txtItemUnitPriceMgt.setText(String.valueOf(i1.getUnitPrice()));
            if (i1.getQtyOnHand()<=0) {
                txtItemQtyOnHandMgt.setText(String.valueOf(obListMgt.get(cartRowForEditMgt).getQty()));
            }else {
                txtItemQtyOnHandMgt.setText(String.valueOf(i1.getQtyOnHand()));
            }
            txtItemDiscountRateMgt.setText(String.valueOf(i1.getDiscRate()));
            txtQtyForCustomerMgt.setText(String.valueOf(obListMgt.get(cartRowForEditMgt).getQty()));
            obListMgt.get(cartRowForEditMgt).setQty(0);
            tblCartMgt.refresh();
            tblCartMgt.getSelectionModel().clearSelection();
        }
    }

    public void btnCancelOrderMgtOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.
                CONFIRMATION,"Do you want to Clear this Order?",ButtonType.NO,ButtonType.YES);
        if (alert.showAndWait().get()==ButtonType.YES){
            afterPlacedOrCancelOrderMgt();
        }
    }

    public void btnRemoveOrderMgtOnAction(ActionEvent actionEvent) {
        if (txtCustomerIdMgt.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select a Order..").show();
            return;
        }

        Alert alert=new Alert(Alert.AlertType.
                WARNING,"Do you want to Sure Remove this Order?",ButtonType.NO,ButtonType.YES);
        if (alert.showAndWait().get()==ButtonType.YES){
            try {
                new OrderController().deleteOrder(lblOrderIdMgt.getText());
                afterPlacedOrCancelOrderMgt();
                cmbOrderIds.getItems().clear();
                setAllOrderIdsToCmbMgt();
                new Alert(Alert.AlertType.INFORMATION,"Order is Permanently Removed..").show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnConfirmOrderMgtOnAction(ActionEvent actionEvent) {
        if (tblCartMgt.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Add Some Items..").show();
            return;
        }else {

            ArrayList<ItemDetails> items=new ArrayList<>();
            double total=0;
            for (cartTM tempTm:obListMgt
            ) {
                total+=tempTm.getTotalPrice();
                items.add(
                        new ItemDetails(
                                tempTm.getItemCode(),
                                tempTm.getUnitPrice(),
                                tempTm.getQty(),
                                tempTm.getDiscount(),
                                tempTm.getTotalPrice()
                        )
                );
            }
            Order order=new Order(
                    lblOrderIdMgt.getText(),
                    txtCustomerIdMgt.getText(),
                    lblDateMgt.getText(),
                    lblTimeMgt.getText(),
                    total,
                    items
            );

            if (new OrderController().updateOrderMgt(order)){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated Order..!").show();
                afterPlacedOrCancelOrderMgt();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }

    }
}
