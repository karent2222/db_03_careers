/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: creates a Student entity with a 1-many mapping to StudentInterest and allows listing of all students
 */

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: finish the object-relational mapping

class StudentInterestPK implements Serializable {
    private String email, abbrv; 

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }

    public String getAbbrv(){
        return abbrv;
    }

    public void setIntAbbrv(String abbrv){
        this.abbrv = abbrv;
    }

    @Override
    public String toString(){
        return "email = " + email + "\t\t\t abbrv = " + abbrv;
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
        return "\n" + stIntPK ;
    }
}

// TODO: finish the object-relational mapping
@Entity 
@Table(name = "Students")
class Student {
    @Id
    private String email; 
    
    private String name, major, graduation; 

    @Transient
    private List<StudentInterest> stIntList; 

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

    public List<StudentInterest> getStudentInterests(){
        return stIntList; 
    }

    public void setStudentInterest(List<StudentInterest> stIntList){
        this.stIntList = stIntList; 
    }

   @Override
   public String toString(){
       String interests = ""; 
       for(StudentInterest stint : this.stIntList){
           interests = interests + stint.getStudentInterestPK().getAbbrv() + " "; 
       }
       return "Student: \nemail = " + email + "\nname = " + name + "\nmajor = " + major + "\ngraduation = " + graduation + "\nStudent Interests = [" + interests + "]\n";
   }

   Student () {
        stIntList = new ArrayList<StudentInterest>();
   }
    public static void main(String[] args) throws SQLException{

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();
        try {
        // TODO: list all students

        // Create query string that includes a join for the Students and StudentInterests table to display students with each interest
        
        Query intQuery = em.createQuery("SELECT i from StudentInterest i"); 
        List<StudentInterest> stInterests = new ArrayList<StudentInterest>();
        StudentInterest stInt; 
        for(Object obj: intQuery.getResultList()){
           stInt = (StudentInterest) obj; 
            stInterests.add(stInt);
        }

        Query query = em.createQuery("SELECT a FROM Student a");
        List<Student> students = new ArrayList<Student> ();
        for(Object obj:  query.getResultList()){
            Student student = (Student) obj; 
            for(StudentInterest sti : stInterests){
                if(sti.getStudentInterestPK().getEmail().contains(student.getEmail())){
                    student.stIntList.add(sti);
                }
            }
            students.add(student);   
        }
        System.out.println(students); 
    } 
    catch (Exception e){
        System.out.println(e);
    }
     finally{   
        em.close();
    }
    }
}
