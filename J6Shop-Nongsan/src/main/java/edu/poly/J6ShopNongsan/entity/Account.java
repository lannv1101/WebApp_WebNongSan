package edu.poly.J6ShopNongsan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account  implements Serializable {
    @Id
    @Column(length = 64)
    String username;
    String password;
    String email;
    String photo;
    Boolean enable;
    @Column(length = 64)
    String verification_code;
    @Temporal(TemporalType.DATE)
    @Column(name = "Register_date")
    Date register_date = new Date();

    @Column(length = 45, nullable = false )
    String first_name;
    @Column(length = 45, nullable = false )
    String last_name;
    @Column(length = 15, nullable = false )
    String phone_number;
    @Column(length =64, nullable = false )
    String address_line1;
    @Column(length =64, nullable = false )
    String address_line2;
    @Column(length =45, nullable = false )
    String city;
    @Column(length =45, nullable = false )
    String state;
    @Column(length =10, nullable = false )
    String postal_code;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Addresses> addresses;
}
