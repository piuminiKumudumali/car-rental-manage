package lk.ijse.carrentalmanage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carrentalmanage.db.DbConnection;
import lk.ijse.carrentalmanage.dto.CarCategoryDto;
import lk.ijse.carrentalmanage.dto.tm.CategoryTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarCategoryFormController {

    public TextField textCarCategoryId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<CategoryTableModel> tableCarCategory;



    @FXML
    private TextArea textDescription;

    @FXML
    private TextField textType;


    public void initialize() throws SQLException {
        System.out.println("CarCategory Form Just Loaded!");

        setCellValueFactory();
        List<CarCategoryDto>categoryList=loadAllCategories();
        setTableData(categoryList);

    }

    public void clearFields(){
        textCarCategoryId.setText("");
        textType.setText("");
        textDescription.setText("");
    }

    private void setFields(CarCategoryDto carCategoryDto) {
        textCarCategoryId.setText(carCategoryDto.getId());
        textType.setText(carCategoryDto.getType());
        textDescription.setText(carCategoryDto.getDescription());
    }

    private void updatedCategoryTable() throws SQLException {
        setCellValueFactory();
        List<CarCategoryDto>categoryList=loadAllCategories();
        setTableData(categoryList);
    }

    private void setTableData(List<CarCategoryDto> categoryList) {

        ObservableList<CategoryTableModel>observableList=FXCollections.observableArrayList();
        for (CarCategoryDto carCategoryDto:categoryList){
               var categoryTableModel= new CategoryTableModel(carCategoryDto.getId(), carCategoryDto.getType(), carCategoryDto.getDescription());
               observableList.add(categoryTableModel);
        }
        tableCarCategory.setItems(observableList);
    }

    private List<CarCategoryDto> loadAllCategories() throws SQLException {
        Connection con=DbConnection.getInstance().getConnection();
        String sql="SELECT*FROM category";
        Statement stm=con.createStatement();
        stm.executeQuery(sql);

        ResultSet resultSet= stm.executeQuery(sql);
        List<CarCategoryDto>categoryList=new ArrayList<>();

        while (resultSet.next()){
            String id= resultSet.getString(1);
            String type=resultSet.getString(2);
            String description=resultSet.getString(3);


            var carCategoryDto=new CarCategoryDto(id,type,description);

            categoryList.add(carCategoryDto);
        }
        return categoryList;
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id=textCarCategoryId.getText();
        String type=textType.getText();
        String description=textDescription.getText();

        try {
            Connection con= DbConnection.getInstance().getConnection();

            String sql= "INSERT INTO category VALUES(?,?,?)";
            PreparedStatement pstm=con.prepareStatement(sql);

            pstm.setString(1,id);
            pstm.setString(2,type);
            pstm.setString(3, description);

            boolean isSaved=pstm.executeUpdate()>0;

            if(isSaved){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Car_Category saved!").show();
            }
        } catch (SQLException e) {
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String typeId=textCarCategoryId.getText();
        String type=textType.getText();
        String description=textDescription.getText();
        try {
            Connection con= DbConnection.getInstance().getConnection();
            String sql= "UPDATE category SET type=?, description=? WHERE typeId=?";
            PreparedStatement pstm= con.prepareStatement(sql);

            pstm.setString(1,type);
            pstm.setString(2,description);
            pstm.setString(3,typeId);

            boolean isUpdated=pstm.executeUpdate()>0;
            if(isUpdated){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Car Category Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String typeId=textCarCategoryId.getText();

        try {
            Connection con=DbConnection.getInstance().getConnection();
            String sql="DELETE FROM category WHERE typeId=?";

            PreparedStatement pstm= con.prepareStatement(sql);

            pstm.setString(1,typeId);

            boolean isDeleted=pstm.executeUpdate()>0;

            if(isDeleted){
                clearFields();
                updatedCategoryTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Car Category Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void btnBackButtenOnAction(ActionEvent actionEvent) {


    }

    public void textIdOnAction(ActionEvent actionEvent) {
        String typeId=textCarCategoryId.getText();
        try {
            Connection con=DbConnection.getInstance().getConnection();
            String sql= "SELECT * FROM category WHERE typeId=?";

            PreparedStatement pstm= con.prepareStatement(sql);
            pstm.setString(1,typeId);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                String categoryId=resultSet.getString(1);
                String type= resultSet.getString(2);
                String description= resultSet.getString(3);

                var carCategoryDto=new CarCategoryDto(categoryId,type,description);
                setFields(carCategoryDto);
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Oops car Category not Found!").show();
            }
        } catch (SQLException e) {
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) throws SQLException {
        clearFields();
    }
}
