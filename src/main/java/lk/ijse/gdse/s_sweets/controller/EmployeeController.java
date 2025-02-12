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
import lk.ijse.gdse.s_sweets.bo.custom.EmployeeBO;
import lk.ijse.gdse.s_sweets.bo.custom.WarehouseBO;
import lk.ijse.gdse.s_sweets.dto.EmployeeDTO;
import lk.ijse.gdse.s_sweets.dto.tm.EmployeeTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeController implements Initializable {

    @FXML
    private AnchorPane ancEmployeePage;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> cbWareId;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpId;

    @FXML
    private TableColumn<EmployeeTM, String> colEmpName;

    @FXML
    private TableColumn<EmployeeTM, String> colPhone;

    @FXML
    private TableColumn<EmployeeTM, Integer> colSalary;

    @FXML
    private TableColumn<EmployeeTM, String> colWareId;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSalary;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);
    WarehouseBO warehouseBO = (WarehouseBO) BOFactory.getInstance().getBO(BOFactory.BOType.WAREHOUSE);
    @FXML
    void btnDeleteOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId = txtEmpId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) employeeBO.delete(empId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Employee deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Employee...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> EmployeeDTOS = employeeBO.getAll();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : EmployeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getEmpId(),
                    employeeDTO.getWareId(),
                    employeeDTO.getEmpName(),
                    employeeDTO.getPhone(),
                    employeeDTO.getSalary()

            );
            employeeTMS.add(employeeTM);
        }

        tblEmployee.setItems(employeeTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextEmployeeId();

        txtEmpId.clear();
        cbWareId.setValue(null);
        txtEmpName.clear();
        txtPhone.clear();
        txtSalary.clear();
    }

    @FXML
    void btnSaveOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId = txtEmpId.getText();
        String wareId = cbWareId.getValue();
        String empName = txtEmpName.getText();
        String phone = txtPhone.getText();
        int salary = Integer.parseInt(txtSalary.getText());

        boolean isValidPhoneNo = isvalidPhoneNo(phone);

        if(isValidPhoneNo){
            EmployeeDTO employeeDTO = new EmployeeDTO(empId,wareId,empName,phone,salary);

            boolean isSaved = employeeBO.save(employeeDTO);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Employee Saved").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee Not Saved").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Ivalid Contact Number").show();
        }


    }

    private boolean isvalidPhoneNo(String phone) {
            String regex = "^(\\+\\d{1,3})?\\s?\\d{10}$";
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(phone).matches();
    }

    private void getNextEmployeeId() throws SQLException, ClassNotFoundException {
        String nextEmpId = employeeBO.generateNewId();
        txtEmpId.setText(nextEmpId);
    }

    @FXML
    void btnShowOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextEmployeeId();
    }

    @FXML
    void btnUpdateOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId = txtEmpId.getText();
        String wareId = cbWareId.getValue();
        String empName = txtEmpName.getText();
        String phone = txtPhone.getText();
        int salary = Integer.parseInt(txtSalary.getText());

        EmployeeDTO employeeDTO = new EmployeeDTO(
                empId,
                wareId,
                empName,
                phone,
                salary
        );

        boolean isUpdate = (boolean) employeeBO.update(employeeDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Employee update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Employee...!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        EmployeeTM employeeTM = tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTM != null){
            txtEmpId.setText(employeeTM.getEmpId());
            cbWareId.setValue(employeeTM.getWareId());
            txtEmpName.setText(employeeTM.getEmpName());
            txtPhone.setText(employeeTM.getPhone());
            txtSalary.setText(String.valueOf(employeeTM.getSalary()));

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadWarehouseIdsIntoChoiceBox();
            getNextEmployeeId();

            colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colWareId.setCellValueFactory(new PropertyValueFactory<>("wareId"));
            colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Employee ID").show();
        }
    }

    private void loadWarehouseIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> warehouseIds = warehouseBO.getAllEmployeeIds();
        cbWareId.getItems().addAll(warehouseIds);
    }
}
