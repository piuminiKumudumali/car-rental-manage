package lk.ijse.carrentalmanage.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDto {

    private String id;
    private String nationalId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public CustomerDto(String id, String nationalId, String firstName, String middleName, String address, String phoneNumber) {
        this.id=id;
        this.nationalId=nationalId;
        this.firstName=firstName;
        this.middleName=middleName;
        this.address=address;
        this.phoneNumber=phoneNumber;

    }
}
