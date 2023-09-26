package lk.ijse.carrentalmanage.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarCategoryDto {
    private String id;
    private String type;
    private  String description;

}
