package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.CustomerDAO;
import lk.ijse.gdse.s_sweets.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> customerDTOArrayList = new ArrayList<>();
        while (rst.next()) {
            customerDTOArrayList.add(new Customer(
                    rst.getString("Cus_id"), rst.getString("Name"), rst.getString("Address"), rst.getString("PhoneNo")));
        }
        return customerDTOArrayList;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("INSERT INTO Customer (Cus_id,Name, Address,PhoneNo) VALUES (?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNo());
        return false;
    }

    @Override
    public Object update(Customer dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE Customer SET Name=?,Address=?,PhoneNo WHERE Cus_id=?"
                , dto.getName()
                , dto.getAddress()
                , dto.getPhoneNo()
                , dto.getId());
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM Customer WHERE Cus_id=?",id);
        return rst.next();
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Customer WHERE Cus_id=?",id);
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Cus_id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("Cus_id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Cus_id FROM Customer");

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }
}
