package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.dto.LoginDTO;
import lk.ijse.gdse.s_sweets.entity.Login;

import java.sql.SQLException;

public interface LoginDAO extends CrudDAO<Login> {
    boolean checkCorrectData(Login loginDTO) throws SQLException, ClassNotFoundException;
}
