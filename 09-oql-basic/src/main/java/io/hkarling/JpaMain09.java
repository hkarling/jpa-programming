package io.hkarling;

import org.hibernate.Criteria;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain09 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            List<Member> resultList = em.createQuery("select m from Member m where m.name like '%kim%'", Member.class)
//                    .getResultList();
//            for(Member member : resultList) {
//                System.out.println("member = " + member.getName());
//            }

            /* Criteria :
               - 문자가 아닌 자바코드로 JPQL 작성이 가능
               - JPA 공식 스펙
               - 근데 안 쓴다 -_- */
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m);
//            cq = cq.where(cb.equal(m.get("name"), "kim"));
//            List<Member> resultList = em.createQuery(cq).getResultList();

            /* QueryDSL
               - 컴파일 시 문법 오류를 찾을 수 있음
               - 동적 쿼리 작성 편리함
               - 단순하고 쉬운 편이다 : 실무 권장 */
//            Member member = new Member();
//            member.setName("MEMBER_A");
//            em.persist(member);
//            // flush -> commit, query execution
//
//            /* native Query */
//            System.out.println("========================================");
//            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, username, city, street, zipcode from Member", Member.class).getResultList();
//
//            for(Member m : resultList) {
//                System.out.println("m = " + m.getName());
//            }

            /* JPQL */
//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setAge(10);
//            em.persist(member);

//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            List<Member> resultList = query1.getResultList();
//
//            TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.id = 0", Member.class);
//            Member result = query4.getSingleResult();
//            // Spring Data JPA -> null 이나 Optional 로 받는다.
//            System.out.println("result = " + result);

            /* query.getResultList() : 결과가 하나 이상일 때 리스트 반환, 없는 경우 빈 리스트 반환
               query.getSingleResult() : 결과가 반드시 하나
                 - 결과가 없으면 : javax.persistence.NoResultException
                 - 결과가 둘 이상이면 : javax.persistence.NonUniqueResultException */

//            Member result= em.createQuery("select m from Member m where m.username  = :username", Member.class)
//                    .setParameter("username", "MEMBER_A")
//                    .getSingleResult();
//            System.out.println("result = " + result);

            /* Projection : SELECT 절에 조회 대상을 지정하는 것
               - 프로젝션 대상 : Entity, Embedded, Scalar type, etc. */

//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setAge(10);
//            em.persist(member);
//
//            List<Member> result = em.createQuery("select m from Member m ", Member.class)
//                    .getResultList();
//            Member findMember = result.get(0);
//            findMember.setAge(20);
//
//            // List<Team> result2 = em.createQuery("select m.team from Member m ", Team.class).getResultList(); // 추천 안함
//            List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class).getResultList(); // join 은 명시적으로 하는 것이 좋다.
//
//            // Embedded Type Projection
//            List<Address> result3 = em.createQuery("select o.address from Order o", Address.class).getResultList();
//
//            List result4 = em.createQuery("select m.username, m.age from Member m").getResultList();
//
//            for(Object o : result4) {
//                Object[] temp = (Object[]) o;
//                System.out.println("usename : " + temp[0]);
//                System.out.println("age : " + temp[1]);
//            }
//
//            List<MemberDTO> result5 = em.createQuery("select new io.hkarling.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            for(MemberDTO memberDTO : result5) {
//                System.out.println("usename : " + memberDTO.getUsername());
//                System.out.println("age : " + memberDTO.getAge());
//            }

            /* Paging */
//            for(int i = 0 ; i < 100 ; i++ ) {
//                Member member = new Member();
//                member.setUsername("MEMBER_" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//            em.flush();
//            em.clear();
//
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for(Member m : resultList) {
//                System.out.println("member = " + m);
//            }

            /* Join
               - 내부 조인 : select m from Member m [inner] join m.team t
               - 외부 조인 : select m from Member m left [outer] join m.team t
               - 세타 조인 : select count(m) from Member m, Team t where m.username = t.name */

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            String query1 = "select m from Member m join m.team t";
//            List<Member> resultList1 = em.createQuery(query1, Member.class).getResultList();
//
//            String query2 = "select m from Member m left outer join m.team t";
//            List<Member> resultList2 = em.createQuery(query2, Member.class).getResultList();
//
//            String query3 = "select m from Member m, Team t where m.username = t.name";
//            List<Member> resultList3 = em.createQuery(query3, Member.class).getResultList();
//
//            // ON 의 활용
//            String query4 = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> resultList4 = em.createQuery(query4, Member.class).getResultList();
//
//            String query5 = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> resultList5 = em.createQuery(query5, Member.class).getResultList();

            /* 서브 쿼리
               - JPA 서브 쿼리 한계
                 - WHERE, HAVING 절에서만 서브쿼리 사용 가능
                 - 하이버네이트에서는 SELECT 절에서도 가능
                 - FROM 절에서는 join 으로 풀어라 */

            /* JPQL 의 타입 */
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("MEMBER_A");
//            member.setAge(10);
//            member.setUserType(UserType.USER);
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            String query1 = "select m.username, 'Hello', true from Member m join m.team t " +
//                            "where m.userType = :userType";
//            List<Object[]> resultList1 = em.createQuery(query1)
//                    .setParameter("userType", UserType.USER).getResultList();
//
//            for (Object[] objects: resultList1) {
//                System.out.println("objects[0] = " + objects[0]);
//                System.out.println("objects[1] = " + objects[1]);
//                System.out.println("objects[2] = " + objects[2]);
//            }

            /* 조건식 */
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setUserType(UserType.USER);
//            member.setTeam(team);
//            em.persist(member);
//
//            String query = "select case when m.age <= 10 then '학생요금' when m.age >= 60 then '경로요금' else '일반요금' end from Member m";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for(String text : resultList) {
//                System.out.println("text = " + text);
//            }
//
//            // COALESCE
//            String query2 = "select coalesce(m.username, '이름없는 회원') from Member m";
//            List<String> resultList2 = em.createQuery(query2, String.class).getResultList();
//            for(String text : resultList2) {
//                System.out.println("text = " + text);
//            }
//
//            // NULLIF
//            String query3 = "select nullif(m.username, '관리자') from Member m";
//            List<String> resultList3 = em.createQuery(query3, String.class).getResultList();
//            for(String text : resultList3) {
//                System.out.println("text = " + text);
//            }

            /* JPQL 기본 함수
               CONCAT, SUBSTRING, TRIM, LOWER, UPPER, LENGTH, LOCATE, ABS, SQRT, MOD, SIZE, INDEX(등등...) */

            /* 사용자 정의 함수 호출
               - Dialect 를 작성하여 등록하여준다 */
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select function('group_concat', m.username) from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for(String i : result) {
                System.out.println("i = " + i);
            }

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

