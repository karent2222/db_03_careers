/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams & Karent Correa
 * Description: creates and populates a file-based embedded database
 */

import java.sql.*;

public class CreateDB {

    // TODO: create and populate the database
    private static String columnLabel;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try{
            //Create and connect to careers db
            String connectUrl = "jdbc:hsqldb:file:db/careers"; 
            Connection conn = DriverManager.getConnection(connectUrl);

            //Check to ensure successful connection
            if(conn != null){
                System.out.println("Connection Successful!"); 
            }else{
                System.out.println("try again!");
            }
            Statement stmt = conn.createStatement(); 

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
                            "('web' , 'Web Development')";

            stmt.executeUpdate(interestTbl);
            conn.commit();

            // Verify the data was inserted into the Interests table 
            String interestCount = "SELECT COUNT(*) as n FROM Interests"; 
            ResultSet interestRS = stmt.executeQuery(interestCount);
            while(interestRS.next()){
                System.out.println("Inserted " + interestRS.getInt("n") + " rows to Interests table.");
            }

            
            // DROP Students table if it already exists in careers db 
            String studentsTbl = "DROP TABlE IF EXISTS Students";
            stmt.executeUpdate(studentsTbl);
            
            // TODO: create/populate Students
            studentsTbl = "CREATE TABLE Students(\n" +
                            "email VARCHAR(255) NOT NULL PRIMARY KEY,\n" +
                            "name VARCHAR(255) NOT NULL, \n" + 
                            "major VARCHAR(15) NOT NULL, \n" + 
                            "graduation VARCHAR(255) NOT NULL);"; 

            stmt.executeUpdate(studentsTbl);

             // Insert data into Students
             studentsTbl = "INSERT INTO STUDENTS VALUES\n" +
                            "('eastmanv@msudenver.edu', 'Virginia Eastman', 'cs', 'Spring 2022, cloud db java mysql sql'),\n" +
                            "('gilbertb@msudenver.edu', 'Barbara Gilbert', 'cs', 'Fall 2023, db python sql'), \n" +
                            "('zachariasr@msudenver.edu', 'Robert Zacharias', 'cs', 'Spring 2023, cloud edu web')";
             stmt.executeUpdate(studentsTbl);
             conn.commit();


            // Verify the data was inserted into the Students table
            String studentsCount = "SELECT COUNT (*) as n FROM Students";
            ResultSet studentsRS = stmt.executeQuery(studentsCount);
            while(studentsRS.next()){
                System.out.println("Students" + studentsRS.getInt("n") + "rows to Students table");
            }

            
            // DROP Employers table if it already exists in careers db 
            String employersTbl = "DROP TABlE IF EXISTS Employers";
            stmt.executeUpdate(employersTbl);
            
            // TODO:Create Employers table 
            employersTbl = "CREATE TABLE Employers(\n" +
                            "id VARCHAR(4) NOT NULL PRIMARY KEY,\n " +
                            "name VARCHAR(255) NOT NULL, \n" +
                            "size INT(3) NOT NULL, \n" +
                            "location VARCHAR(255) NOT NULL, \n" +
                            "forprofit VARCHAR(255) NOT NULL, \n" +
                            "govem VARCHAR(255) NOT NULL);";

            stmt.executeUpdate(employersTbl);
           
            //Insert data into Employers table
            employersTbl = "INSERT INTO EMPLOYERS VALUES\n" +
                            "('101', 'Wonka Industries', '350', 'Lakewood, CO', 'true, false','db dba java mysql postgres sql'), \n" +
                            "('202', 'Cheers Agency', '1000', 'Denver, CO', 'true, false', 'aws cloud python sql'), \n" +
                            "('303', 'Dominate the World', '5', 'Golden, CO', 'true, false', 'cloud cyber java web'), \n" +
                            "('404', 'Stingers Org', '212', 'Denver, CO', 'true, false', 'postgres python sql'), \n" +
                            "('505', 'Easy Peasy', '1', 'Littleton, CO', 'true, false', 'java')";
                                                      
            // Verify the data was inserted into the Employers table 
            String employersCount = "SELECT COUNT (*) as n FROM Employers";
            ResultSet employersRS = stmt.executeQuery(employersCount);
            while(employersRS.next()){
                System.out.println("Employers" + employersRS.getInt("n") + "rows to Employers table");
            }
            }

        
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        
        

       

       

    }
}
