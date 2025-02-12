package lk.ijse.gdse.s_sweets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitializer extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Attempt to load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Appinitializer.class.getResource("/view/LoginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Attempt to load the stylesheet
//            String stylesheetPath = "/css/Style.css";
//            if (getClass().getResource(stylesheetPath) != null) {
//                scene.getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
//            } else {
//                System.err.println("Stylesheet not found: " + stylesheetPath);
//            }

            // Set the stage properties
            stage.setTitle("Superior Scoop Sweets!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Handle IO-related issues (e.g., file not found or unreadable)
            System.err.println("Failed to load the FXML file or stylesheet:");
            e.printStackTrace();

        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
