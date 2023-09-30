package lk.ijse.carrentalmanage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {
    public AnchorPane nodeTwo;
    public AnchorPane nodeOne;

    public void btnCarCategoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent node =FXMLLoader.load(getClass().getResource("/view/car_category_form.fxml"));
        this.nodeTwo.getChildren().clear();
        this.nodeTwo.getChildren().add(node);
    }

    public void btnCarOnAction(ActionEvent actionEvent) {

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent node=FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.nodeTwo.getChildren().clear();
        this.nodeTwo.getChildren().add(node);
    }

    public void btnRentalsOnAction(ActionEvent actionEvent) throws IOException {
        Parent node=FXMLLoader.load(getClass().getResource("/view/rent_form.fxml"));
        this.nodeTwo.getChildren().clear();
        this.nodeTwo.getChildren().add(node);
    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
    }
}
