package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.entity.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDAO extends CrudDAO<Ingredient> {
     List<String> getAllIngredientIds() throws SQLException, ClassNotFoundException;
}
