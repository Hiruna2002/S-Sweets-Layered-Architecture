package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.ProductCategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductCategoryBO extends SuperBO {
    ArrayList<ProductCategoryDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(ProductCategoryDTO dto) throws SQLException, ClassNotFoundException;
    Object update(ProductCategoryDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    List<String> getAllCategoryIds() throws SQLException, ClassNotFoundException;
}
