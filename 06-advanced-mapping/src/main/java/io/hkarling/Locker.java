package io.hkarling;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;

    // 3. 일대일 양방향
    @OneToOne(mappedBy = "locker")
    private Member member;
}
