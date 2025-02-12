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
import lk.ijse.gdse.s_sweets.bo.custom.IngredientBO;
import lk.ijse.gdse.s_sweets.bo.custom.InventoryBO;
import lk.ijse.gdse.s_sweets.bo.custom.WarehouseBO;
import lk.ijse.gdse.s_sweets.dto.InventoryDTO;
import lk.ijse.gdse.s_sweets.dto.tm.InventoryTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private AnchorPane ancInventory;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> cbIngId;

    @FXML
    private ChoiceBox<String> cbWareId;

    @FXML
    private TableColumn<InventoryTM, String> colIngId;

    @FXML
    private TableColumn<InventoryTM, String> colInvenId;

    @FXML
    private TableColumn<InventoryTM, String> colWareId;

    @FXML
    private TableView<InventoryTM> tblInventory;

    @FXML
    private TextField txtInvenId;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);
    IngredientBO ingredientBO = (IngredientBO) BOFactory.getInstance().getBO(BOFactory.BOType.INGREDIENT);
    WarehouseBO warehouseBO = (WarehouseBO) BOFactory.getInstance().getBO(BOFactory.BOType.WAREHOUSE);

    @FXML
    void btnDeleteOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String invenId = txtInvenId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) inventoryBO.delete(invenId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Inventory deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Inventory...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDTO> InventoryDTOS = inventoryBO.getAll();

        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();

        for (InventoryDTO inventoryDTO : InventoryDTOS) {
            InventoryTM inventoryTM = new InventoryTM(
                    inventoryDTO.getInvenId(),
                    inventoryDTO.getIngId(),
                    inventoryDTO.getWareId()
            );
            inventoryTMS.add(inventoryTM);
        }
        tblInventory.setItems(inventoryTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextInventoryId();

        txtInvenId.clear();
        cbIngId.getItems().clear();
        cbWareId.getItems().clear();
    }

    @FXML
    void btnSaveOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String invenId = txtInvenId.getText();
        String ingId = cbIngId.getValue();
        String wareId = cbWareId.getValue();


        InventoryDTO inventoryDTO = new InventoryDTO(invenId,ingId,wareId);

        boolean isSaved = inventoryBO.save(inventoryDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Inventory Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Inventory Not Saved").show();
        }
    }

    private void getNextInventoryId() throws SQLException, ClassNotFoundException {
        String nextInvenId = inventoryBO.generateNewId();
        txtInvenId.setText(nextInvenId);
    }

    @FXML
    void btnShowOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        getNextInventoryId();
    }

    @FXML
    void btnUpdateOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String invenId = txtInvenId.getText();
        String ingId = cbIngId.getValue();
        String wareId = cbWareId.getValue();

        InventoryDTO inventoryDTO = new InventoryDTO(
                invenId,
                ingId,
                wareId
        );
        boolean isUpdate = (boolean) inventoryBO.update(inventoryDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Inventory update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Inventory...!").show();
        }
        btnSave.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        InventoryTM inventoryTM = tblInventory.getSelectionModel().getSelectedItem();
        if (inventoryTM != null){
            txtInvenId.setText(inventoryTM.getInvenId());
            cbIngId.setValue(inventoryTM.getIngId());
            cbWareId.setValue(inventoryTM.getWareId());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadIngredientIdsIntoChoiceBox();
            loadWarehouseIdsIntoChoiceBox();
            getNextInventoryId();

            colInvenId.setCellValueFactory(new PropertyValueFactory<>("invenId"));
            colIngId.setCellValueFactory(new PropertyValueFactory<>("ingId"));
            colWareId.setCellValueFactory(new PropertyValueFactory<>("wareId"));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Inventory ID").show();
        }
    }

    private void loadIngredientIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> ingredientIds = ingredientBO.getAllIngredientIds();
        cbIngId.getItems().addAll(ingredientIds);
    }

    private void loadWarehouseIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> warehouseIds = warehouseBO.getAllWarehouseIds();
        cbWareId.getItems().addAll(warehouseIds);
    }
}
