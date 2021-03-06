package edu.poly.J6ShopNongsan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Addresses")
public class Addresses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
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

    Boolean address_default;

    @ManyToOne
    @JoinColumn(name = "username")
    Account account;
}
