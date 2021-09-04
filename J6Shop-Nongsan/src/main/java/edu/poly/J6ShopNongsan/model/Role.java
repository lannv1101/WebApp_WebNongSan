package edu.poly.J6ShopNongsan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Data

@NoArgsConstructor
@AllArgsConstructor

public class Role  implements Serializable {

    private String id;
    private String name;

}