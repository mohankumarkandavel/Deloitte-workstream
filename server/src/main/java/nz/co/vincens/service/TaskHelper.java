package nz.co.vincens.service;

import nz.co.vincens.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskHelper {
    /**
     * @param task
     * @param ownId
     */
    public static void addTask(Task task, int ownId) {
        String name = task.getName();
        String description = task.getDescription();
        String deadline = String.valueOf(task.getDeadline());
        String group = String.valueOf(task.getGroup());
        String resource = String.valueOf(task.getResources());
        String availability = String.valueOf(task.getAvailability());
        String numAssigneesRequired = String.valueOf(task.getNumAssigneesRequired());
        String status = String.valueOf(task.getStatus());
        String sql = "CALL Task_addTask(" + "'" + name + "'" + "," + "'" + description + "'" + "," + availability + "," + resource + "," + "'" + deadline + "'" + "," + "'" + group + "'" + "," + "'" + status + "'" + "," + "'" + numAssigneesRequired + "'" + "," + ownId + ")";
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

    /**
     * Get the last list id of assignee details list
     * Call before creating new list record
     *
     * @return List id
     */
    public static int getLastAssigneesListId() {
        int listId = 0;
        // Generate database query sentence
        String sql = "CALL Task_getLastAssigneesListId()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                listId = rs.getInt("MAX(listId)");
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
        } finally {
            try {
                // Clean-up environment
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listId;
    }

    /**
     * @param listId
     * @param teamMemberId
     */
    public static void createNewAssigneesList(int listId, int teamMemberId) {
        // Generate database query sentence
        String sql = "CALL Task_createNewAssigneesList(" + (listId + 1) + "," + teamMemberId + ")";
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

    /**
     * @param assigneesListId
     * @param status
     * @param taskId
     */
    public static void updateToPending(int assigneesListId, String status, int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToPending(" + "'" + assigneesListId + "'" + "," + "'" + status + "'" + "," + "'" + taskId + "')";
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

    /**
     * After team member accepting a task, change the status to ASSIGNED
     *
     * @param taskId The id of the selected task
     */
    public static void updateToAssigned(int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToAssigned(" + taskId + ")";
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
