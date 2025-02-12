package lk.ijse.gdse.s_sweets.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.s_sweets.bo.BOFactory;
import lk.ijse.gdse.s_sweets.bo.custom.impl.LoginBOImpl;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.LoginDAO;
import lk.ijse.gdse.s_sweets.dto.LoginDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    public AnchorPane ancLogin;

    @FXML
    private Button btnCreateAnAccount;

    @FXML
    private ImageView btnFacebook;

    @FXML
    private ImageView btnGmail;

    @FXML
    private ImageView btnGoogle;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    LoginBOImpl loginBO = (LoginBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);

    @FXML
    void btnCreateAnAccount(ActionEvent event) throws IOException {
        try {
            ancLogin.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Security.fxml"));
            ancLogin.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnFacebookOnNavigateTo(MouseEvent event) {

    }

    @FXML
    void btnGmailOnNavigateTo(MouseEvent event) {

    }

    @FXML
    void btnGoogleOnNavigateTo(MouseEvent event) {

    }

    @FXML
    void btnLoginOnNavigateTo(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String userName = txtName.getText();
        String password = txtPassword.getText();

        LoginDTO loginDTO = new LoginDTO(userName,password);

        boolean isCorrect = loginBO.checkCorrectData(loginDTO);

        if(isCorrect == true){
            ancLogin.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            ancLogin.getChildren().add(load);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
//            AnchorPane dashboardPane = loader.load();
//
//            // Get the current stage
//            Stage currentStage = (Stage) ancLogin.getScene().getWindow();
//
//            // Set a new scene with the dashboard FXML
//            Scene newScene = new Scene(dashboardPane);
//
//            // Update the stage with the new scene
//            currentStage.setScene(newScene);
//
//            // Set the size of the stage to match the dashboard's dimensions
//            currentStage.sizeToScene(); // Automatically resize to fit the new scene
//            currentStage.setTitle("Dashboard");
        }else{
            new Alert(Alert.AlertType.ERROR,"User Name or Password is Invalid");
            txtName.clear();
            txtPassword.clear();
        }
    }



}
