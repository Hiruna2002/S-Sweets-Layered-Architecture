package lk.ijse.gdse.s_sweets.bo;

import lk.ijse.gdse.s_sweets.bo.custom.impl.*;

public class BOFactory implements SuperBO {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        CUSTOMER,DELIVERY,ORDER,PRODUCT,INGREDIENT,EMPLOYEE,WAREHOUSE,FEEDBACK,INVENTORY,PAYMENT,SUPPLIER,CATEGORY,CREATEACCOUNT,LOGIN
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case WAREHOUSE:
                return new WarehouseBOImpl();
            case FEEDBACK:
                return new FeedbackBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case CATEGORY:
                return new ProductCategoryBOImpl();
            case CREATEACCOUNT:
                return new CreateAccountBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }
}
