package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {

        authSignInButton.setOnAction(event ->{
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(loginText.equals("") && loginPassword.equals("")){
                System.out.println("Login and Password is empty");

            } else {
                loginUser(loginText, loginPassword);
            }
        });

        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signUP.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    public void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        try {
            Statement statement = null;
            dbHandler.ConnectionSQLite();
            String query = "SELECT username, password FROM users" +
                    " WHERE username='"+user.getUsername()+"' AND password='"+user.getPassword()+"'";
            ResultSet rs = statement.executeQuery(query);
            int count = 0;
            while(rs.next()) {
                String u = rs.getString("username");
                String p = rs.getString("password");
                count++;
            }
            if(count>=1){
                System.out.println("Succsesful");
            }
            if(count==0){
                System.out.println("Такого чела нет");
            }
            rs.close();
            statement.close();
            //connection.close();


        } catch (Exception e) {
            //throw new RuntimeException("Ошибка в Controller в методе loginUser");
            e.getStackTrace();
        }

    }
}

