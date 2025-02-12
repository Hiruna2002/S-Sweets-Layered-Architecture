package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.CreateAccountDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CreateAccountBO extends SuperBO {
    ArrayList<CreateAccountDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(CreateAccountDTO dto) throws SQLException, ClassNotFoundException;
    Object update(CreateAccountDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
