package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.IngredientBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.IngredientDAO;
import lk.ijse.gdse.s_sweets.dto.IngredientDTO;
import lk.ijse.gdse.s_sweets.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBOImpl implements IngredientBO {

    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INGREDIENT);
    @Override
    public List<String> getAllIngredientIds() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getAllIngredientIds();
    }

    @Override
    public ArrayList<IngredientDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> all = ingredientDAO.getAll();
        ArrayList<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (Ingredient ingredient : all){
            ingredientDTOS.add(new IngredientDTO(ingredient.getIngId(), ingredient.getIngName(), ingredient.getExpDate(), ingredient.getQty(), ingredient.getUnit()));
        }
        return ingredientDTOS;
    }

    @Override
    public boolean save(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return ingredientDAO.save(new Ingredient(dto.getIngId(), dto.getIngName(), dto.getExpDate(), dto.getQty(), dto.getUnit()));
    }

    @Override
    public Object update(IngredientDTO dto) throws SQLException, ClassNotFoundException {
        return ingredientDAO.update(new Ingredient(dto.getIngId(), dto.getIngName(), dto.getExpDate(), dto.getQty(), dto.getUnit()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return ingredientDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return ingredientDAO.generateNewId();
    }
}
