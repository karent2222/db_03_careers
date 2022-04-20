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
public class Interest {
    private String intAbbrv, empId;

    public String getsIntAbbrv(){
        return intAbbrv;
    }

    public void setsIntAbbrv(String intAbbrv){
        this.intAbbrv = intAbbrv; 
    }
    public String getEmpId(){
        return empId;
    }
    public void setEmpId(String empId){
        this.empId = empId;
    }
    @Override
    public String toString(){
        return "InterestEmployers{intAbbrv =" + intAbbrv + ", empId =" + empId + "}";
    }
   
   // TODO: finish the object-relational mapping
   @Entity
   @Table(name = "InterestEmployers")
   class InterstEmployer implements Serializable{
       @EmbeddedId
       private InterestEmployerPK intEpPK;

       public InterestEmployerPK getInterestEmployerPK(){
           return intEpPK;
       }
       public void setInterestsEmployerPK(InterestEmployerPK intEpPK){
           this.intEpPK = intEpPK;
       }
       @Override
       public String toString(){
           return "InterestEmployer{ InterestEmployerPK = " + intEpPK + " }";

       }
       
   }
   
    // TODO: finish the object-relational mapping
    @Entity
    @Table(name = "Interests")
    class Interests {
        @Id
        private String abbrv;
        
        private String descr;
        
        public String getAbbrv(){
            return abbrv;
        }
        public void setAbbrv(String abbrv){
            this.abbrv = abbrv;
        }
        
        public String getDescr(){
            return descr;
        }
        public void setDescr(String description){
            this.descr = description;
        }
    @Override
    public String toString(){
        return "Interests{ abbrv = " + abbrv + ", descr =" + descr + "}";  
    }
    
    }



    public static void main(String[] args) {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();

        // TODO: list all interests
        

    
        em.close();
    }
}
