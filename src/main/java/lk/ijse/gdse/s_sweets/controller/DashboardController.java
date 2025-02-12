package lk.ijse.gdse.s_sweets.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    public Button btnCustomer;

    @FXML
    public Button btnPayment;

    @FXML
    public Button btnDelivery;

    @FXML
    public Button btnProduct;

    @FXML
    public Button btnCategory;

    @FXML
    public AnchorPane ancHome;

    @FXML
    public AnchorPane ancDashboard;

    @FXML
    public Button btnOrders;

    @FXML
    public Button btnSupplier;

    @FXML
    public Button btnInven;

    @FXML
    public Button btnWarehouse;

    @FXML
    public Button btnEmployee;

    @FXML
    public Button btnFeedback;

    @FXML
    public Button btnProductSupplier;

    @FXML
    public Button btnProductIngredient;

    @FXML
    public Button btnIngredient;

    @FXML
    public Button btnOrderDetails;

    @FXML
    private AnchorPane anchorHomePage;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;
    
    @FXML
    void btnSignInNavigateTo(ActionEvent event) throws IOException {
        navigateTo("/view/LoginPage.fxml");
    }

    @FXML
    void btnSignUpOnNavigateTo(ActionEvent event) throws IOException {
        ancDashboard.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Security.fxml"));
        ancDashboard.getChildren().add(load);
    }

    @FXML
    void navigateTo(String path) throws IOException {
       try{
           ancHome.getChildren().clear();
           AnchorPane load = FXMLLoader.load(getClass().getResource(path));
           ancHome.getChildren().add(load);
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    @FXML
    public void btnCustomerNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Customer.fxml");
    }

    @FXML
    public void btnPaymentOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    public void btnDeliveryOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Delivery.fxml");
    }

    @FXML
    public void btnProductOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Product.fxml");
    }

    @FXML
    public void btnCategoryOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/ProductCategory.fxml");
    }

    @FXML
    public void btnOrdersOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Order.fxml");
    }

    @FXML
    public void btnSupplierOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Supplier.fxml");
    }

    @FXML
    public void btnInvenOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Inventory.fxml");
    }

    @FXML
    public void btnWarehouseOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Warehouse.fxml");

    }

    @FXML
    public void btnEmployeeOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Employee.fxml");
    }

    @FXML
    public void btnFeedbackOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Feedback.fxml");
    }

    @FXML
    public void btnProductSupplierOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/ProductSupplier.fxml");
    }

    @FXML
    public void btnProductIngredientOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/ProductIngredient.fxml");
    }

    @FXML
    public void btnIngredientOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Ingredient.fxml");
    }

    @FXML
    public void btnOrderDetailsOnNavigateTo(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/OrderDetails.fxml");
    }
}
