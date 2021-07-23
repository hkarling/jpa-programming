package io.hkarling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain08 {

    public static void main(String[] args) {

//        int a = 10;
//        int b = a;
//        a = 20;
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
        /* primitive type 의 경우 값을 복사, side-effect 가 없다. */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setName("Member_A");
//            member.setHomeAddress(new Address("CITY", "STREET", "ZIPCODE"));
//            em.persist(member);
            /* 값 타입 저장 */

//            Address address = new Address("CITY", "STREET", "ZIPCODE");
//
//            Member member = new Member();
//            member.setName("Member_A");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setName("Member_B");
//            member2.setHomeAddress(address);
//            em.persist(member2);
//
//            // member.getHomeAddress().setCity("newCITY");

            /* side effect 발생 member2 의 city 값도 바뀐다. 이를 의도적으로 한다면 값 타입이 아니라 Entity 로 만든다. */

//            Address copyAddress = new Address("newCITY", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(copyAddress);

            /* 객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다. -> 객체의 공유 참조는 막을 수 없다.
               값 타입은 불변 객체(immutable object)로 설계해야 한다. 생성자에서만 값을 설정 ex) Integer, String */

//            Address address1 = new Address("CITY", "STREET", "ZIPCODE");
//            Address address2 = new Address("CITY", "STREET", "ZIPCODE");
//
//            System.out.println("address1 == address2 : " + (address1 == address2)); // false
//            System.out.println("address1.equals(address2) : " + (address1.equals(address2))); // true: override 한 equals 작동

            /* 값 타입의 비교 : 동등성 비교를 기본으로 한다. equals, hashCode 메소드 재정의 한다. */

//            Member member = new Member();
//            member.setName("MEMBER_A");
//            member.setHomeAddress(new Address("CITY", "ADDRESS", "ZIPCODE"));
//
//            member.getFavoriteFoods().add("PIZZA");
//            member.getFavoriteFoods().add("HAMBURGER");
//            member.getFavoriteFoods().add("PASTA");
//
//            member.getAddressHistory().add(new Address("OLDCITY1", "OLDSTREET1", "OLDZIPCODE1"));
//            member.getAddressHistory().add(new Address("OLDCITY2", "OLDSTREET2", "OLDZIPCODE2"));
//            em.persist(member);

            /* 값 타입은 영속성 전이 + 고아 객체 제거를 필수로 가진다 */

//            em.flush();
//            em.clear();
//
//            System.out.println("============================================");
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("============================================");
//            for (Address add : findMember.getAddressHistory()) {
//                System.out.println(add.getCity() + " " + add.getStreet() + " " + add.getZipcode());
//            }
//            System.out.println("============================================");
//            for (String food : findMember.getFavoriteFoods()) {
//                System.out.println("food = " + food);
//            }

            /* 지연로딩이 기본이다. */

//            findMember.getFavoriteFoods().remove("PIZZA");
//            findMember.getFavoriteFoods().add("RAMEN");

//            findMember.getAddressHistory().remove(new Address("OLDCITY1", "OLDSTREET1", "OLDZIPCODE1"));
//            findMember.getAddressHistory().add(new Address("OLDCITY3", "OLDSTREET3", "OLDZIPCODE3"));

            /* 실무에서는 값 타입 컬렉션 대신 일대다 엔티티로 설계하는 것이 좋다. */

            Member member = new Member();
            member.setName("MEMBER_A");
            member.setHomeAddress(new Address("CITY", "ADDRESS", "ZIPCODE"));

            member.getFavoriteFoods().add("PIZZA");
            member.getFavoriteFoods().add("HAMBURGER");
            member.getFavoriteFoods().add("PASTA");

            member.getAddressHistory().add(new AddressHist(new Address("OLDCITY1", "OLDSTREET1", "OLDZIPCODE1")));
            member.getAddressHistory().add(new AddressHist(new Address("OLDCITY2", "OLDSTREET2", "OLDZIPCODE2")));
            em.persist(member);

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

