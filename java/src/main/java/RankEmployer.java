/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: show recommendation scores for student-employer matching based on common interests
 */

import javax.persistence.*;
import java.util.Scanner;

public class RankEmployer {

    // compute the jaccard similarity between given student and employer
    static double jaccard(final Student student, final Employer employer) {
        int common = 0;
        for (StudentInterest studentInterest: student.getStudentInterests()) {
            boolean found = false;
            for (EmployerInterest employerInterest : employer.getEmpIntList())
                if (studentInterest.getStudentInterestPK().getAbbrv().equals(employerInterest.getEmployerInterestPK().getAbbrv())) {
                    found = true;
                    break;
                }
            if (found)
                common++;
        }
        int all = student.getStudentInterests().size() + employer.getEmpIntList().size() - common;
        return (double) common / all;
    }

    public static void main(String[] args) {

        // Entity Manager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        EntityManager em = emf.createEntityManager();

        // TODO: retrieve a student by email
        Scanner sc = new Scanner(System.in);
        System.out.print("email: ");
        String email = sc.nextLine();
        System.out.println("You said: " + email);
        email = email.trim(); // trim whitespace from user input to ensure a successful query on Students table. 

        // Query that selects all from Students table where email is equal to email entered by user, utilize trim() method to 

        // TODO: matching of the student to each employer
        
        // Query that selects all employers from Employer table.

        // For each result set, push the employer result into an employers list 

        // create a RankEmployer object to be able to call the jaccard() method 

        // For each employer compare the student using the jaccard() method. 

        // sort the list by ranking 

        // print the student details 

        // print the sorted ranking of each eamployer for the specified student. 
        

        // close the scanner 
        sc.close(); 

        // close the Entity Manager connection
        em.close();
    }
}
