/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: creates Interest entity and allows listing of all interests
 */

import javax.persistence.*;

// ORM for Interests table
@Entity
@Table(name = "Interests")
public class Interest {
    @Id
    private String abbrv; // primary key for Interests table 

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

    // Overload toString() method to display all Interest attributes. 
    @Override
    public String toString() {
        return "Interest: [abbrv = " + abbrv + ", descr = " + descr + "]";
    }

    public static void main(String[] args) {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();

        // Select all from Interests table and loop through the result set to print each interest. 
        Query query = em.createQuery("SELECT a FROM Interest a");
        for (Object obj : query.getResultList()) {
            Interest interest = (Interest) obj;
            System.out.println(interest);
        }

        em.close();
    }
}
