package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.entity.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDAO extends CrudDAO<ProductCategory> {
     List<String> getAllCategoryIds() throws SQLException, ClassNotFoundException;
}
