package nz.co.vincens.service;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskTest {

    public static void main(String[] args) {
        // updateToPending(1, 1);
        updateToAssigned(1);
    }

    public static void updateToPendingOrAssigned(int assigneesListId, String status, int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToPendingOrAssigned(" + "'" + assigneesListId + "'" + "," + "'" + status + "'" + "," + "'" + taskId + "')";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Clean-up environment
            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateToAssigned(int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToAssigned(" + "'" + taskId + "')";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Clean-up environment
            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
