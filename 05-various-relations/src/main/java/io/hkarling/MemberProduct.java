package io.hkarling;

import javax.persistence.*;
import java.time.LocalDateTime;

// 4. 중간 테이블을 둔다.
@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Integer amount;
    private Integer price;
    private LocalDateTime orderDateTime;

}
