package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
     List<String> getAllProductIds() throws SQLException, ClassNotFoundException;
     boolean updateQty(String proId, int orderQty) throws SQLException, ClassNotFoundException;
}
