package lk.ijse.gdse.s_sweets.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.DeliveryBO;
import lk.ijse.gdse.s_sweets.bo.custom.OrderBO;
import lk.ijse.gdse.s_sweets.dto.DeliveryDTO;
import lk.ijse.gdse.s_sweets.dto.tm.DeliveryTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeliveryController implements Initializable {

    @FXML
    public ChoiceBox cbOrderId;
    @FXML
    private AnchorPane ancDelivaryPage;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<DeliveryTM, String> colDeliAddress;

    @FXML
    private TableColumn<DeliveryTM, Integer> colDeliAmount;

    @FXML
    private TableColumn<DeliveryTM, String> colDeliDate;

    @FXML
    private TableColumn<DeliveryTM, String> colDeliId;

    @FXML
    private TableColumn<DeliveryTM, String> colDeliRyderName;

    @FXML
    private TableColumn<DeliveryTM, String> colOrderId;

    @FXML
    private TableView<DeliveryTM> tblDelivary;

    @FXML
    private TextField txtDeliAddress;

    @FXML
    private TextField txtDeliAmount;

    @FXML
    private DatePicker txtDeliDate;

    @FXML
    private TextField txtDeliId;

    @FXML
    private TextField txtDeliRyderName;
    
    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getInstance().getBO(BOFactory.BOType.DELIVERY);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String delId = txtDeliId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) deliveryBO.delete(delId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
        btnSave.setDisable(false);
        loadNextDeliveryId();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<DeliveryDTO> DeliveryDTOS = deliveryBO.getAll();

        ObservableList<DeliveryTM> deliveryTMS = FXCollections.observableArrayList();

        for (DeliveryDTO deliveryDTO : DeliveryDTOS) {
            DeliveryTM deliveryTM = new DeliveryTM(
                    deliveryDTO.getDeliveryId(),
                    deliveryDTO.getOrderId(),
                    deliveryDTO.getDeliRyderName(),
                    deliveryDTO.getDeliAddress(),
                    deliveryDTO.getDeliDate(),
                    deliveryDTO.getDeliAmount()
            );
            deliveryTMS.add(deliveryTM);
        }

        tblDelivary.setItems(deliveryTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextDeliveryId();

        txtDeliId.clear();
        cbOrderId.getItems().clear();
        txtDeliRyderName.clear();
        txtDeliAddress.clear();
        txtDeliDate.setValue(null);
        txtDeliAmount.clear();

    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String deliId = txtDeliId.getText();
        String orderId = cbOrderId.getValue().toString();
        String deliRyderName = txtDeliRyderName.getText();
        String deliAddress = txtDeliAddress.getText();
        String deliDate = txtDeliDate.getValue().toString();
        int amount = Integer.parseInt(txtDeliAmount.getText());

        DeliveryDTO deliveryDTO = new DeliveryDTO(deliId, orderId, deliRyderName, deliAddress, deliDate, amount);

        boolean isSaved = deliveryBO.save(deliveryDTO);

        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Delivery has been saved successfully").show();
            refreshPage();
        }else{
            new Alert(Alert.AlertType.ERROR,"Delivery has not been saved successfully").show();
        }

    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextDeliveryId();
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String deliId = txtDeliId.getText();
        String orderId = cbOrderId.getValue().toString();
        String deliRyderName = txtDeliRyderName.getText();
        String deliAddress = txtDeliAddress.getText();
        String deliDate = txtDeliDate.getValue().toString();
        int deliAmount = Integer.parseInt(txtDeliAmount.getText());


        DeliveryDTO deliveryDTO = new DeliveryDTO(
                deliId,
                orderId,
                deliRyderName,
                deliAddress,
                deliDate,
                deliAmount
        );
        boolean isSaved = (boolean) deliveryBO.update(deliveryDTO);

        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Delivery Updated").show();
            refreshPage();
            loadTableData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Delivery Not Updated").show();
        }
        btnSave.setDisable(false);
        loadNextDeliveryId();
    }

    public void loadNextDeliveryId() throws SQLException, ClassNotFoundException {
        String nextDeliId = deliveryBO.generateNewId();
        txtDeliId.setText(nextDeliId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDeliId.setCellValueFactory(new PropertyValueFactory<>("DeliveryId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colDeliRyderName.setCellValueFactory(new PropertyValueFactory<>("DeliRyderName"));
        colDeliAddress.setCellValueFactory(new PropertyValueFactory<>("DeliAddress"));
        colDeliDate.setCellValueFactory(new PropertyValueFactory<>("DeliDate"));
        colDeliAmount.setCellValueFactory(new PropertyValueFactory<>("DeliAmount"));

        try{
            loadOrderIdsIntoChoiceBox();
            loadNextDeliveryId();
        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error Loading next Order Id");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> orderIds = orderBO.getAllOrderIds();
        cbOrderId.getItems().addAll(orderIds);
    }

    public void onClickTable(MouseEvent mouseEvent) {
        DeliveryTM deliveryTM = tblDelivary.getSelectionModel().getSelectedItem();

        if(deliveryTM != null){
            txtDeliId.setText(deliveryTM.getDeliveryId());
            cbOrderId.setValue(deliveryTM.getOrderId());
            txtDeliRyderName.setText(deliveryTM.getDeliRyderName());
            txtDeliAddress.setText(deliveryTM.getDeliAddress());
            txtDeliDate.setValue(LocalDate.parse(deliveryTM.getDeliDate()));
            txtDeliAmount.setText(String.valueOf(deliveryTM.getDeliAmount()));

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

        }
    }
}
