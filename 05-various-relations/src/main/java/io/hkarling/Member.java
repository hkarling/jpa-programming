package io.hkarling;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 1. 다대일 단방향
    /*@ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;*/

    // 2. 일대다 양방향이란건 없다.

    // 3. 일대일 단방향 : 다대일 단방향 매핑
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 4. 다대다 관계 : 관계형 데이터베이스는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없다.
    // @ManyToMany -> @ManyToOne, @OneToMany로 변경. JoinTable을 중간 테이블로 승격
    /*@ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();*/
}
