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
import lk.ijse.gdse.s_sweets.bo.custom.WarehouseBO;
import lk.ijse.gdse.s_sweets.dto.WarehouseDTO;
import lk.ijse.gdse.s_sweets.dto.tm.WarehouseTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class WarehouseController implements Initializable {

    @FXML
    public TextField txtLocation;

    @FXML
    private AnchorPane ancWarehouse;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<WarehouseTM,String> colLocation;

    @FXML
    private TableColumn<WarehouseTM,String> colWareId;

    @FXML
    private TableView<WarehouseTM> tblWarehouse;

    @FXML
    private TextField txtWareId;

    WarehouseBO warehouseBO = (WarehouseBO) BOFactory.getInstance().getBO(BOFactory.BOType.WAREHOUSE);
    @FXML
    void btnDeleteOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String wareId = txtWareId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) warehouseBO.delete(wareId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Warehouse deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Warehouse...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<WarehouseDTO> WarehouseDTOS = warehouseBO.getAll();

        ObservableList<WarehouseTM> warehouseTMS = FXCollections.observableArrayList();

        for (WarehouseDTO warehouseDTO : WarehouseDTOS) {
            WarehouseTM warehouseTM = new WarehouseTM(
                    warehouseDTO.getWareId(),
                    warehouseDTO.getLocation()
            );
            warehouseTMS.add(warehouseTM);
        }

        tblWarehouse.setItems(warehouseTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextWarehouseId();

        txtWareId.clear();
        txtLocation.clear();
    }

    @FXML
    void btnSaveOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String wareId = txtWareId.getText();
        String location = txtLocation.getText();


        WarehouseDTO warehouseDTO = new WarehouseDTO(wareId,location);

        boolean isSaved = warehouseBO.save(warehouseDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Warehouse Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Warehouse Not Saved").show();
        }
        getNextWarehouseId();

    }

    private void getNextWarehouseId() throws SQLException, ClassNotFoundException {
        String nextWareId = warehouseBO.generateNewId();
        txtWareId.setText(nextWareId);
    }

    @FXML
    void btnShowOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextWarehouseId();
    }

    @FXML
    void btnUpdateOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String wareId = txtWareId.getText();
        String location = txtLocation.getText();

        WarehouseDTO warehouseDTO = new WarehouseDTO(
                wareId,
                location
        );
        boolean isUpdate = (boolean) warehouseBO.update(warehouseDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Warehouse update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Warehouse...!").show();
        }
        btnSave.setDisable(false);
        refreshPage();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        WarehouseTM warehouseTM = tblWarehouse.getSelectionModel().getSelectedItem();
        if (warehouseTM != null){
            txtWareId.setText(warehouseTM.getWareId());
            txtLocation.setText(warehouseTM.getLocation());



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getNextWarehouseId();
            colWareId.setCellValueFactory(new PropertyValueFactory<>("wareId"));
            colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Warehouse ID").show();
        }
    }
}

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//public class OrderProcessor {
//
//    // Method to check if ingredients are sufficient
//    public boolean canFulfillOrder(Map<String, Integer> orderItems) throws SQLException {
//        for (Map.Entry<String, Integer> item : orderItems.entrySet()) {
//            String ingredientName = item.getKey(); // Ingredient name
//            int requiredQty = item.getValue(); // Required quantity for the order
//
//            // Query to get the available quantity of the ingredient
//            ResultSet rst = CrudUtil.execute("SELECT Qty FROM Ingredient WHERE Ing_name = ?", ingredientName);
//
//            if (rst.next()) {
//                int availableQty = rst.getInt("Qty");
//                if (availableQty < requiredQty) {
//                    System.out.println("Not enough " + ingredientName + ". Required: " + requiredQty + ", Available: " + availableQty);
//                    return false; // Insufficient quantity
//                }
//            } else {
//                System.out.println("Ingredient " + ingredientName + " not found in the database.");
//                return false; // Ingredient not found
//            }
//        }
//        return true; // All ingredients are sufficient
//    }
//
//    // Method to update the inventory after the order is fulfilled
//    public void updateInventory(Map<String, Integer> orderItems) throws SQLException {
//        for (Map.Entry<String, Integer> item : orderItems.entrySet()) {
//            String ingredientName = item.getKey();
//            int usedQty = item.getValue();
//
//            // Update the quantity in the Ingredient table
//            CrudUtil.execute("UPDATE Ingredient SET Qty = Qty - ? WHERE Ing_name = ?", usedQty, ingredientName);
//            System.out.println("Updated inventory for " + ingredientName + ": Deducted " + usedQty);
//        }
//    }
//
//    public static void main(String[] args) {
//        OrderProcessor orderProcessor = new OrderProcessor();
//
//        // Example order: Map of ingredient names and required quantities
//        Map<String, Integer> order = Map.of(
//                "Sugar", 5,
//                "Flour", 10,
//                "Butter", 2
//        );
//
//        try {
//            // Check if the order can be fulfilled
//            if (orderProcessor.canFulfillOrder(order)) {
//                System.out.println("Order can be fulfilled. Proceeding to update inventory...");
//                orderProcessor.updateInventory(order);
//            } else {
//                System.out.println("Order cannot be fulfilled due to insufficient ingredients.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

