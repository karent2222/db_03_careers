/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams & Karent Correa
 * Description: creates and populates a file-based embedded database
 */

import java.sql.*;

public class CreateDB {

   
    public static void main(String[] args) throws SQLException {

        try {
            // Create and connect to careers db
            String connectUrl = "jdbc:hsqldb:file:db/careers";
            Connection conn = DriverManager.getConnection(connectUrl);

            // Check to ensure successful connection
            if (conn != null) {
                System.out.println("\nConnection Successful!\n");
            } else {
                System.out.println("An error has occurred. Please try again!");
            }
            Statement stmt = conn.createStatement();

            // String drop = "DROP SCHEMA PUBLIC CASCADE"; 
            // stmt.executeUpdate(drop);
           
             // DROP EmployerIntersts table if it already exists in the careers db
            String empIntTbl = "DROP TABLE IF EXISTS EmployerInterests;";
            stmt.executeUpdate(empIntTbl);

            // DROP StudentInterests table if it exists in careers db
            String stIntTbl = "DROP TABLE IF EXISTS StudentInterests;";
            stmt.executeUpdate(stIntTbl);

             // DROP Employers table if it already exists in careers db
             String employersTbl = "DROP TABlE IF EXISTS Employers";
             stmt.executeUpdate(employersTbl);

             // DROP Students table if it already exists in careers db
            String studentsTbl = "DROP TABlE IF EXISTS Students";
            stmt.executeUpdate(studentsTbl);

              // DROP Interests table if it already exists in careers db
              String interestTbl = "DROP TABlE IF EXISTS Interests";
              stmt.executeUpdate(interestTbl);

           


            // Create Interests table
            interestTbl = "CREATE TABLE Interests(\n" +
                    "abbrv VARCHAR(15) NOT NULL PRIMARY KEY,\n" +
                    "descr VARCHAR(50) NOT NULL);";

            stmt.executeUpdate(interestTbl);

            // Insert data into Interests
            interestTbl = "INSERT INTO Interests VALUES\n" +
                    "('android' , 'Android Development'),\n" +
                    "('app' , 'Mobile App Development'),\n" +
                    "('aws' , 'Amazon Web Services'),\n" +
                    "('cloud' , 'Cloud Computing'),\n" +
                    "('cyber' ,'Cyber Security'),\n" +
                    "('db' , 'Databases'),\n" +
                    "('dba' , 'Database Administrator'),\n" +
                    "('edu' , 'Education'),\n" +
                    "('java' ,'Java'),\n" +
                    "('kotlin' , 'Kotlin'),\n" +
                    "('mysql' , 'MySQL'),\n" +
                    "('postgres' , 'Postgresql'),\n" +
                    "('python' , 'Python'),\n" +
                    "('software' , 'Software Development'),\n" +
                    "('sql' , 'SQL'),\n" +
                    "('web' , 'Web Development');";

            stmt.executeUpdate(interestTbl);
            conn.commit();

            // Verify the data was inserted into the Interests table
            String interestCount = "SELECT COUNT(*) as n FROM Interests";
            ResultSet interestRS = stmt.executeQuery(interestCount);
            while (interestRS.next()) {
                System.out.println("Inserted " + interestRS.getInt("n") + " rows to Interests table.");
            }

            // Create Students table
            studentsTbl = "CREATE TABLE Students(\n" +
                    "email VARCHAR(255) NOT NULL PRIMARY KEY,\n" +
                    "name VARCHAR(255) NOT NULL, \n" +
                    "major VARCHAR(15) NOT NULL, \n" +
                    "graduation VARCHAR(255) NOT NULL);";

            stmt.executeUpdate(studentsTbl);

            // Insert data into Students
            studentsTbl = "INSERT INTO Students VALUES\n" +
                    "('eastmanv@msudenver.edu', 'Virginia Eastman', 'cs', 'Spring 2022'),\n" +
                    "('gilbertb@msudenver.edu', 'Barbara Gilbert', 'cs', 'Fall 2023'), \n" +
                    "('zachariasr@msudenver.edu', 'Robert Zacharias', 'cs', 'Spring 2023');";
            stmt.executeUpdate(studentsTbl);
            conn.commit();

            // Verify the data was inserted into the Students table
            String studentsCount = "SELECT COUNT (*) as n FROM Students";
            ResultSet studentsRS = stmt.executeQuery(studentsCount);
            while (studentsRS.next()) {
                System.out.println("Inserted " + studentsRS.getInt("n") + " rows to Students table.");
            }

        
            // Create StudentInterests table
            stIntTbl = "CREATE TABLE StudentInterests(\n" +
                    "email VARCHAR(255) NOT NULL,\n" +
                    "abbrv VARCHAR(15) NOT NULL,\n" +
                    "FOREIGN KEY(email) REFERENCES Students(email)," +
                    "FOREIGN KEY(abbrv) REFERENCES Interests(abbrv)," +
                    "PRIMARY KEY(email, abbrv));";

            stmt.executeUpdate(stIntTbl);

            // Insert data into StudentInterests
            stIntTbl = "INSERT INTO StudentInterests VALUES\n" +
                    "('eastmanv@msudenver.edu', 'cloud'),\n" +
                    "('eastmanv@msudenver.edu', 'db'),\n" +
                    "('eastmanv@msudenver.edu', 'java'),\n" +
                    "('eastmanv@msudenver.edu', 'mysql'),\n" +
                    "('eastmanv@msudenver.edu', 'sql'),\n" +
                    "('gilbertb@msudenver.edu', 'db'),\n" +
                    "('gilbertb@msudenver.edu', 'python'),\n" +
                    "('gilbertb@msudenver.edu', 'sql'),\n" +
                    "('zachariasr@msudenver.edu', 'cloud'),\n" +
                    "('zachariasr@msudenver.edu', 'edu'),\n" +
                    "('zachariasr@msudenver.edu', 'web');";
            
            stmt.executeUpdate(stIntTbl);
            conn.commit();

            // Verify the data was inserted into the StudentInterests table
            String stIntCount = "SELECT COUNT (*) as n FROM StudentInterests";
            ResultSet stIntRS = stmt.executeQuery(stIntCount);
            while (stIntRS.next()) {
                System.out.println("Inserted " + stIntRS.getInt("n") + " rows to StudentInterests table.");
            }

            // Create Employers table
            employersTbl = "CREATE TABLE Employers(\n" +
                    "id INT NOT NULL PRIMARY KEY,\n " +
                    "name VARCHAR(255) NOT NULL, \n" +
                    "size INT NOT NULL, \n" +
                    "location VARCHAR(255) NOT NULL, \n" +
                    "forprofit VARCHAR(5) NOT NULL, \n" + // boolean?
                    "govern VARCHAR(5) NOT NULL);"; // boolean?

            stmt.executeUpdate(employersTbl);

            // Insert data into Employers table
            employersTbl = "INSERT INTO Employers VALUES\n" +
                    "('101', 'Wonka Industries', 350, 'Lakewood, CO', 'true', 'false'),\n" +
                    "('202', 'Cheers Agency', 1000, 'Denver, CO', 'true', 'false'),\n" +
                    "('303', 'Dominate the World', 5, 'Golden, CO', 'true', 'false'),\n" +
                    "('404', 'Stingers Org', 212, 'Denver, CO', 'true', 'false'),\n" +
                    "('505', 'Easy Peasy', 1, 'Littleton, CO', 'true', 'false');";

            stmt.executeUpdate(employersTbl);
            conn.commit();

            // Verify the data was inserted into the Employers table
            String employersCount = "SELECT COUNT (*) as n FROM Employers";
            ResultSet employersRS = stmt.executeQuery(employersCount);
            while (employersRS.next()) {
                System.out.println("Inserted " + employersRS.getInt("n") + " rows to Employers table.");
            }


            // Create EmployerInterests table
            empIntTbl = "CREATE TABLE EmployerInterests(\n" +
                    "id INT NOT NULL,\n" +
                    "abbrv VARCHAR(15) NOT NULL,\n" +
                    "FOREIGN KEY(id) REFERENCES Employers(id),\n" +
                    "FOREIGN KEY(abbrv) REFERENCES Interests(abbrv),\n" +
                    "PRIMARY KEY(id, intAbbrv));";

            stmt.executeUpdate(empIntTbl);

            // Instert data into EmployerInterests table
            empIntTbl = "INSERT INTO EmployerInterests VALUES\n" +
                    "(101, 'db'),\n" +
                    "(101, 'dba'),\n" +
                    "(101, 'java'),\n" +
                    "(101, 'mysql'),\n" +
                    "(101, 'postgres'),\n" +
                    "(101, 'sql'),\n" +
                    "(202, 'aws'),\n" +
                    "(202, 'cloud'),\n" +
                    "(202, 'python'),\n" +
                    "(202, 'sql'),\n" +
                    "(303, 'cloud'),\n" +
                    "(303, 'cyber'),\n" +
                    "(303, 'java'),\n" +
                    "(303, 'web'),\n" +
                    "(404, 'postgres'),\n" +
                    "(404, 'python'),\n" +
                    "(404, 'sql'),\n" +
                    "(505, 'java');";

            stmt.executeUpdate(empIntTbl);
            conn.commit();

            // Verify the data was inserted into the EmployerInterests table
            String empIntCount = "SELECT COUNT (*) as n FROM EmployerInterests";
            ResultSet empIntRS = stmt.executeQuery(empIntCount);
            while (empIntRS.next()) {
                System.out.println("Inserted " + empIntRS.getInt("n") + " rows to EmployerInterests table.");
            }
            
            System.out.println("\nDatabase populated! have a nice day :)\n");
            conn.close(); 

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
