package lk.ijse.gdse.s_sweets.controller;

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
import lk.ijse.gdse.s_sweets.dto.CustomerDTO;
import lk.ijse.gdse.s_sweets.dto.tm.CustomerTM;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class CustomerController implements Initializable {

    @FXML
    private AnchorPane anchorCustomerPage;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnNext;

    @FXML
    private TableColumn<CustomerTM, String> colId;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private TableColumn<CustomerTM, String> colPhone;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    CustomerBO customerBO =(CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CusId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtId.setText(newValue.getCusId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtPhone.setText(newValue.getPhoneNo());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtPhone.setDisable(false);
            }
        });
        txtAddress.setOnAction(event -> btnSave.fire());
        try {
            loadAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllCustomers() throws SQLException {
        tblCustomer.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAll();
            for(CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(customer.getCusId(), customer.getName(), customer.getAddress(), customer.getPhoneNo()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtPhone.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        } else if (!phone.matches("^(\\+\\d{1,3})?\\s?\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR,"Phone Number is wrong").show();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Customer*/
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                customerBO.save(new CustomerDTO(id,name,address,phone));

                tblCustomer.getItems().add(new CustomerTM(id, name, address, phone));
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            }


        } else {
            /*Update customer*/
            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
                customerBO.Update(new CustomerDTO(id,name,address,phone));
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            }

            CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setPhoneNo(phone);
            tblCustomer.refresh();
        }
    }

    private boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.exist(id);
    }

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException {
        /*Delete Customer*/
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCusId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            customerBO.Delete(id);

            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();

            refreshPage();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNextCustomerId() throws SQLException {
        try {
            return customerBO.generateNewId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        if (tblCustomer.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }
    }
    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getCusId();
    }

    private void refreshPage() throws SQLException {
        getNextCustomerId();

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null){
            txtId.setText(customerTM.getCusId());
            txtName.setText(customerTM.getName());
            txtAddress.setText(customerTM.getAddress());
            txtPhone.setText(customerTM.getPhoneNo());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
        }
    }

    @FXML
    void btnNextOnNavigateTo(ActionEvent actionEvent) throws IOException {
        anchorCustomerPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Order.fxml"));
        anchorCustomerPage.getChildren().add(load);
    }

//    public void generateReportOnAction(ActionEvent actionEvent) {
//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport(
//                    getClass()
//                            .getResourceAsStream("/report/customer_report.jrxml"
//                            ));
//
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                    jasperReport,
//                    null,
//                    connection
//            );
//
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (JRException e) {
//            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
////           e.printStackTrace();
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
//        }
//    }
}
