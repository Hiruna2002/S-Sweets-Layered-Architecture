package lk.ijse.gdse.s_sweets.dao;

import lk.ijse.gdse.s_sweets.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER, DELIVERY,ORDER,PRODUCT,INGREDIENT,EMPLOYEE,WAREHOUSE,FEEDBACK,INVENTORY,PAYMENT,SUPPLIER,CATEGORY,ORDERDETAIL,CREATEACCOUNT,LOGIN
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DELIVERY:
                return new DeliveryDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case WAREHOUSE:
                return new WarehouseDAOImpl();
            case FEEDBACK:
                return new FeedbackDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case CATEGORY:
                return new ProductCategoryDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case CREATEACCOUNT:
                return new CreateAccountDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }
}
