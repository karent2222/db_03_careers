/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names:
 * Description: creates Interest entity and allows listing of all interests
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// TODO: finish the object-relational mapping
@Entity
@Table(name = "Interests")
public class Interest {
    @Id
    private String abbrv;

    private String descr;

    public String getAbbrv() {
        return abbrv;
    }

    public void setAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String description) {
        this.descr = description;
    }

    @Override
    public String toString() {
        return "Interest: {abbrv = " + abbrv + ", descr =" + descr + "}";
    }

    public static void main(String[] args) {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();

        // TODO: list all interests
        Query query = em.createQuery("SELECT a FROM Interest a");
        for (Object obj : query.getResultList()) {
            Interest interest = (Interest) obj;
            System.out.println(interest);

        }

        em.close();
    }
}
