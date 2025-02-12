package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.ProductCategoryBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.ProductCategoryDAO;
import lk.ijse.gdse.s_sweets.dto.ProductCategoryDTO;
import lk.ijse.gdse.s_sweets.entity.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryBOImpl implements ProductCategoryBO {
    ProductCategoryDAO productCategoryDAO = (ProductCategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGORY);
    @Override
    public ArrayList<ProductCategoryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ProductCategory> all = productCategoryDAO.getAll();
        ArrayList <ProductCategoryDTO>productCategoryDTOS = new ArrayList<>();
        for (ProductCategory productCategory : all){
            productCategoryDTOS.add(new ProductCategoryDTO(productCategory.getCatId(), productCategory.getCatName()));
        }
        return productCategoryDTOS;
    }

    @Override
    public boolean save(ProductCategoryDTO dto) throws SQLException, ClassNotFoundException {
        return productCategoryDAO.save(new ProductCategory(dto.getCatId(), dto.getCatName()));
    }

    @Override
    public Object update(ProductCategoryDTO dto) throws SQLException, ClassNotFoundException {
        return productCategoryDAO.update(new ProductCategory(dto.getCatId(), dto.getCatName()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return productCategoryDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return productCategoryDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return productCategoryDAO.generateNewId();
    }

    @Override
    public List<String> getAllCategoryIds() throws SQLException, ClassNotFoundException {
        return productCategoryDAO.getAllCategoryIds();
    }
}
