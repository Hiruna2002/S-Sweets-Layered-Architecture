package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.ProductCategoryDAO;
import lk.ijse.gdse.s_sweets.entity.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {
    public ArrayList<ProductCategory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Product_Category");

        ArrayList<ProductCategory> productCategoryDTOS = new ArrayList<>();

        while (rst.next()){
            ProductCategory productCategoryDTO = new ProductCategory(
                    rst.getString(1),
                    rst.getString(2)
            );
            productCategoryDTOS.add(productCategoryDTO);
        }
        return productCategoryDTOS;
    }

    public boolean save(ProductCategory productCategoryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Product_Category values(?,?)"
            , productCategoryDTO.getCatId()
            , productCategoryDTO.getCatName());
    }

    public Object update(ProductCategory productCategoryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Product_Category set Cat_name=? where Cat_id=?",
                productCategoryDTO.getCatName(),
                productCategoryDTO.getCatId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Product_Category WHERE Cat_id=?",id);
        return rst.next();
    }

    public Object delete(String catId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Product_Category where Cat_id=?",catId);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Cat_id from Product_Category order by Cat_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(3); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Cat%03d",newIdIndex);
        }
        return  "Cat001";
    }

    public List<String> getAllCategoryIds() throws SQLException, ClassNotFoundException {
        List<String> categoryIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Cat_id FROM Product_Category");

        while (rst.next()) {
            categoryIds.add(rst.getString(1));
        }

        return categoryIds;
    }
}
