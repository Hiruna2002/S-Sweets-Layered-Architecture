package lk.ijse.gdse.s_sweets.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.ProductCategoryBO;
import lk.ijse.gdse.s_sweets.dto.ProductCategoryDTO;
import lk.ijse.gdse.s_sweets.dto.tm.ProductCategoryTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductCategoryController implements Initializable {

    @FXML
    private AnchorPane ancCategoryPage;

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
    private TableColumn<ProductCategoryTM, String> colCatId;

    @FXML
    private TableColumn<ProductCategoryTM, String> colCatName;

    @FXML
    private Label lblCatId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<ProductCategoryTM> tblCategory;

    @FXML
    private TextField txtCatId;

    @FXML
    private TextField txtName;

    ProductCategoryBO productCategoryBO = (ProductCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORY);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String catId = txtCatId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = (boolean) productCategoryBO.delete(catId);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION, "Product deleted...!").show();
                refreshPage();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Product...!").show();
            }
        }
        btnSave.setDisable(false);
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String catId = txtCatId.getText();
        String catName = txtName.getText();


        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO(catId,catName);

        boolean isSaved = productCategoryBO.save(productCategoryDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Category Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Category Not Saved").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextCategoryId = productCategoryBO.generateNewId();
        txtCatId.setText(nextCategoryId);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ProductCategoryDTO> ProductCategoryDTOS = productCategoryBO.getAll();

        ObservableList<ProductCategoryTM> productCategoryTMS = FXCollections.observableArrayList();

        for (ProductCategoryDTO productCategoryDTO : ProductCategoryDTOS) {
            ProductCategoryTM productCategoryTM = new ProductCategoryTM(
                    productCategoryDTO.getCatId(),
                    productCategoryDTO.getCatName()
            );
            productCategoryTMS.add(productCategoryTM);
        }

        tblCategory.setItems(productCategoryTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextId();

        txtCatId.clear();
        txtName.clear();
    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextId();
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String catId = txtCatId.getText();
        String catName = txtName.getText();

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO(
                catId,
                catName
        );

        boolean isUpdate = (boolean) productCategoryBO.update(productCategoryDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Category update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Category...!").show();
        }
        btnSave.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCatId.setCellValueFactory(new PropertyValueFactory<>("CatId"));
        colCatName.setCellValueFactory(new PropertyValueFactory<>("CatName"));

        try {
            loadNextId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Category ID").show();
        }
    }

    @FXML
    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
        ProductCategoryTM productCategoryTM = tblCategory.getSelectionModel().getSelectedItem();
        if (productCategoryTM != null) {
            txtCatId.setText(productCategoryTM.getCatId());
            txtName.setText(productCategoryTM.getCatName());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
}
