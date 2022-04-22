/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: creates an Employer entity with a 1-many mapping to EmployerInterest and allows listing of all employers
 */

import javax.persistence.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

// ORM for EmployerInterest combinational primary key 
class EmployerInterestPK implements Serializable {
    private int id;
    private String abbrv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbrv() {
        return abbrv;
    }

    public void setAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    @Override
    public String toString() {
        return "id = " + id + "\t\tabbrv = " + abbrv;
    }
}

// ORM for EmployerInterests table, uses EmployerInterestPK as @EmbeddedId
@Entity
@Table(name = "EmployerInterests")
class EmployerInterest implements Serializable {
    @EmbeddedId
    private EmployerInterestPK empIntPK;

    public EmployerInterestPK getEmployerInterestPK() {
        return empIntPK;
    }

    public void setEmployerInterestPK(EmployerInterestPK empIntPK) {
        this.empIntPK = empIntPK;
    }

    @Override
    public String toString() {
        return "\n" + empIntPK;
    }

}

// ORM For Students table, has a one-to-many relationship with EmployerInterests table.
@Entity
@Table(name = "Employers")
class Employer {
    @Id
    private int id; // primary key for Employers table. 

    private String name, location, forprofit, govern;
    private int size;

    @OneToMany(targetEntity = EmployerInterest.class)
    @JoinColumn(name = "id")
    private List<EmployerInterest> empIntList; //list to join Employers and EmployerInterests tables. FK from Employers table is PK id. 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getForprofit() {
        return forprofit;
    }

    public void setForprofit(String forprofit) {
        this.forprofit = forprofit;
    }

    public String getGovern() {
        return govern;
    }

    public void setGovern(String govern) {
        this.govern = govern;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<EmployerInterest> getEmpIntList() {
        return empIntList;
    }

    public void setEmpIntList(List<EmployerInterest> empIntList) {
        this.empIntList = empIntList;
    }

    //Overload toString() method to display all Employer attributes and their interests based on the employer id. 
    @Override
    public String toString() {
        String interests = "";
        for (EmployerInterest empint : this.empIntList) {
            interests = interests + empint.getEmployerInterestPK().getAbbrv() + " ";
        }
        return "\nEmployer:\nid = " + id + "\nname = " + name + "\nsize = " + size + "\nlocation = " + location + "\nforprofit = "
                + forprofit + "\ngovern = " + govern + "\ninterests = [ " + interests + "]";
    }

    // contructor to initialize employer interests list 
    Employer() {
        empIntList = new ArrayList<EmployerInterest>();
    }

    public static void main(String[] args) throws SQLException {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();
        try {
            
            // Query intQuery = em.createQuery("SELECT i from EmployerInterest i");
            // List<EmployerInterest> empInterests = new ArrayList<EmployerInterest>();
            // EmployerInterest empInt;
            // for (Object obj : intQuery.getResultList()) {
            //     empInt = (EmployerInterest) obj;
            //     empInterests.add(empInt);
            // } < -- don't actually need this code when using @Join, wrote it before implementing the ManyToOne relationship. This might come in handy when refactoring for ManyToMany so leaving it commented out for now. 

            // Select all from Employers and loop through the result set and print our each employer with their specific interests 
            Query query = em.createQuery("SELECT a FROM Employer a");
            for (Object obj : query.getResultList()) {
                Employer employer = (Employer) obj;
                // for (EmployerInterest empi : empInterests) {
                //     if (empi.getEmployerInterestPK().getId() == employer.getId()) {
                //         employer.empIntList.add(empi);
                //     }
                // } < -- don't actually need this code when using @Join, wrote it before implementing the ManyToOne relationship.This might come in handy when refactoring for ManyToMany so leaving it commented out for now. 
                System.out.println(employer);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close(); // close connection to db03
        }

    }
}
