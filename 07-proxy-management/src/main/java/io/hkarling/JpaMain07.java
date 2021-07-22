package io.hkarling;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class JpaMain07 {

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

//            Member member1 = new Member();
//            member1.setUsername("MEMBER1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); // Proxy
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
////            refMember.getUsername(); // 강제 초기화
//            Hibernate.initialize(refMember); // 강제 초기화
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

//            Team team = new Team();
//            team.setName("TEAM_A");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
//            System.out.println("========================");
//            findMember.getTeam().getName();
//            System.out.println("========================");

            /* 가급적이면 실무에서는 지연로딩만 사용하기를 권장. 즉시로딩은 JPQL 에서 N+1 문제 발생.
               @OneToOne, @ManyToOne 기본은 즉시로딩 -> 지연로딩으로 변경
               @ManyToOne, @ManyToMany 는 기본이 지연로딩 */

//            Team team = new Team();
//            team.setName("TEAM_A");
//            em.persist(team);
//
//            Team team2 = new Team();
//            team2.setName("TEAM_B");
//            em.persist(team2);
//
//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setTeam(team);
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setUsername("MEMBER_B");
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            Member findMember = em.find(Member.class, member.getId());
////            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

            /* N+1 문제 해결 : join fetch */

//            Child child1 = new Child();
//            Child child2 = new Child();
//            Parent parent = new Parent();
//
//            parent.addChild(child1);
//            parent.addChild(child2);
//
////            em.persist(child1);
////            em.persist(child2);
//            em.persist(parent);

            /* 1. Child 와 Parent 의 라이프 사이클이 동일할 때, 2. Child 의 소유가 오직 Parent 에게만 존재할 때 */

            Child child1 = new Child();
            Child child2 = new Child();
            Parent parent = new Parent();

            parent.addChild(child1);
            parent.addChild(child2);
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);

            /* 상위 엔티티 삭제 시 하위 엔티티도 삭제 -> 고아 객체 */

            /* CascadeType.ALL + orphanRemoval = true 모두 ON
               부모 객체를 통해서 자식 객체의 생명주기를 관리한다.
               DDD : Aggregate Root 개념을 구현할 때 유용 */


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

