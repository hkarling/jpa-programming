package io.hkarling;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // 1. 다대일 양방향 매핑
    /*@OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();*/

    // 2. 일대다 단방향 매핑
    /*@OneToMany
    @JoinColumn(name="TEAM_ID")
    private List<Member> members = new ArrayList<>();*/

}
