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
import lk.ijse.gdse.s_sweets.bo.custom.CustomerBO;
import lk.ijse.gdse.s_sweets.bo.custom.FeedbackBO;
import lk.ijse.gdse.s_sweets.bo.custom.ProductBO;
import lk.ijse.gdse.s_sweets.dto.FeedbackDTO;
import lk.ijse.gdse.s_sweets.dto.tm.FeedbackTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {

    @FXML
    private AnchorPane ancFeedback;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> cbCusId;

    @FXML
    private ChoiceBox<String> cbProId;

    @FXML
    private TableColumn<FeedbackTM,String> colCusId;

    @FXML
    private TableColumn<FeedbackTM,String> colDesc;

    @FXML
    private TableColumn<FeedbackTM,String> colFeedId;

    @FXML
    private TableColumn<FeedbackTM,String> colProId;

    @FXML
    private TextArea taDesc;

    @FXML
    private TableView<FeedbackTM> tblFeedback;

    @FXML
    private TextField txtFeedId;

    FeedbackBO feedbackBO = (FeedbackBO) BOFactory.getInstance().getBO(BOFactory.BOType.FEEDBACK);
    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String feedId = txtFeedId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) feedbackBO.delete(feedId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Feedback deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Feedback...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<FeedbackDTO> FeedbackDTOS = feedbackBO.getAll();

        ObservableList<FeedbackTM> feedbackTMS = FXCollections.observableArrayList();

        for (FeedbackDTO feedbackDTO : FeedbackDTOS) {
            FeedbackTM feedbackTM = new FeedbackTM(
                    feedbackDTO.getFeedbackId(),
                    feedbackDTO.getProductId(),
                    feedbackDTO.getCustomerId(),
                    feedbackDTO.getDescription()

            );
            feedbackTMS.add(feedbackTM);
        }

        tblFeedback.setItems(feedbackTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextFeedbackId();

        txtFeedId.clear();
        cbProId.setValue(null);
        cbCusId.setValue(null);
        taDesc.clear();
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String feedId = txtFeedId.getText();
        String proId =  cbProId.getValue();
        String cusId = cbCusId.getValue();
        String desc = taDesc.getText();

        FeedbackDTO feedbackDTO = new FeedbackDTO(feedId,proId,cusId,desc);

        boolean isSaved = feedbackBO.save(feedbackDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Feedback Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Feedback Not Saved").show();
        }
    }

    private void getNextFeedbackId() throws SQLException, ClassNotFoundException {
        String nextFeedbackId = feedbackBO.generateNewId();
        txtFeedId.setText(nextFeedbackId);
    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextFeedbackId();
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String feedId = txtFeedId.getText();
        String proId = cbProId.getValue();
        String cusId = cbCusId.getValue();
        String desc = taDesc.getText();

        FeedbackDTO feedbackDTO = new FeedbackDTO(
                feedId,
                proId,
                cusId,
                desc
        );

        boolean isUpdate = (boolean) feedbackBO.update(feedbackDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Feedback update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Feedback...!").show();
        }
        btnSave.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        FeedbackTM feedbackTM = tblFeedback.getSelectionModel().getSelectedItem();
        if (feedbackTM != null){
            txtFeedId.setText(feedbackTM.getFeedbackId());
            cbProId.setValue(feedbackTM.getProductId());
            cbCusId.setValue(feedbackTM.getCustomerId());
            taDesc.setText(feedbackTM.getDescription());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadProductIdsIntoChoiceBox();
            loadCustomerIdsIntoChoiceBox();
            getNextFeedbackId();

            colFeedId.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
            colProId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> customerIds = customerBO.getAllCustomerIds();
        cbCusId.getItems().addAll(customerIds);
    }

    private void loadProductIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> productIds = productBO.getAllProductIds();
        cbProId.getItems().addAll(productIds);
    }
}
