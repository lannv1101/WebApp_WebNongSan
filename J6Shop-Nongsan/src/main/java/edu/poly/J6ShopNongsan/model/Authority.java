package edu.poly.J6ShopNongsan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data

@NoArgsConstructor
@AllArgsConstructor

public class Authority  implements Serializable {

    private Integer id;

    private AccountDTO account;

}