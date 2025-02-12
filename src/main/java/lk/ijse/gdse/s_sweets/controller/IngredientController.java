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
import lk.ijse.gdse.s_sweets.dto.IngredientDTO;
import lk.ijse.gdse.s_sweets.dto.tm.IngredientTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class IngredientController implements Initializable {

    @FXML
    public TextField txtUnit;

    @FXML
    private AnchorPane ancIngredient;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<IngredientTm,String> colExpDate;

    @FXML
    private TableColumn<IngredientTm,String> colIngId;

    @FXML
    private TableColumn<IngredientTm,String> colIngName;

    @FXML
    private TableColumn<IngredientTm,Integer> colQty;

    @FXML
    private DatePicker dpExpDate;

    @FXML
    private TableView<IngredientTm> tblIngredient;

    @FXML
    private TextField txtIngId;

    @FXML
    private TextField txtIngName;

    @FXML
    private TextField txtQty;
    
    IngredientBO ingredientBO = (IngredientBO) BOFactory.getInstance().getBO(BOFactory.BOType.INGREDIENT);

    @FXML
    void btnDeleteOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingId = txtIngId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) ingredientBO.delete(ingId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Ingredient deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Ingredient...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<IngredientDTO> IngredientDTOS = ingredientBO.getAll();

        ObservableList<IngredientTm> ingredientTms = FXCollections.observableArrayList();

        for (IngredientDTO ingredientDTO : IngredientDTOS) {
            IngredientTm ingredientTm = new IngredientTm(
                    ingredientDTO.getIngId(),
                    ingredientDTO.getIngName(),
                    ingredientDTO.getExpDate(),
                    ingredientDTO.getQty(),
                    ingredientDTO.getUnit()

            );
            ingredientTms.add(ingredientTm);
        }

        tblIngredient.setItems(ingredientTms);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        getNextIngredientId();

        txtIngId.clear();
        txtIngName.clear();
        dpExpDate.setValue(null);
        txtQty.clear();
        txtUnit.clear();
    }

    @FXML
    void btnSaveOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingId = txtIngId.getText();
        String ingName =  txtIngName.getText();
        String expDate = dpExpDate.getValue().toString();
        String qty = txtQty.getText();
        String unit = txtUnit.getText();

        IngredientDTO ingredientDTO = new IngredientDTO(ingId,ingName,expDate,qty,unit);

        boolean isSaved = ingredientBO.save(ingredientDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Ingredient Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Ingredient Not Saved").show();
        }
    }

    private void getNextIngredientId() throws SQLException, ClassNotFoundException {
        String nextIngredientId = ingredientBO.generateNewId();
        txtIngId.setText(nextIngredientId);
    }

    @FXML
    void btnShowOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        refreshPage();
    }

    @FXML
    void btnUpdateOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ingId = txtIngId.getText();
        String ingName = txtIngName.getText();
        String expDate = dpExpDate.getValue().toString();
        String qty = txtQty.getText();
        String unit = txtUnit.getText();

        IngredientDTO ingredientDTO = new IngredientDTO(ingId,ingName,expDate,qty,unit);

        boolean isUpdate = (boolean) ingredientBO.update(ingredientDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Ingredient update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Ingredient...!").show();
        }
        btnSave.setDisable(false);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        IngredientTm ingredientTm = tblIngredient.getSelectionModel().getSelectedItem();
        if (ingredientTm != null){
            txtIngId.setText(ingredientTm.getIngId());
            txtIngName.setText(ingredientTm.getIngName());
            dpExpDate.setValue(LocalDate.parse(ingredientTm.getExpDate()));
            txtQty.setText(ingredientTm.getQty());
            txtUnit.setText(ingredientTm.getUnit());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getNextIngredientId();

            colIngId.setCellValueFactory(new PropertyValueFactory<>("ingId"));
            colIngName.setCellValueFactory(new PropertyValueFactory<>("ingName"));
            colExpDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
