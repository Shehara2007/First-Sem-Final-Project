package lk.ijse.sciencelab.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.sciencelab.model.ForgotPasswordmodel;

public class ForgotPasswordController {

    @FXML
    private TextField txtUsernameOrEmail;

    @FXML
    private Label lblMessage;

    private ForgotPasswordmodel model = new ForgotPasswordmodel();

    @FXML
    private void onSendResetLink() {
        String input = txtUsernameOrEmail.getText().trim();

        if (input.isEmpty()) {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("Please enter your username or email.");
            return;
        }

        boolean isSent = model.sendResetLink(input);

        if (isSent) {
            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Reset link sent! Check your email.");
        } else {
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("User not found or email error.");
        }
    }
}
