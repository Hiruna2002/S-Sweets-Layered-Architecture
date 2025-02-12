package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {
    List<String> getAllProductIds() throws SQLException, ClassNotFoundException;
    boolean updateQty(String proId, int orderQty) throws SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(ProductDTO dto) throws SQLException, ClassNotFoundException;
    Object update(ProductDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
