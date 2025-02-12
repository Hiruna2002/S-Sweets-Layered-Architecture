package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dao.SuperDAO;
import lk.ijse.gdse.s_sweets.dto.LoginDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginBO extends SuperBO {
    boolean checkCorrectData(LoginDTO loginDTO) throws SQLException, ClassNotFoundException;
    ArrayList<LoginDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(LoginDTO dto) throws SQLException, ClassNotFoundException;
    Object update(LoginDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
