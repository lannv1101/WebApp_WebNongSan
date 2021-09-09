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
@Table(name = "Orders")
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    Date createDate = new Date();
    Float amount;
    Integer status;
    @Column(length = 45, nullable = false )
    String payment_method;
    
    
//    @JsonIgnore     //dung jsonIgnore de lay dc account.username // khi them lai ko lay dc username
    @ManyToOne
    @JoinColumn(name = "username")
    Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}