package io.hkarling;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain10 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team1 = new Team();
//            team1.setName("TEAM_A");
//            em.persist(team1);
//
//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            member2.setTeam(team1);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();

//            String query = "select m.team from Member m";
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//            for(Team t : result) {
//                System.out.println("team = " + t);
//            }

            /* 묵시적인 JOIN 발생 - 가급적이면 쓰지 않는 것이 좋다. */

//            String query = "select t.members.size from Team t";
//            Integer result = em.createQuery(query, Integer.class).getSingleResult();
//            System.out.println("result = " + result);

            /* Sample */

//            String query = "select t.members from Team t";
//            Collection result = em.createQuery(query, Collection.class).getResultList();
//            for(Object o : result) {
//                System.out.println("team = " + o);
//            }

            /* t.members 에서 더이상 탐색이 안된다. -> 묵시적 JOIN 으로 인해서.. 명시적인 JOIN 을 사용 */

//            String query = "select m from Team t join t.members m";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            System.out.println("result = " + result);

            /* 절대로 묵시적 JOIN 쓰지마라. 항상 명시적 JOIN 사용 */

            /* FETCH JOIN - JPQL 에서 성능 최적화를 위해서 제공
               실무에서 매.우. 중요하다. */

            Team team1 = new Team();
            team1.setName("TEAM_A");
            em.persist(team1);
            Team team2 = new Team();
            team2.setName("TEAM_B");
            em.persist(team2);
            Team team3 = new Team();
            team3.setName("TEAM_C");
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("MEMBER_1");
            member1.setTeam(team1);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("MEMBER_2");
            member2.setTeam(team1);
            em.persist(member2);
            Member member3 = new Member();
            member3.setUsername("MEMBER_3");
            member3.setTeam(team2);
            em.persist(member3);
            Member member4 = new Member();
            member4.setUsername("MEMBER_4");
            em.persist(member4);

            em.flush();
            em.clear();

//            // String query = "select m from Member m join  m.team";
//            String query = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for(Member mem : result) {
//                System.out.println("mem = " + mem + ", team = " + mem.getTeam().getName());
//            }
//
//            /* N+1 발생 -> FETCH JOIN 으로 푼다.
//               지연로딩으로 세팅해도 FETCH JOIN 이 우선한다. */
//
//            // String query2 = "select t from Team t join fetch t.members m";
//            String query2 = "select distinct t from Team t join fetch t.members";
//            List<Team> result2 = em.createQuery(query2, Team.class).getResultList();
//            System.out.println("result2.size() = " + result2.size());
////            for(Team t : result2) {
////                System.out.println("team = " + t.getName());
////                for (Member m : t.getMembers()) {
////                    System.out.println("m.getUsername() +  = " + m.getUsername() + " member = " + m);
////                }
////            }

            /* TEAM A 가 두 번 출력되는 것에 주의 -> DISTINCT 의 추가 */

//            // String query3 = "select distinct t from Team t join fetch t.members";
//            // String query3 = "select m from Member m join fetch m.team t"; -> 뒤집는 방법 1
//            String query3 = "select distinct t from Team t"; // Team 의 OneToMany 에 BatchSize 등록
//            List<Team> result3 = em.createQuery(query3, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//            System.out.println("result3.size() = " + result3.size());
//            for(Team t : result3) {
//                System.out.println("team = " + t.getName());
//                for (Member m : t.getMembers()) {
//                    System.out.println("m.getUsername() +  = " + m.getUsername() + " member = " + m);
//                }
//            }

            /* Collection 에서는 곤란하다.
               WARN: HHH000104: firstResult/maxResults specified with collection fetch; applying in memory! */

            /**
             * treat : down-casting 의 가능
             * Select i from Item i where treat(i as Book).author = 'Karl'
             */

            /**
             * Entity 의 직접 사용 -> 기본키 값을 쓴다.
             */
//            //String query = "Select m from Member m where m.id = :memberId";
//            String query = "Select m from Member m where m = :member";
//            Member findMember = em.createQuery(query, Member.class)
//                    //.setParameter("memberId", member1.getId())
//                    .setParameter("member", member1)
//                    .getSingleResult();
//            System.out.println("findMember = " + findMember);

            /**
             * NamedQuery : Entity 에 미리 선언해 두는 쿼리
             * - 정적 쿼리
             * - 어노테이션, XML에서 정의
             * - 애플리케이션 로딩 시점에 초기화 후 재사용, !!!검증가능!!!
             */
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "MEMBER_2")
//                    .getResultList();
//
//            for(Member member: resultList) {
//                System.out.println("member = " + member);
//            }

            /**
             * Bulk process -> SQL 에서의 UPDATE 등...
             * - 쿼리 한 번으로 여러 테이블의 ROW 변경
             * - 주의 : 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리
             *       (1) 벌크 연산을 먼저 실행
             *       (2) 벌크 연산을 수행 후 영속성 컨텍스트 초기화
             */
            Member findMember = em.find(Member.class, member2.getId());
            System.out.println("findMember = " + findMember);
            int count = em.createQuery("update Member m set m.age = 20")
                    //.setParameter("username", "MEMBER_2")
                    .executeUpdate();
            System.out.println("count = " + count);
            System.out.println("findMember = " + findMember + " : 아직 업데이트가 안되어 있음. ");

            em.clear();
            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("findMember2 = " + findMember2);


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

