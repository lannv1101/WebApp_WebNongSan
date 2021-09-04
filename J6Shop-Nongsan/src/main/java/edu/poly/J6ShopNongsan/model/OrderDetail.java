package edu.poly.J6ShopNongsan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetail  implements Serializable {

    Long id;
    Double price;
    Integer quantity;

}