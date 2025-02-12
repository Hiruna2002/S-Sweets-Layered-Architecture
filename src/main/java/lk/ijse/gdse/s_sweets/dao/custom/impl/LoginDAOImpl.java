package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.LoginDAO;
import lk.ijse.gdse.s_sweets.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {
    public boolean checkCorrectData(Login loginDTO) throws SQLException, ClassNotFoundException {
        String query = "select * from Admin where UserName=? and Password=?";

        ResultSet rst = SQLUtil.execute(query,
                loginDTO.getUserName(),
                loginDTO.getPassword()
        );
        if(rst.next()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ArrayList<Login> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Login dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object update(Login dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }
}
