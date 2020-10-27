package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class ControllerSignUp {

    Connection conn;

    public ControllerSignUp () {
        conn = ConnectionUtil.connDB();
    }

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private Label lblInfo;

    @FXML
    private Button buttonSignIn;

    @FXML
    void onActionSignUp(ActionEvent event) throws SQLException {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String login = txtLogin.getText();
        String password = txtPassword.getText();

        String sql = "INSERT INTO users SET firstName = ?, lastName = ?, email = ?, login = ?, password = ?;";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, login);
        statement.setString(5, password);
        int result = statement.executeUpdate();
        if(result == 1){
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("Пользователь успешно добавлен в базу под номером " + id);
            }
        }
    }

    @FXML
    void initialize() {

        buttonSignIn.setOnAction(event -> {

            buttonSignIn.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/window/signIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.show();
        });


    }

}
