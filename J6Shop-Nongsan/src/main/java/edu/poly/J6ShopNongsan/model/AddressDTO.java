package edu.poly.J6ShopNongsan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {
    Integer id;
    String first_name;

    String last_name;

    String phone_number;

    String address_line1;

    String address_line2;

    String city;

    String state;

    String postal_code;

    Boolean address_default=false ;
}
