package lk.ijse.gdse.s_sweets.bo.custom;

import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.CustomerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public interface CustomerBO extends SuperBO {
    void Delete(String id) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void refreshPage() throws SQLException;
    void Update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    void initialize(URL url, ResourceBundle resourceBundle);
    String generateNewId() throws SQLException, ClassNotFoundException;
    void onClickTable(MouseEvent event);
    List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
