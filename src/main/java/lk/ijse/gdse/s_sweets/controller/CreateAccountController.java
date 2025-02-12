package lk.ijse.gdse.s_sweets.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.CreateAccountBO;
import lk.ijse.gdse.s_sweets.dto.CreateAccountDTO;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class CreateAccountController {

    @FXML
    public TextField txtUserName;

    @FXML
    public TextField txtEmail;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField txtCPassword;

    @FXML
    private TextField txtPassword;

    CreateAccountBO createAccountBO = (CreateAccountBO) BOFactory.getInstance().getBO(BOFactory.BOType.CREATEACCOUNT);

    @FXML
    void btnBackOnNavigateTo(ActionEvent event) {

    }

    @FXML
    void btnCreateAccountOnNavigateTo(ActionEvent event) throws SQLException, ClassNotFoundException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String cPassword = txtCPassword.getText();
        String email = txtEmail.getText();


        CreateAccountDTO createAccountDTO = new CreateAccountDTO(userName,password,cPassword,email);

        boolean isSaved = createAccountBO.save(createAccountDTO);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Customer Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Saved").show();
        }
    }

    private void refreshPage() {
        txtUserName.clear();
        txtPassword.clear();
        txtCPassword.clear();
        txtEmail.clear();
    }

    public void btnGoogleOnNavigateTo(MouseEvent mouseEvent) {
    }

    public void btnFacebookOnNavigateTo(MouseEvent mouseEvent) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com"));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop browsing not supported");
            }
    }

    public void btnGmailOnNavigateTo(MouseEvent mouseEvent) {
        
    }
}
