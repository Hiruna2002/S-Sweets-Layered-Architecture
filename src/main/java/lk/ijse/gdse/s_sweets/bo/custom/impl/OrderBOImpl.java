package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.OrderBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.OrderDAO;
import lk.ijse.gdse.s_sweets.dto.OrderDTO;
import lk.ijse.gdse.s_sweets.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll();
        ArrayList<OrderDTO>orderDTOS = new ArrayList<>();
        for (Order order : all){
            orderDTOS.add(new OrderDTO(order.getOrderId(), order.getCusId(), order.getProId(), order.getItem(), order.getQty(), order.getOrderDate(), order.getDueDate(), order.getAmount()));
        }
        return orderDTOS;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(dto.getOrderId(), dto.getCusId(), dto.getProId(), dto.getItem(), dto.getQty(), dto.getOrderDate(), dto.getDueDate(), dto.getAmount()));
    }


    @Override
    public Object update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(dto.getOrderId(), dto.getCusId(), dto.getProId(), dto.getItem(), dto.getQty(), dto.getOrderDate(), dto.getDueDate(), dto.getAmount()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public List<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        return orderDAO.getAllOrderIds();
    }
}
