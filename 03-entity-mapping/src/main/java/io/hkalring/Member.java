package io.hkalring;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// @UniqueConstraint(name = ) // unique 제약조건은 여기다가 넣는다.
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 50) // sequence 생성
// @TableGenerator(name="MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCE", pkColumnValue = "MEMBER_SEQ", initialValue = 1, allocationSize = 50) // 테이블 전략
public class Member {

    @Id // 기본키 매핑
    // @GeneratedValue(strategy = GenerationType.AUTO) // AUTO, IDENTITY (DB에 위임), SEQUENCE (sequence object 를 생성)
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // commit 시점까지 엔티티의 pk를 알 수 없다. 따라서 persist 한 즉시 insert 쿼리가 실행된다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator") // 시퀀스 전략
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR") // 테이블 전략
    private Long id;

    @Column(name = "name",
            insertable = true,
            updatable = true,
            nullable = false,
            //unique = true // 이거 여기다가 안쓴다.
            length = 100,
            columnDefinition = "varchar(100) default 'EMPTY'"
    )
    private String username;

    // @Column(precision = ) // 소숫점
    private Integer age;

    @Enumerated(EnumType.STRING) // EnumType.STRING: 이름(이걸 쓰는쪽이 맞다), EnumType.ORDINAL: enum 아이디
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // LocalDateTime 쓸 경우 생략
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // String, char[] 등 문자는 Clob, byte 등 다른 것들은 Blob
    private String description;

    @Transient
    private int temp;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
