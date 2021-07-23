package io.hkarling.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 잘못된 설계에 가까움.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
