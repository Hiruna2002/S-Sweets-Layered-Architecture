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
import lk.ijse.gdse.s_sweets.bo.custom.ProductBO;
import lk.ijse.gdse.s_sweets.bo.custom.SupplierBO;
import lk.ijse.gdse.s_sweets.dao.custom.ProductSupplierDAO;
import lk.ijse.gdse.s_sweets.dao.custom.impl.ProductSupplierDAOImpl;
import lk.ijse.gdse.s_sweets.db.DBConnection;
import lk.ijse.gdse.s_sweets.dto.ProductSupplierDTO;
import lk.ijse.gdse.s_sweets.dto.SupplierDTO;
import lk.ijse.gdse.s_sweets.dto.tm.SupplierTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierController implements Initializable {

    @FXML
    private AnchorPane ancSupplier;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> cbProId;

    @FXML
    private TableColumn<SupplierTM, String> colProId;

    @FXML
    private TableColumn<SupplierTM, Integer> colSupAmount;

    @FXML
    private TableColumn<SupplierTM, String> colSupDate;

    @FXML
    private TableColumn<SupplierTM, String> colSupId;

    @FXML
    private TableColumn<SupplierTM, String> colSupName;

    @FXML
    private TableColumn<SupplierTM, Integer> colSupPhone;

    @FXML
    private DatePicker dpSupDate;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtSupAmount;

    @FXML
    private TextField txtSupId;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtSupPhone;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    ProductSupplierDAO productSupplierDAO = new ProductSupplierDAOImpl();
    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        String supId = txtSupId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) supplierBO.delete(supId);
            if (isDeleted) {
                connection.commit();
                new Alert(Alert.AlertType.INFORMATION, "Supplier deleted...!").show();
                refreshPage();
                loadTableData();
                boolean isItDelete = (boolean) productSupplierDAO.delete(supId);
                if (isItDelete){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Fail to delete Supplier...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("1");
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        System.out.println("2");
        String supId = txtSupId.getText();
        String proId = cbProId.getValue();
        String supName = txtSupName.getText();
        String supDate = dpSupDate.getValue().toString();
        int supAmount = Integer.parseInt(txtSupAmount.getText());
        String supPhone = txtSupPhone.getText();
        System.out.println("3");
        boolean isValidPhoneNo = isValidPhoneNo(supPhone);
        System.out.println("4");
        if(isValidPhoneNo){
            connection.commit();
            SupplierDTO supplierDTO = new SupplierDTO(supId,proId,supName,supDate,supAmount,supPhone);
            ProductSupplierDTO productSupplierDTO = new ProductSupplierDTO(supId,proId,supAmount);
            System.out.println("5");
            boolean isSaved = supplierBO.save(supplierDTO);
            System.out.println("6");
            if (isSaved){
                connection.commit();
                new Alert(Alert.AlertType.INFORMATION,"Supplier Saved").show();
                refreshPage();
                System.out.println("7");
                boolean isItSaved = productSupplierDAO.save(productSupplierDTO);
                System.out.println("8");
                if (isItSaved){
                    connection.commit();
                }else{
                    connection.rollback();
                }
            }else {
                System.out.println("9");
                connection.rollback();
                System.out.println("10");
                new Alert(Alert.AlertType.ERROR,"Supplier Not Saved").show();
            }
        }else{
            System.out.println("11");
            connection.rollback();
            new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
        }


    }

    private boolean isValidPhoneNo(String supPhone) {
        String regex = "^(\\+\\d{1,3})?\\s?\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(supPhone).matches();
    }

    private void getNextSupplierId() throws SQLException, ClassNotFoundException {
        String nextSupId = supplierBO.generateNewId();
        txtSupId.setText(nextSupId);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> SupplierDTOS = supplierBO.getAll();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

        for (SupplierDTO supplierDTO : SupplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getProductId(),
                    supplierDTO.getSupplierName(),
                    supplierDTO.getSupplierDate(),
                    supplierDTO.getSupplierAmount(),
                    supplierDTO.getPhone()
            );
            supplierTMS.add(supplierTM);
        }

        tblSupplier.setItems(supplierTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextSupplierId();

        txtSupId.clear();
        cbProId.getItems().clear();
        txtSupName.clear();
        dpSupDate.setValue(null);
        txtSupAmount.clear();
        txtSupPhone.clear();
    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextSupplierId();
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        String supId = txtSupId.getText();
        String proId = cbProId.getValue();
        String supName = txtSupName.getText();
        String supDate = dpSupDate.getValue().toString();
        int supAmount = Integer.parseInt(txtSupAmount.getText());
        String supPhone = txtSupPhone.getText();

        SupplierDTO supplierDTO = new SupplierDTO(
                supId,
                proId,
                supName,
                supDate,
                supAmount,
                supPhone
        );
        boolean isUpdate = (boolean) supplierBO.update(supplierDTO);
        if (isUpdate) {
            connection.commit();
            new Alert(Alert.AlertType.INFORMATION, "Supplier update...!").show();
            refreshPage();
            loadTableData();
            ProductSupplierDTO productSupplierDTO = new ProductSupplierDTO(
                    supId,
                    proId,
                    supAmount
            );
            boolean isItUpdate = (boolean) productSupplierDAO.update(productSupplierDTO);
            if (isItUpdate){
                connection.commit();
            }else{
                connection.rollback();
            }
        } else {
            connection.rollback();
            new Alert(Alert.AlertType.ERROR, "Fail to update Supplier...!").show();
        }
        btnSave.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        SupplierTM supplierTM = tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTM != null){
            txtSupId.setText(supplierTM.getSupplierId());
            cbProId.setValue(supplierTM.getProductId());
            txtSupName.setText(supplierTM.getSupplierName());
            dpSupDate.setValue(LocalDate.parse(supplierTM.getSupplierDate()));
            txtSupAmount.setText(String.valueOf(supplierTM.getSupplierAmount()));
            txtSupPhone.setText(supplierTM.getPhone());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadProductIdsIntoChoiceBox();
            getNextSupplierId();

            colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
            colProId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            colSupDate.setCellValueFactory(new PropertyValueFactory<>("supplierDate"));
            colSupAmount.setCellValueFactory(new PropertyValueFactory<>("supplierAmount"));
            colSupPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Supplier ID").show();
        }
    }

    private void loadProductIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> productIds = productBO.getAllProductIds();
        cbProId.getItems().addAll(productIds);
    }
}
