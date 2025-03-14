import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class connect {
    static String URL = "jdbc:mysql://localhost:3306/phonebook";
    static String USER = "root"; // MySQL username
    static String PASSWORD = "0000"; // MySQL password

    public static ArrayList<String[]> executeQuery(String query) {
        ArrayList<String[]> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // ResultSetMetaData object provides detailed information about the columns in the result set.
            // This includes column names, types, and other attributes like whether a column is nullable, its size, etc.
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // to add colums automatically
            for (int i = 1; i <= columnCount; i++) {
                phonebook.model.addColumn(metaData.getColumnName(i)); // model from form1.java
            }

            // to add rows.
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString(i + 1);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return results;
    }
    public static void addContact(String phone, String firstName, String lastName, InputStream img) {
        // SQL query for inserting actor data into the database
        String query = "INSERT INTO phonebook.phones (phone, first_name, last_name, img) VALUES (?, ?, ?, ?)";

// Declare variables for database connection and prepared statement
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Disable auto-commit to manually handle transactions
            connection.setAutoCommit(false);

            // Prepare the SQL query
            pstmt = connection.prepareStatement(query);

            // Set the values for the query placeholders using the input data
            pstmt.setString(1, phone);  // Set phone
            pstmt.setString(2, firstName);   // Set first name
            pstmt.setString(3, lastName);  // Set last name
            pstmt.setBinaryStream(4, img);  // Set the image as a binary stream

            // Execute the query and get the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            // If the insertion is successful, commit the transaction
            if (rowsAffected > 0) {
                connection.commit();
                System.out.println("Insertion committed successfully for contact with phone: " + phone);
            } else {
                // If insertion fails, roll back the transaction
                connection.rollback();
                System.out.println("Insertion failed. Transaction rolled back.");
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            try {
                if (connection != null) {
                    // If an error occurs, roll back the transaction
                    connection.rollback();
                }
                System.out.println("SQL Error: " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                // Print stack trace if there is an error during rollback
                rollbackEx.printStackTrace();
            }

        }
    }
    public static void updateContact(String phone, String firstName, String lastName){
        // SQL query for inserting actor data into the database
        String query = "UPDATE phonebook.phones SET first_name = ?, last_name = ? WHERE phone = ?";

// Declare variables for database connection and prepared statement
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Disable auto-commit to manually handle transactions
            connection.setAutoCommit(false);

            // Prepare the SQL query
            pstmt = connection.prepareStatement(query);

            // Set the values for the query placeholders using the input data
            pstmt.setString(1, firstName);  // first name
            pstmt.setString(2, lastName);   // last name
            pstmt.setString(3, phone);  // phone

            // Execute the query and get the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            // If the insertion is successful, commit the transaction
            if (rowsAffected > 0) {
                connection.commit();
                System.out.println("Update committed successfully for contact with phone: " + phone);
            } else {
                // If insertion fails, roll back the transaction
                connection.rollback();
                System.out.println("Update failed. Transaction rolled back.");
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            try {
                if (connection != null) {
                    // If an error occurs, roll back the transaction
                    connection.rollback();
                }
                System.out.println("SQL Error: " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                // Print stack trace if there is an error during rollback
                rollbackEx.printStackTrace();
            }

        }
    }
    public static void deleteContact(String id){
        String query = "DELETE FROM phonebook.phones WHERE phone LIKE " + id;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Execute the update query
            pstmt.executeUpdate();

            // Debugging: Print a success message
            System.out.println("Successfully deleted record with id: " + id);
        } catch (SQLException ex) {
            // If there's an exception (e.g., a syntax error or connection issue), print the error message.
            System.out.println("SQL Error: " + ex.getMessage());
        }
    }
}
