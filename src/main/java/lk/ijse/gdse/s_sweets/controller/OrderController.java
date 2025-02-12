package lk.ijse.gdse.s_sweets.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.CustomerBO;
import lk.ijse.gdse.s_sweets.bo.custom.OrderBO;
import lk.ijse.gdse.s_sweets.bo.custom.ProductBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.s_sweets.db.DBConnection;
import lk.ijse.gdse.s_sweets.dto.OrderDTO;
import lk.ijse.gdse.s_sweets.dto.OrderDetailDTO;
import lk.ijse.gdse.s_sweets.dto.tm.OrderTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderController implements Initializable {

    @FXML
    public TableColumn <OrderTM,String> colProId;

    @FXML
    public ChoiceBox cbCusId;

    @FXML
    public ChoiceBox cbProId;

    @FXML
    public DatePicker dpOrderDate;

    @FXML
    public DatePicker dpDueDate;

    @FXML
    private AnchorPane anchorOrdersPage;

    @FXML
    private TableColumn<OrderTM,String> colOrderId;

    @FXML
    private TableColumn<OrderTM,String> colCusId;

    @FXML
    private TableColumn<OrderTM,String> colItem;

    @FXML
    private TableColumn<OrderTM,String> colQty;

    @FXML
    private TableColumn<OrderTM,String> colOrderDate;

    @FXML
    private TableColumn<OrderTM,String> colDueDate;

    @FXML
    private TableColumn<OrderTM, Integer> colAmount;


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnNext;

    @FXML
    public Button btnBack;


    @FXML
    private TableView<OrderTM> tblOrders;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtItem;

    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);
    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        String orderId = txtOrderId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.YES){
            boolean isDeleted = (boolean) orderBO.delete(orderId);
            if(isDeleted){
                connection.commit();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Deleted", ButtonType.OK);
                refreshPage();
                loadTableData();
                boolean isItDelete = (boolean) orderDetailDAO.delete(orderId);
                if(isItDelete){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }else {
                connection.rollback();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Not Deleted", ButtonType.OK);
            }
        }
        btnSave.setDisable(false);
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        String orderId = txtOrderId.getText();
        String cusId = cbCusId.getValue().toString();
        String proId = cbProId.getValue().toString();
        String item = txtItem.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String orderDate = dpOrderDate.getValue().toString();
        String dueDate = dpDueDate.getValue().toString();
        int amount = Integer.parseInt(txtAmount.getText());

        OrderDTO orderDTO = new OrderDTO(orderId,cusId,proId,item,qty,orderDate,dueDate,amount);



        boolean isEnough = productBO.updateQty(proId,qty);
        if (isEnough){
            connection.commit();
            boolean isSaved = orderBO.save(orderDTO);
            if(isSaved){
                connection.commit();
                new Alert(Alert.AlertType.INFORMATION,"Order Saved").show();
                refreshPage();

                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId,proId,cusId,amount);
                boolean isItSaved = orderDetailDAO.save(orderDetailDTO);

                if(isItSaved){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Order Not Saved").show();
            }
        }else{
            connection.rollback();
            new Alert(Alert.AlertType.ERROR,"Not Enough Products In This Order");
        }
    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextOrderId();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList <OrderDTO> OrderDTOS = orderBO.getAll();

        ObservableList<OrderTM> OrderTMS = FXCollections.observableArrayList();

        for(OrderDTO orderDTO : OrderDTOS){
            OrderTM orderTM = new OrderTM(
                    orderDTO.getOrderId(),
                    orderDTO.getCusId(),
                    orderDTO.getProId(),
                    orderDTO.getItem(),
                    orderDTO.getQty(),
                    orderDTO.getOrderDate(),
                    orderDTO.getDueDate(),
                    orderDTO.getAmount()
            );
            OrderTMS.add(orderTM);
        }
        tblOrders.setItems(OrderTMS);
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        String orderId = txtOrderId.getText();
        String cusId = cbCusId.getValue().toString();
        String proId = cbProId.getValue().toString();
        String item = txtItem.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String orderDate = dpOrderDate.getValue().toString();
        String dueDate = dpDueDate.getValue().toString();
        int amount = Integer.parseInt(txtAmount.getText());

        OrderDTO orderDTO = new OrderDTO(
                orderId,
                cusId,
                proId,
                item,
                qty,
                orderDate,
                dueDate,
                amount
        );

        boolean isUpdate = (boolean) orderBO.update(orderDTO);

        if(isUpdate){
            connection.commit();
            new Alert(Alert.AlertType.INFORMATION,"Order Updated").show();
            refreshPage();
            loadTableData();
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    orderId,
                    proId,
                    cusId,
                    amount
            );
            boolean isItUpdate = (boolean) orderDetailDAO.update(orderDetailDTO);
            if(isItUpdate){
                connection.commit();
            }else{
                connection.rollback();
            }
        }else{
            connection.rollback();
            new Alert(Alert.AlertType.ERROR,"Order Not Updated").show();
        }
        btnSave.setDisable(false);
    }


    public void loadNextOrderId() throws SQLException, ClassNotFoundException {
        String nextOrderId = orderBO.generateNewId();
        txtOrderId.setText(nextOrderId);
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextOrderId();

        txtOrderId.clear();
        cbCusId.getItems().clear();
        cbProId.getItems().clear();
        txtItem.clear();
        txtQty.clear();
        dpOrderDate.setValue(null);
        dpDueDate.setValue(null);
        txtAmount.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("CusId"));
        colProId.setCellValueFactory(new PropertyValueFactory<>("ProId"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("Item"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        try{
            loadCustomerIdsIntoChoiceBox();
            loadProductIdsIntoChoiceBox();
            loadNextOrderId();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error Loading next Order Id");
        }

    }

    private void loadProductIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> productIds = productBO.getAllProductIds();
        cbProId.getItems().addAll(productIds);
    }

    private void loadCustomerIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> customerIds = customerBO.getAllCustomerIds();
        cbCusId.getItems().addAll(customerIds);
    }

    public void onClickTable(MouseEvent event) {
        OrderTM orderTM = tblOrders.getSelectionModel().getSelectedItem();

        if(orderTM != null){
            txtOrderId.setText(orderTM.getOrderId());
            cbCusId.setValue(orderTM.getCusId());
            cbProId.setValue(orderTM.getProId());
            txtItem.setText(orderTM.getItem());
            txtQty.setText(String.valueOf(orderTM.getQty()));
            dpOrderDate.setValue(LocalDate.parse(orderTM.getOrderDate()));
            dpDueDate.setValue(LocalDate.parse(orderTM.getDueDate()));
            txtAmount.setText(String.valueOf(orderTM.getAmount()));

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

        }
    }
}
