package lk.ijse.gdse.s_sweets.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.ProductBO;
import lk.ijse.gdse.s_sweets.bo.custom.ProductCategoryBO;
import lk.ijse.gdse.s_sweets.dto.ProductDTO;
import lk.ijse.gdse.s_sweets.dto.tm.ProductTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    public ChoiceBox cbCatId;
    public TableColumn colQty;
    public TextField txtQty;

    @FXML
    private AnchorPane ancProductPage;

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
    private TableColumn<ProductTM, String> colCatId;

    @FXML
    private TableColumn<ProductTM, Integer> colPrice;

    @FXML
    private TableColumn<ProductTM, String> colProId;

    @FXML
    private TableColumn<ProductTM, String> colProName;

    @FXML
    private TableView<ProductTM> tblProduct;

    @FXML
    private TextField txtProId;

    @FXML
    private TextField txtProName;

    @FXML
    private TextField txtProPrice;

    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    ProductCategoryBO productCategoryBO = (ProductCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORY);

    @FXML
    void btnDeleteOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String proId = txtProId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.YES){
            boolean isDeleted = (boolean) productBO.delete(proId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted", ButtonType.OK);
                refreshPage();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Product Not Deleted", ButtonType.OK);
            }
        }
        btnSave.setDisable(false);
        btnShow.setDisable(false);
    }

    @FXML
    void btnSaveOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String proId = txtProId.getText();
        String catId = cbCatId.getValue().toString();
        String proName = txtProName.getText();
        int price = Integer.parseInt(txtProPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        ProductDTO productDTO = new ProductDTO(proId, catId, proName, price,qty);

        boolean isSaved = productBO.save(productDTO);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Product Saved").show();
            refreshPage();
        }else{
            new Alert(Alert.AlertType.ERROR,"Product Not Saved").show();
        }

    }

    @FXML
    void btnShowOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextProductId();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productDTOS = productBO.getAll();

        ObservableList <ProductTM>productTMS = FXCollections.observableArrayList();

        for(ProductDTO productDTO : productDTOS){
            ProductTM productTM = new ProductTM(
                    productDTO.getProId(),
                    productDTO.getCatId(),
                    productDTO.getProName(),
                    productDTO.getPrice(),
                    productDTO.getQty()
            );
            productTMS.add(productTM);
        }
        tblProduct.setItems(productTMS);
    }

    @FXML
    void btnUpdateOnClickTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String proId = txtProId.getText();
        String catId = cbCatId.getValue().toString();
        String proName = txtProName.getText();
        int price = Integer.parseInt(txtProPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        ProductDTO productDTO = new ProductDTO(
                proId,
                catId,
                proName,
                price,
                qty
        );

        boolean isUpdate = (boolean) productBO.update(productDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Product update...!").show();
            refreshPage();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Product...!").show();
        }
        btnSave.setDisable(false);
        btnShow.setDisable(false);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextProductId();

        txtProId.clear();
        cbCatId.setValue(null);
        txtProName.clear();
        txtProPrice.clear();
        txtQty.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProId.setCellValueFactory(new PropertyValueFactory<>("ProId"));
        colCatId.setCellValueFactory(new PropertyValueFactory<>("CatId"));
        colProName.setCellValueFactory(new PropertyValueFactory<>("ProName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            loadCategoryIdsIntoChoiceBox();
            loadNextProductId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the next Product ID").show();
        }
    }

    private void loadNextProductId() throws SQLException, ClassNotFoundException {
        String nextProductId = productBO.generateNewId();
        txtProId.setText(nextProductId);
    }

    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
        ProductTM productTM = tblProduct.getSelectionModel().getSelectedItem();
        if (productTM != null){
            txtProId.setText(productTM.getProId());
            cbCatId.setValue(productTM.getCatId());
            txtProName.setText(productTM.getProName());
            txtProPrice.setText(String.valueOf(productTM.getPrice()));
            txtQty.setText(String.valueOf(productTM.getQty()));

            btnSave.setDisable(true);
            btnShow.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    private void loadCategoryIdsIntoChoiceBox() throws SQLException, ClassNotFoundException {
        List<String> categoryIds = productCategoryBO.getAllCategoryIds();
        cbCatId.getItems().addAll(categoryIds);
    }
}
