package lk.ijse.gdse.s_sweets.bo.custom.impl;

import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.s_sweets.bo.custom.CustomerBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.CustomerDAO;
import lk.ijse.gdse.s_sweets.dto.CustomerDTO;
import lk.ijse.gdse.s_sweets.entity.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory
            .getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhoneNo()));
        }
        return customerDTOs;
    }

    @Override
    public void save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getPhoneNo()));

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public void refreshPage() throws SQLException {

    }

    @Override
    public void Update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getPhoneNo()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public void onClickTable(MouseEvent event) {

    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomerIds();
    }

}
