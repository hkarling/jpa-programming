package io.hkalring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain03 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("AAA");

            Member member2 = new Member();
            member2.setUsername("BBB");

            Member member3 = new Member();
            member3.setUsername("CCC");

            System.out.println("===================================");
            // allocationSize = 50 : call next value for member_seq 에서 50개 땡겨온다
            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            System.out.println("===================================");

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

