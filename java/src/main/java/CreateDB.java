/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alyssa Williams & Karent Correa
 * Description: creates and populates a file-based embedded database
 */

import java.sql.*;

public class CreateDB {

    // TODO: create and populate the database
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

            // TODO: create/populate Students  
            // DROP Students table if it already exists in careers db 
            // Create Students table
            // Insert data into Students 
            // Verify the data was inserted into the Students table

            // TODO: create/populate Employers  
            // DROP Employers table if it already exists in careers db 
            // Create Employers table
            // Insert data into Employers 
            // Verify the data was inserted into the Employers table 
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        
        

       

       

    }
}
