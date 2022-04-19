/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: creates a Student entity with a 1-many mapping to StudentInterest and allows listing of all students
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// TODO: finish the object-relational mapping

class StudentInterestPK implements Serializable {
    private String stEmail, intAbbrv; 

    public String getStEmail(){
        return stEmail;
    }

    public void setStEmail(String stEmail){
        this.stEmail = stEmail; 
    }

    public String getIntAbbrv(){
        return intAbbrv;
    }

    public void setIntAbbrv(String intAbbrv){
        this.intAbbrv = intAbbrv;
    }

    @Override
    public String toString(){
        return "StudentInterestPK{stEmail = " + stEmail + ", intAbbrv = " + intAbbrv + "}";
    }
}

// TODO: finish the object-relational mapping
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
    public String toString(){
        return "StudentInterests{ StudentInterestPK = " + stIntPK +" }";
    }
}

// TODO: finish the object-relational mapping
@Entity 
@Table(name = "Students")
class Student {
    @Id
    private String email; 
    
    private String name, major, graduation; 

    public String getEmail(){
        return email; 
    }
    public void setEmail(String email){
        this.email = email; 
    }

    public String getName(){
        return name; 
    }

    public void setName(String name){
        this.name = name; 
    }

    public String getMajor(){
        return major; 
    }

    public void setMajor(String major){
        this.major = major; 
    }

    public String getGraduation(){
        return graduation; 
    }

     public void setGraduation(String graduation){
        this.graduation = graduation; 
    }

   @Override
   public String toString(){
       return "Students{ email = " + email + ", name = " + name + ", major = " + major + ", graduation = " + graduation + " }";
   }

    public static void main(String[] args) {
        
        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();

        // TODO: list all students

        // Create query string that includes a join for the Students and StudentInterests table to display students with each interest
        Query query = em.createQuery("SELECT a FROM Student a");
        //List<String> students = new List<String> ();
        for(Object obj:  query.getResultList()){
            Student student = (Student) obj; 
            System.out.println(student);
        }
        
        em.close();
    }
}
