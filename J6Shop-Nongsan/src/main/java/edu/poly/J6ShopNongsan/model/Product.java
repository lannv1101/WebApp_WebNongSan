package edu.poly.J6ShopNongsan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product  implements Serializable {

    Integer id;
    String name;
    String image;
    Double price;

    Date createDate = new Date();
    Boolean available;
    @ManyToOne
    @JoinColumn(name = "Categoryid")
    Category category;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
}

