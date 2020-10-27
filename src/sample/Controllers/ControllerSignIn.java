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

public class ControllerSignIn {

    Connection conn;

    public ControllerSignIn (){
        conn = ConnectionUtil.connDB();
    }


    @FXML
    private Label lblInfo;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    public void onActionSignIn(ActionEvent event) throws SQLException {

        String login = txtLogin.getText();
        String password = txtPassword.getText();

        String sql = "SELECT login, password from users WHERE login = ? and password = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(login.equals(resultSet.getString("login")) &&
                    password.equals(resultSet.getString("password"))){
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("you are logged in");

                buttonSignIn.setOnAction(event1 -> {
                    buttonSignIn.getScene().getWindow().hide();

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/sample/window/showWin.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Show Window");
                    assert root != null;
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });
            } else
                System.out.println("not data");
        }
    }

    @FXML
    void initialize() {

        buttonSignUp.setOnAction(event -> {
            buttonSignUp.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/window/signUp.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Registration");
            assert root != null;
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}
