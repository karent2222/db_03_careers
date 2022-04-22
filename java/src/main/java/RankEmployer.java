/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams and Karent Correa 
 * Description: show recommendation scores for student-employer matching based on common interests
 */

import javax.persistence.*;

import java.util.*;

public class RankEmployer {

    // compute the jaccard similarity between given student and employer
    static double jaccard(final Student student, final Employer employer) {
        int common = 0;
        for (StudentInterest studentInterest : student.getStudentInterests()) {
            boolean found = false;
            for (EmployerInterest employerInterest : employer.getEmpIntList())
                if (studentInterest.getStudentInterestPK().getAbbrv()
                        .equals(employerInterest.getEmployerInterestPK().getAbbrv())) {
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
        email = email.trim(); // trim whitespace from user input to ensure a successful query on Students
                              // table.

        // Query that selects all from Students table where email is equal to email
        // entered by user
        Student student; // init Student object to use outside of result set scope
        Query stQuery = em.createQuery("SELECT s from Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email);
        for (Object sObj : stQuery.getResultList()) {
            student = (Student) sObj;
            System.out.println("\nStudent-Employer Interest Ranking\n---------------------------------\n" + student);

            // TODO: matching of the student to each employer

            // Query that selects all employers from Employer table.
            Employer employer; // init Employer object to use outside of result set scope
            Query query = em.createQuery("SELECT a FROM Employer a");
            for (Object eObj : query.getResultList()) {
                employer = (Employer) eObj;
                // Print each employer 
                System.out.println(employer);
                // Print a ranking with the employer formatted to 2 decimal places 
                System.out.printf("Ranking: %.2f", jaccard(student, employer)); 
                System.out.print("\n"); // formatting the result set to include a carriage return between each employer
                                          
            }
        }

        // close the scanner
        sc.close();

        // close the Entity Manager connection
        em.close();
    }
}
