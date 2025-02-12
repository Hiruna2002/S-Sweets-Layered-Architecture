package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.IngredientDAO;
import lk.ijse.gdse.s_sweets.entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    public Object delete(String ingId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Ingredients where Ing_id=?",ingId);
    }

    public ArrayList<Ingredient> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Ingredients");
        ArrayList<Ingredient> ingredientDTOS = new ArrayList<>();

        while (rst.next()){
            Ingredient ingredientDTO = new Ingredient(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)+ rst.getString(5),
                    rst.getString(5)
            );
            ingredientDTOS.add(ingredientDTO);
        }
        return ingredientDTOS;
    }

    public boolean save(Ingredient ingredientDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Ingredients values(?,?,?,?,?)"
            , ingredientDTO.getIngId()
            , ingredientDTO.getIngName()
            , ingredientDTO.getExpDate()
            , ingredientDTO.getQty()
            , ingredientDTO.getUnit());

    }

    public Object update(Ingredient ingredientDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Ingredients set Ing_name=?, Exp_date=?, Qty=?, Unit=? where Ing_id=?",
                ingredientDTO.getIngName(),
                ingredientDTO.getExpDate(),
                ingredientDTO.getQty(),
                ingredientDTO.getUnit(),
                ingredientDTO.getIngId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM Ingredients WHERE Ing_id=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT Ing_id from Ingredients order by Ing_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(3); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Ing%03d",newIdIndex);
        }
        return  "Ing001";
    }

    public List<String> getAllIngredientIds() throws SQLException, ClassNotFoundException {
        List<String> inventoryIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Ing_id FROM Ingredients");

        while (rst.next()) {
            inventoryIds.add(rst.getString(1));
        }

        return inventoryIds;
    }
}
