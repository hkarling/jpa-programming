package io.hkarling;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn // 상속클래스 구분 컬럼
public abstract class Item { // 추상클래스로 작성하는 것이 좋다.
    // 상속 시 기본 전략은 SINGLE-TABLE 전략이다. 전체 상속 클래스들의 정보들이 한 테이블에 저장된다.
    // 기본은 JOINED 전략을 사용하는 것으로 한다.
    // TABLE_PER_CLASS 전략은 사용하지 않는 편이 좋다.

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
