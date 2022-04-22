/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: creates a Student entity with a 1-many mapping to StudentInterest and allows listing of all students
 */


import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;


// ORM for StudentInterest combinational primary key 
class StudentInterestPK implements Serializable {
    private String email, abbrv;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbbrv() {
        return abbrv;
    }

    public void setIntAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    @Override
    public String toString() {
        return "email = " + email + "\t\t\t abbrv = " + abbrv;
    }
}

// ORM for StudentInterests table, uses StudentInterestPK as @EmbeddedId
@Entity
@Table(name = "StudentInterests")
class StudentInterest implements Serializable {
    @EmbeddedId
    private StudentInterestPK stIntPK;

    public StudentInterestPK getStudentInterestPK() {
        return stIntPK;
    }

    public void setStudentInterestPK(StudentInterestPK stIntPK) {
        this.stIntPK = stIntPK;
    }

    @Override
    public String toString() {
        return "\n" + stIntPK;
    }
}

// ORM For Students table, has a one-to-many relationship with StudentInterests
// table.
@Entity
@Table(name = "Students")
class Student {
    @Id
    private String email; // primary key for Students table

    private String name, major, graduation;

    @OneToMany(targetEntity = StudentInterest.class)
    @JoinColumn(name = "email")
    private List<StudentInterest> stIntList; // list to join Students and StudentInterests tables. FK from Students
                                             // table is PK email

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public List<StudentInterest> getStudentInterests() {
        return stIntList;
    }

    public void setStudentInterest(List<StudentInterest> stIntList) {
        this.stIntList = stIntList;
    }

    // Overload toString() method to display all Student attributes and student
    // interests based on student's email.
    @Override
    public String toString() {
        String interests = "";
        for (StudentInterest stint : this.stIntList) {
            interests = interests + stint.getStudentInterestPK().getAbbrv() + " ";
        }
        return "Student: \nemail = " + email + "\nname = " + name + "\nmajor = " + major + "\ngraduation = "
                + graduation + "\ninterests = [ " + interests + "]\n";
    }

    // Constructor to initialize student interest list.
    Student() {
        stIntList = new ArrayList<StudentInterest>();
    }

    public static void main(String[] args) throws SQLException {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();
        try {

            // < -- don't actually need this code when using @Join, wrote it before
            // implementing the ManyToOne relationship.This might come in handy when
            // refactoring for ManyToMany so leaving it commented out for now. -- >

            // Query intQuery = em.createQuery("SELECT i from StudentInterest i");
            // List<StudentInterest> stInterests = new ArrayList<StudentInterest>();
            // StudentInterest stInt;
            // for(Object obj: intQuery.getResultList()){
            // stInt = (StudentInterest) obj;
            // stInterests.add(stInt);
            // } 

            // Select all from Students and loop through the result set to print each
            // student, including a list of their specific interests.
            Query query = em.createQuery("SELECT a FROM Student a");
            for (Object obj : query.getResultList()) {
                Student student = (Student) obj;
                // for(StudentInterest sti : stInterests){
                // if(sti.getStudentInterestPK().getEmail().contains(student.getEmail())){
                // student.stIntList.add(sti);
                // }
                // } < -- don't actually need this code when using @Join, wrote it before
                // implementing the ManyToOne relationship.This might come in handy when
                // refactoring for ManyToMany so leaving it commented out for now.
                System.out.println(student);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close(); // close the connection
        }
    }
}
