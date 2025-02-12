package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.s_sweets.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Order_detail values(?,?,?,?)"
            , orderDetailDTO.getOrderId()
            , orderDetailDTO.getProId()
            , orderDetailDTO.getCusId()
            , orderDetailDTO.getAmount());
    }


    public Object delete(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Order_detail where Order_id=?",orderId);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public Object update(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Order_detail set Cus_id=?, Pro_id=?, Amount=? where Order_id=?",
                orderDetailDTO.getCusId(),
                orderDetailDTO.getProId(),
                orderDetailDTO.getAmount(),
                orderDetailDTO.getOrderId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
