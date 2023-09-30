package lk.ijse.carrentalmanage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane rootNode;
    public TextField txtUser;
    public TextField txtPw;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        Parent node=FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Scene scene=new Scene(node);
        Stage stage= (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void registerOnAction(ActionEvent actionEvent) {
    }
}
