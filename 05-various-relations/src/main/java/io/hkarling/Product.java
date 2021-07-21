package io.hkarling;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String name;

    // 4. 다대다 양방향
    /*@ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();*/
}
