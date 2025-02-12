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
import lk.ijse.gdse.s_sweets.bo.custom.OrderBO;
import lk.ijse.gdse.s_sweets.bo.custom.PaymentBO;
import lk.ijse.gdse.s_sweets.dto.PaymentDTO;
import lk.ijse.gdse.s_sweets.dto.tm.PaymentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private ToggleGroup paymentMethodGroup;

    @FXML
    public Label lblPayMethod;

    @FXML
    public RadioButton rbtnCard;

    @FXML
    public RadioButton rbtnCash;

    @FXML
    public ChoiceBox cbOrderId;

    @FXML
    private Button btnHome;

    @FXML
    private AnchorPane ancPaymentPage;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PaymentTm, Integer> colAmount;

    @FXML
    private TableColumn<PaymentTm, String> colOrderId;

    @FXML
    private TableColumn<PaymentTm, String> colPayDate;

    @FXML
    private TableColumn<PaymentTm, String> colPayId;

    @FXML
    private TableColumn<PaymentTm, String> colPayMethod;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtPayId;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String payId = txtPayId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) paymentBO.delete(payId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Payment...!").show();
            }
        }
        btnSave.setDisable(false);
        getNextId();
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String payId = txtPayId.getText();
        String orderId = cbOrderId.getValue().toString();
        String payDate = txtDate.getValue().toString();
        String payMethod = ((RadioButton) paymentMethodGroup.getSelectedToggle()).getText();
        int amount = Integer.parseInt(txtAmount.getText());


        PaymentDTO paymentDTO = new PaymentDTO(payId,orderId,payDate,payMethod,amount);

        boolean isSaved = paymentBO.save(paymentDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Payment Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Payment Not Saved").show();
        }
    }

    private void getNextId() throws SQLException, ClassNotFoundException {
        String nextProductId = paymentBO.generateNewId();
        txtPayId.setText(nextProductId);
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> PaymentDTOS = paymentBO.getAll();

        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : PaymentDTOS) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDTO.getPayId(),
                    paymentDTO.getOrderId(),
                    paymentDTO.getPayDate(),
                    paymentDTO.getPayMethod(),
                    paymentDTO.getAmount()
            );
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextId();

        txtPayId.clear();
        cbOrderId.setValue(null);
        txtDate.setValue(null);
        rbtnCard.setText(null);
        txtAmount.clear();
    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextId();
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String payId = txtPayId.getText();
        String orderId = cbOrderId.getValue().toString();
        String payDate = txtDate.getValue().toString();
        String payMethod = ((RadioButton) paymentMethodGroup.getSelectedToggle()).getText();
        int amount = Integer.parseInt(txtAmount.getText());

        PaymentDTO paymentDTO = new PaymentDTO(
                payId,
                orderId,
                payDate,
                payMethod,
                amount
        );

        boolean isUpdate = (boolean) paymentBO.update(paymentDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }
        btnSave.setDisable(false);
        getNextId();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTm != null){
            txtPayId.setText(paymentTm.getPayId());
            cbOrderId.setValue(paymentTm.getOrderId());
            txtDate.setValue(LocalDate.parse(paymentTm.getPayDate()));
            txtAmount.setText(String.valueOf(paymentTm.getPayAmount()));

            if (paymentTm.getPayMethod().equalsIgnoreCase("Card")) {
                rbtnCard.setSelected(true);
            } else if (paymentTm.getPayMethod().equalsIgnoreCase("Cash")) {
                rbtnCash.setSelected(true);
            }

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("PayId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("PayDate"));
        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("PayMethod"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("PayAmount"));

        rbtnCash.setSelected(true);

        try {
            loadOrderIdsIntoChoiceBox();
            getNextId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Payment ID").show();
        }
    }

    private void loadOrderIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> orderIds = orderBO.getAllOrderIds();
        cbOrderId.getItems().addAll(orderIds);
    }
}
