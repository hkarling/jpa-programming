package io.hkarling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain04 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
////            member.setTeamId(team.getId());
//            member.setTeam(team);
//
//            em.persist(member);
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
////            Long findTeamId = findMember.getTeamId();
////            Team findTeam = em.find(Team.class, findTeamId);
//            Team findTeam = findMember.getTeam();
//
//            System.out.println("findTeam.getName() = " + findTeam.getName());
//
//            List<Member> members = findTeam.getMembers();
//            for (Member member1 : members) {
//                System.out.println("member1.getUsername() = " + member1.getUsername());
//            }

            Team team = new Team();
            team.setName("TeamA");
            // team.getMembers().add(member); // 1 이거만 하면 DB에 저장안된다
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            // member.setTeam(team); // 2 연관관계에 주인에 매핑한다.
            // 4 연관관계 편의 메서드 작성
            member.assignTeam(team);
            em.persist(member);

            // team.getMembers().add(member); // 3 결론: 양방향 연관관계에서는 순수 객체상태를 고려해서 양쪽에 다 업데이트를 해준다.

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("=============================");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername()); // em.flush(), em.clear() 안하면 아무것도 찍히지 않는다.
            }
            System.out.println("=============================");

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

