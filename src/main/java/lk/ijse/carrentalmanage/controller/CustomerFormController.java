package lk.ijse.carrentalmanage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carrentalmanage.db.DbConnection;
import lk.ijse.carrentalmanage.dto.CarCategoryDto;
import lk.ijse.carrentalmanage.dto.CustomerDto;
import lk.ijse.carrentalmanage.dto.tm.CategoryTableModel;
import lk.ijse.carrentalmanage.dto.tm.CustomerTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMiddleName;

    @FXML
    private TableColumn<?, ?> colNationalId;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<CustomerTableModel> tableCustomer;

    public TextField textNationalId;
    public TextField textAddress;
    public TextField textFirstName;
    public TextField textLastName;
    public TextField textPhoneNumber;
    public TextField textMiddleName;
    public TextField textCustomerId;


    public void initialize() throws SQLException {
        System.out.println("Customer Form Just Loaded");

        setCellValueFactory();
        List<CustomerDto> customerList=loadAllCustomers();
        setTableData(customerList);
    }



    private void updatedCategoryTable() throws SQLException {
        setCellValueFactory();
        List<CustomerDto>customerList=loadAllCustomers();
        setTableData(customerList);
    }
    private void clearFields() {
        textCustomerId.setText("");
        textNationalId.setText("");
        textFirstName.setText("");
        textMiddleName.setText("");
        textLastName.setText("");
        textAddress.setText("");
        textPhoneNumber.setText("");
    }




    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNationalId.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }
    private List<CustomerDto> loadAllCustomers() throws SQLException {
        Connection con= DbConnection.getInstance().getConnection();
            String sql="SELECT*FROM customer";
            Statement stm=con.createStatement();
            stm.executeQuery(sql);

            ResultSet resultSet= stm.executeQuery(sql);
            List<CustomerDto>customerList=new ArrayList<>();

            while (resultSet.next()){
                String id= resultSet.getString(1);
                String nationalId=resultSet.getString(2);
                String firstName=resultSet.getString(3);
                String middleName=resultSet.getString(4);
                String address=resultSet.getString(6);
                String phoneNumber=resultSet.getString(7);

                var customerDto=new CustomerDto(id,nationalId,firstName,middleName,address,phoneNumber);
                customerList.add(customerDto);
        }
            return customerList;
    }
    private void setTableData(List<CustomerDto> customerList) {
        ObservableList<CustomerTableModel> observableList= FXCollections.observableArrayList();
        for (CustomerDto customerDto:customerList){
            var customerTableModel= new CustomerTableModel(customerDto.getId(), customerDto.getNationalId(), customerDto.getFirstName(),customerDto.getMiddleName(),customerDto.getAddress(),customerDto.getPhoneNumber());
            observableList.add(customerTableModel);
        }
        tableCustomer.setItems(observableList);
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id=textCustomerId.getText();
        String nationalId=textNationalId.getText();
        String firstName=textFirstName.getText();
        String middleName=textMiddleName.getText();
        String lastName=textLastName.getText();
        String address=textAddress.getText();
        String phoneNumber=textPhoneNumber.getText();

        try {
            Connection con= DbConnection.getInstance().getConnection();

            String sql= "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstm=con.prepareStatement(sql);

            pstm.setString(1,id);
            pstm.setString(2,nationalId);
            pstm.setString(3, firstName);
            pstm.setString(4,middleName);
            pstm.setString(5,lastName);
            pstm.setString(6,address);
            pstm.setString(7,phoneNumber);

            boolean isSaved=pstm.executeUpdate()>0;
            if(isSaved){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=textCustomerId.getText();
        String national_id=textNationalId.getText();
        String first_name=textFirstName.getText();
        String middle_name=textMiddleName.getText();
        String last_name=textLastName.getText();
        String address=textAddress.getText();
        String phone_num=textPhoneNumber.getText();
        try {
            Connection con= DbConnection.getInstance().getConnection();
            String sql= "UPDATE customer SET national_id=?,first_name=?,middle_name=?,last_name=?,address=?,phone_num=? WHERE id=?";
            PreparedStatement pstm= con.prepareStatement(sql);

            pstm.setString(1,national_id);
            pstm.setString(2,first_name);
            pstm.setString(3,middle_name);
            pstm.setString(4,last_name);
            pstm.setString(5,address);
            pstm.setString(6,phone_num);
            pstm.setString(7,id);

            boolean isUpdated=pstm.executeUpdate()>0;
            if(isUpdated){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=textCustomerId.getText();

        try {
            Connection con=DbConnection.getInstance().getConnection();
            String sql="DELETE FROM customer WHERE id=?";

            PreparedStatement pstm= con.prepareStatement(sql);

            pstm.setString(1,id);

            boolean isDeleted=pstm.executeUpdate()>0;

            if(isDeleted){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnBackButtenOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }



    public void textIdOnAction(ActionEvent actionEvent) {
        String id=textCustomerId.getText();
        try {
            Connection con=DbConnection.getInstance().getConnection();
            String sql= "SELECT * FROM customer WHERE id=?";

            PreparedStatement pstm= con.prepareStatement(sql);
            pstm.setString(1,id);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                id=resultSet.getString(1);
                String nationalId= resultSet.getString(2);
                String firstName= resultSet.getString(3);
                String middleName=resultSet.getString(4);
                String lastName=resultSet.getString(5);
                String address=resultSet.getString(6);
                String phoneNumber=resultSet.getString(7);

                var customerDto=new CustomerDto(id,nationalId,firstName,middleName,lastName,address,phoneNumber);
                setFields(customerDto);
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Oops Customer not Found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setFields(CustomerDto customerDto) {
        textCustomerId.setText(customerDto.getId());
        textNationalId.setText(customerDto.getNationalId());
        textFirstName.setText(customerDto.getFirstName());
        textMiddleName.setText(customerDto.getMiddleName());
        textLastName.setText(customerDto.getLastName());
        textAddress.setText(customerDto.getAddress());
        textPhoneNumber.setText(customerDto.getPhoneNumber());
    }

    public void textNationalIdOnAction(ActionEvent actionEvent) {

    }
}
