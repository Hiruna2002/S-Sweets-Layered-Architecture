package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.ProductDAO;
import lk.ijse.gdse.s_sweets.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public boolean save(Product productDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Products values(?,?,?,?,?)"
            ,productDTO.getProId()
             ,productDTO.getCatId()
             ,productDTO.getProName()
             ,productDTO.getPrice()
             ,productDTO.getQty()
        );
    }

    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from Products");
        ArrayList <Product>productDTOS = new ArrayList<>();

        while (rst.next()){
            Product productDTO = new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5)
            );
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public Object update(Product productDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Products set Cat_id=?, Pro_name=?, Price=?, Qty=? where Pro_id=?",
                productDTO.getCatId(),
                productDTO.getProName(),
                productDTO.getPrice(),
                productDTO.getQty(),
                productDTO.getProId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM Products WHERE Pro_id=?",id);
        return rst.next();
    }

    public Object delete(String proId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Products where Pro_id=?",proId);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Pro_id from Products order by Pro_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(3); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Pro%03d",newIdIndex);
        }
        return  "Pro001";
    }

    public List<String> getAllProductIds() throws SQLException, ClassNotFoundException {
        List<String> productIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Pro_id FROM Products");

        while (rst.next()) {
            productIds.add(rst.getString(1));
        }

        return productIds;
    }

    public boolean updateQty(String proId, int orderQty) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select Qty from Products where Pro_id=?",proId);

        if (rst.next()){
            int availableQty = rst.getInt("Qty");
            if (orderQty <= availableQty){
                return SQLUtil.execute("update Products set Qty=? where Pro_id=?",(availableQty - orderQty), proId);
            }
            return false;
        }else{
            throw new SQLException("Product not found for Id: " + proId);
        }
    }
}
