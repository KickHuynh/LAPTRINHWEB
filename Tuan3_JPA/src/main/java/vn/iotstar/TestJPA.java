package vn.iotstar;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.iotstar.entity.Category;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataSource");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Category c = new Category();
        c.setName("Thử nghiệm JPA");
        em.persist(c);

        tx.commit();
        em.close();
        emf.close();

        System.out.println(">>> DONE, check database jpast5!");
    }
}
