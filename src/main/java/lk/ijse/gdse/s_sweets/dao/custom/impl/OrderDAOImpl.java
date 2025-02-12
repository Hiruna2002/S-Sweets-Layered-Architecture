package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.OrderDAO;
import lk.ijse.gdse.s_sweets.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public boolean save(Order orderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Orders values(?,?,?,?,?,?,?,?)"
        , orderDTO.getOrderId()
        , orderDTO.getCusId()
        , orderDTO.getProId()
        , orderDTO.getItem()
        , orderDTO.getQty()
        , orderDTO.getOrderDate()
        , orderDTO.getDueDate()
        , orderDTO.getAmount());
    }

    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from Orders");

        ArrayList <Order> orderDTOS = new ArrayList<>();

        while(rst.next()){
            Order orderDTO = new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getInt(8)
            );
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    public Object update(Order orderDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Orders set CusId=?, ProId=?, Item=?, Qty=?, OrderDate=?, DueDate=?, Amount=? where OrderId=?",
                orderDTO.getCusId(),
                orderDTO.getProId(),
                orderDTO.getItem(),
                orderDTO.getQty(),
                orderDTO.getOrderDate(),
                orderDTO.getDueDate(),
                orderDTO.getAmount(),
                orderDTO.getOrderId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Orders WHERE OrderId=?",id);
        return rst.next();
    }

    public Object delete(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Orders where OrderId=?",orderId);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select OrderId from Orders order by OrderId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("O%03d",newIdIndex);
        }
        return  "O001";
    }

    @Override
    public List<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        List<String> orderIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT OrderId FROM Orders");

        while (rst.next()) {
            orderIds.add(rst.getString(1));
        }

        return orderIds;
    }
}
