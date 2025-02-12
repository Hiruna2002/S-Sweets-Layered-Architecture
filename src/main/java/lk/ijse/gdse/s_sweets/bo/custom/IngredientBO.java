package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientBO extends SuperBO {
    List<String> getAllIngredientIds() throws SQLException, ClassNotFoundException;
    ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException;
    Object update(IngredientDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
