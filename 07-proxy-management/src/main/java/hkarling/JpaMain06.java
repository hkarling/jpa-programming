package hkarling;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain06 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setUsername("MEMBER_01");
//            Team team = new Team();
//            team.setName("TEAM_ID");
//            em.persist(team);
//            em.persist(member);
////            Member findMember = em.find(Member.class, 1L);
////            printMember(findMember);
//            em.flush();
//            em.clear();
//
//            //Member findMember = em.find(Member.class, member.getId()); // 진짜 Entity 객체를 반환
//            Member findMember = em.getReference(Member.class, member.getId()); // 가짜 Entity 객체를 반환
//            System.out.println("findMember.getClass() = " + findMember.getClass());
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getUsername());

            /*
             * 프록시 객체는 처음 사용할 때 한번만 초기화
             * 엔티티를 == 비교하지 말것
             */

//            Member member1 = new Member();
//            member1.setUsername("MEMBER1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("MEMBER2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.getReference(Member.class, member2.getId());
//
//            System.out.println("m1 == m2 : "  + (m1.getClass() == m2.getClass()));
//            System.out.println("m1 instanceof Member : "  + (m1 instanceof Member));
//            System.out.println("m2 instanceof Member : "  + (m2 instanceof Member));

            /* instanceof 로 타입체크를 한다. */


//            Member member1 = new Member();
//            member1.setUsername("MEMBER1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member m1 = em.find(Member.class, member1.getId());
//            System.out.println("m1 = " + m1.getClass());
//
//            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference = " + reference.getClass());
//            System.out.println("a == a: " +(m1 == reference));

            /* 영속성 컨텍스트에 이미 존재하는 엔티티의 경우 getReference 로 부러와도 실제 엔티티를 반환한다. */

//            Member member1 = new Member();
//            member1.setUsername("MEMBER1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass());
//
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass());
//            System.out.println("refMember == findMember: " +(refMember == findMember));

            /* 순서가 반대여도 두 엔티티가 같음을 보장한다. */

//            Member member1 = new Member();
//            member1.setUsername("MEMBER1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass());
//
//            em.detach(refMember);
////            em.clear();
////            em.close();
//
//            System.out.println("refMember.getUsername() = " + refMember.getUsername()); // 오류난다.

            /* 프록시의 초기화는 영속성 컨텍스트 관리 하에서 가능하다. org.hibernate.LazyInitializationException */

            Member member1 = new Member();
            member1.setUsername("MEMBER1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); // Proxy
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
//            refMember.getUsername(); // 강제 초기화
            Hibernate.initialize(refMember); // 강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void printMember(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
    }

    public static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}

