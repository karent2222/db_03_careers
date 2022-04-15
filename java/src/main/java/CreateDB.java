/*
 * CS3810 - Principles of Database Systems - Spring 2022
 * Instructor: Thyago Mota
 * Student Names: Alissa Williams & Karent Correa
 * Description: creates and populates a file-based embedded database
 */

import java.sql.*;
import javax.persistence.*;

public class CreateDB {

    // TODO: create and populate the database
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection c = DriverManager.getConnection(
         "jdbc:hsqldb:file:/opt/db/testdb;ifexists=true", "SA", "");
        
 
        if(c != null){
        
        System.out.println("Connection to db " + c + " was successful!");
        
        }
        
        else{
        
        System.out.println("Connection to db " + c + " was not successful!");
        
        }

        // Create careers db

        // Use careers db

        // Create Interests table
        // Create Students table
        // Create Employers table

    }
}
