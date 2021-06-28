package io.hkalring;

import javax.persistence.*;
import java.util.List;

public class JpaMain02 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("Member02");
//            // 영속
//            System.out.println("============ BEFORE ============");
//            em.persist(member);
//            System.out.println("============ AFTER ============");
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            // 1차 캐시 확인
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L); // 두번째는 쿼리를 날리지 않는다.
//            System.out.println("findMember1 == findMember2 : " + (findMember1 == findMember2)); // 영속 엔티티의 동일성 보장

//            // Transaction 을 지원하는 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("========================"); // 이 뒤에 쿼리가 날아감

//            // Dirty check: 변경 감지
//            Member findMember = em.find(Member.class, 150L);
//            findMember.setName("ZZZZ");
//            // em.persist(findMember); // 이건 안쓰는거다..
//            System.out.println("========================");

//            // flush - em.flush, 트랜잭선 커밋, JPQL 쿼리 실행
//            Member member = new Member(200L, "Member200");
//            em.persist(member);
//            System.out.println("============ BEFORE ============");
//            em.flush();
//            System.out.println("============ AFTER ============");
//            // em.setFlushMode(FlushModeType.AUTO); // FlushModeType.AUTO (default), FlushModeType.COMMIT (이를 쓸때는 이유가 필요함)

            // detached
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("AAAA");
            em.detach(findMember); // update 쿼리가 날아가지 않는다.
            System.out.println("========================");
            em.clear(); // 영속성 컨텍스트 초기화
            Member findMember2 = em.find(Member.class, 150L);
            System.out.println("========================");

            tx.commit(); // 실질적으로 persist 쿼리가 날아감
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

