package lk.ijse.sciencelab.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sciencelab.User;

import java.io.IOException;

public class LoginPageController {
    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnLogin1;
    public AnchorPane ancLogin;
    private User user = new User("1", "1");

    public void loginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (user.verifyLogin(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
                Parent dashboardRoot = loader.load();
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                Scene scene = new Scene(dashboardRoot);
                stage.setScene(scene);
                stage.setTitle("Dashboard");
                stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Invalid username or password.", Alert.AlertType.ERROR);
        }
    }

        private void showAlert(String message, Alert.AlertType type) {
            Alert alert = new Alert(type);
            alert.setContentText(message);
            alert.show();
        }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();

    }

    private void nevigateTo(String s) {
        try {
            ancLogin.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancLogin.widthProperty());
            pane.prefHeightProperty().bind(ancLogin.heightProperty());

            ancLogin.getChildren().add(pane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page Not Found!").show();
            e.printStackTrace();

        }
    }

    public void btnForgotPassword(ActionEvent actionEvent) {
        nevigateTo("/view/ForgotPassword.fxml");
    }
}

