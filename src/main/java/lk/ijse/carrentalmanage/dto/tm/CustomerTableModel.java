package lk.ijse.carrentalmanage.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTableModel {
    private String id;
    private String nationalId;
    private String firstName;
    private String middleName;
    private String address;
    private String phoneNumber;
}
