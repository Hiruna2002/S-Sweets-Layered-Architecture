package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.ProductBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.ProductDAO;
import lk.ijse.gdse.s_sweets.dto.ProductDTO;
import lk.ijse.gdse.s_sweets.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public List<String> getAllProductIds() throws SQLException, ClassNotFoundException {
        return productDAO.getAllProductIds();
    }

    @Override
    public boolean updateQty(String proId, int orderQty) throws SQLException, ClassNotFoundException {
        return productDAO.updateQty(proId,orderQty);
    }

    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> all = productDAO.getAll();
        ArrayList<ProductDTO>productDTOS = new ArrayList<>();
        for (Product product : all){
            productDTOS.add(new ProductDTO(product.getProId(), product.getCatId(), product.getProName(), product.getPrice(), product.getQty()));
        }
        return productDTOS;
    }

    @Override
    public boolean save(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(dto.getProId(), dto.getCatId(), dto.getProName(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public Object update(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getProId(), dto.getCatId(), dto.getProName(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return productDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewId();
    }
}
