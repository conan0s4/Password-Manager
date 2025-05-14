package password.manager.main;

import java.sql.Connection;         // Used to open a connection to the SQLite database
import java.sql.DriverManager;      // Manages database drivers and helps establish a connection
import java.sql.SQLException;       // Handles SQL errors and exceptions
import java.sql.Statement;          // Used to execute static SQL statements like CREATE, INSERT, DELETE
import java.sql.ResultSet;          // Holds the result of a SELECT query (used to read data from the DB)
import java.sql.PreparedStatement;  // Used to safely execute parameterized SQL queries (prevents SQL injection)


//main function: handles all database function:
// connecting to db | tables | loginui/pswmngui functions
//add sqlite jdbc to project (libraries)

//https://sqlite.org/docs.html
//https://www.tutorialspoint.com/sqlite/index.htm
/*______________________________________________________________________________

 -->>   FOR CONNNECTING TO DATABASE  FUNCTIONS
______________________________________________________________________________*/



public class SQLITEDataBaseHandler {
    private static final String DB_URL = "jdbc:sqlite:password_manager.db";//<<----- (DB_URL)holds the path to sqlite db
//if file dont exist it then it will be created
//reason for the table being in the handler: for creating the db     
    public SQLITEDataBaseHandler() {  // instatiation of two classes
        createMasterKeyTable(); //----> creating the tables
        createPasswordManagerTable();
    }

    // Connect to the SQLite database
    private Connection connect() throws SQLException { //-----> error handler if connection was not successful
        return DriverManager.getConnection(DB_URL); //using driver manager it opens a connection to the db
    }
/*______________________________________________________________________________

 -->>   FOR tables of the DATABASE  FUNCTIONS
______________________________________________________________________________*/

    // Create table to store the master key  --> use when creating a new db
    private void createMasterKeyTable() {  //sql command (CREATE TABLE identifier (specify))
        String sql = "CREATE TABLE IF NOT EXISTS master_key ("+
                     "password TEXT NOT NULL" + //store master key here (must not be empty)
                     ");";  // master key password only here 
//reason: (system designed for single user only)
        Connection conn = null; //conn  && stmt  use for managing connections and sql statement execution
        Statement stmt = null;

        try { 
            conn = connect(); // Open DB connection
            stmt = conn.createStatement(); // Create a SQL statement object
            stmt.execute(sql); // Run the SQL command    ------> creates the table
        } catch (SQLException e) { //error handler
            System.out.println("Error creating master key table.");  //if failed 
        } finally { //use for closing the connection either successful or not after use   [#](same logic goes to other method which is applied)
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
            }
        }
    }
//##############################################################################
    // Create table to store passwords
    private void createPasswordManagerTable() { //explaination above
        String sql = "CREATE TABLE IF NOT EXISTS passwords (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," + //why?(avoid conflict) so we could avoid duplicate ID  so we can easily delete a set of it by only specifyig its id
                     "user TEXT NOT NULL," +
                     "password TEXT NOT NULL," +
                     "platform TEXT NOT NULL" +
                     ");";

        Connection conn = null;   //explaination above 
        Statement stmt = null;    // for sql db connection and queries

        try {  //try this first then 
            conn = connect();   
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {  //error handler (if any errors may occur catch)
            System.out.println("Error creating password manager table.");
            e.printStackTrace(); //prints out the specific error it had
        } finally { //finally means constant (cannot be change)    -----> close connection
            try {
                if (stmt != null) stmt.close();  //stmt / conn closed after used within this method
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
/*______________________________________________________________________________

 -->>   FOR LOGIN UI FUNCTIONS
______________________________________________________________________________*/

    // Check if master key already exists (verification)
    public boolean isMasterKeySet() {
        String sql = "SELECT COUNT(*) FROM master_key;"; // this is the query
        
/*
checks if the master key is already set by counting how many rows are present in the master_key table. 
If the count is greater than 0, the key is set.        
        
        
*/        
        
        
        Connection conn = null;
        Statement stmt = null; // Used to execute the query.
        ResultSet rs = null; //Holds the result of the query (the result set).

        
   /*
rs.getInt(1) > 0: If  count of rows is greater than 0, 
meaning the master key is set, and if it returns true. if not it returns false.   
 
   */     
        //rs result statement (the execute query gets the result and stores it in the rs (look below) )
        try {
            conn = connect(); ///connecting to db
            stmt = conn.createStatement(); //using the connection  to create the statement object (allowsto send sql queries to the db)
            rs = stmt.executeQuery(sql); //sends sql command to db and then gets the result --> execute the query (select count) 
            return rs.getInt(1) > 0; //<<-----
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {   //closing the  connection / result statement / statement after use
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
//##############################################################################
    // Save a new master key (only if not set)
    public boolean setMasterKey(String password) {  // boolean returns either (true / false)   // password = takes the psw that was made stores it here
        if (isMasterKeySet()) return false; //checks if master  key was already made ---> method above
//if returns true then it already exist (if false it's not yet made) ---> proceed
        String sql = "INSERT INTO master_key(password) VALUES(?);"; // SQL query used to insert a value into the master_key table
        Connection conn = null;                    //values(?) ? - placeholder 
        PreparedStatement pstmt = null;
// to prevent sql injection we will be using prepared statement here
        try {
            conn = connect();
            pstmt = conn.prepareStatement(sql); //similar to statement we use the connect to be able to use query in the db 
            pstmt.setString(1, password);  // <-- replaces the ? with the actual password  //set string >> insert  the  value
            pstmt.executeUpdate(); //execute update the table in the db
            return true;  // returns true (new masster key psw craeted successfully )---stores it in the master key table 
        } catch (SQLException e) { //error handler
            e.printStackTrace();
            return false;
        } finally {  ///close prepared statement / connection
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) { //error handler
                ex.printStackTrace();
            }
        }
    }
//##############################################################################
    // Check if the entered password matches the stored master key
    public boolean validateMasterKey(String inputPassword) { //returns either true if psw (correct) false if (invalid)
        String sql = "SELECT password FROM master_key LIMIT 1;"; //query
        /*
        input password --> user typed in the txt field
        limit 1 because system is for single user only
        select the password from master key table 
        
        
        */
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null; // explaination already stated above

        try {
            conn = connect();
            stmt = conn.createStatement();  // creates a SQL statement
            rs = stmt.executeQuery(sql); //then executes the query

            if (rs.next()) { //if db has data it returns true ---> handled in other class
                String storedPassword = rs.getString("password"); //exctracts the actual password of the db
                return storedPassword.equals(inputPassword); // this will return true/false
                //is the stored password is equals to what the user has inputed
            }
        } catch (SQLException e) { //error handler
            e.printStackTrace(); //prints out the actual error 
        } finally { //closing 
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) { //error handler
                ex.printStackTrace();
            }
        }
        return false; // If we never returned true (makes sure it would return false to avoid crashing the system)
        //database not existing / the table not existing /  or the input password not matching / corrupted
    }
/*______________________________________________________________________________

 -->>   FOR PASSWORD MANAGER UI FUNCTIONS
______________________________________________________________________________*/
    // Save a new password entry                                                     // if (error/failure) occured (false)
    public boolean savePasswordEntry(String user, String password, String platform) { // returns either true if saved successfully || false if not
        String sql = "INSERT INTO passwords(user, password, platform) VALUES (?, ?, ?);"; // sql query (insert) insert value to psw manager table
                                                                           // (?) placeholder-- will be replaced right after access our db and update the table
        // insert -- add new row in the table --> isnert to passwords table --> this (  , , ,)
        
        Connection conn = null; 
        PreparedStatement pstmt = null; 
//placeholders will be safely filled with actual values using a PreparedStatement.
        try {//using preparedstatement to set the values in numerical order one at a time 
            conn = connect(); //open connection to db
            pstmt = conn.prepareStatement(sql); //for querying --> to use in the db
            pstmt.setString(1, user); //set 1st placeholder (?) to user(userinputted) from the psw managerui class 
            pstmt.setString(2, password); //2nd ''
            pstmt.setString(3, platform);//3rd ''
            pstmt.executeUpdate();//execute update the table in the db (the actual insertion happens)
            return true;
        } catch (SQLException e) { //error handler
            e.printStackTrace();
            return false;
        } finally { //close connection db and prepared statement
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) { //error handler
                ex.printStackTrace();
            }
        }
    }
//##############################################################################
    // Delete an entry by ID        //method takes one input (id)  
    public boolean deletePasswordEntry(int id) { //returns true if deletion was successfull || if fail false 
        String sql = "DELETE FROM passwords WHERE id = ?;"; //delete query --> (delete from passwordstable where? ID row specifically = (?)ID )
        Connection conn = null; //connect to db ---> both an object to be used
        PreparedStatement pstmt = null;

        try {
            conn = connect();
            pstmt = conn.prepareStatement(sql); //for query in  db
            pstmt.setInt(1, id); //no we replace the ? placeholder the actual value
            return pstmt.executeUpdate() > 0; // then execute deletion --> 
            /*
If executeUpdate() returns 1 or more --> a row was deleted --> return true.
If it returns 0 --> no row matched the ID, so nothing was deleted --> return false.
           */
        } catch (SQLException e) { //error handler
            e.printStackTrace();
            return false;
        } finally { //closing connection | prepared statement after use
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) { //error handler
                ex.printStackTrace();
            }
        }
    }
//##############################################################################
    // Return all saved entries  --> get all data from the psw table db
    public ResultSet viewPasswordEntries() {    //result set or result table --> returns the table
        String sql = "SELECT * FROM passwords;"; //select all (id,user,psw,platform) everything from passwords table 
        Connection conn = null;
        PreparedStatement pstmt = null;

        try { //try block --> if it throws  sqlexception
            conn = connect();
            pstmt = conn.prepareStatement(sql);//for querying use
            return pstmt.executeQuery(); // executes the query then returns the result set
        } catch (SQLException e) { //error handler
            e.printStackTrace();
            return null;
        }
    }
}
