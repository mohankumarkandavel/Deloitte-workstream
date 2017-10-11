package nz.co.vincens.service;

import nz.co.vincens.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database functions for tasks
 * This class is to implement the essential function for database query of managing tasks.
 * The function includes flowing:
 * 1. The manager create a new task
 * 2. The manager assigns a task to some team members and update the status into pending
 * 3. The team member accept a task and update the status into assigned.
 * 4. The team member decline a task and update the status back into draft.
 * 5. The team member mark a task as done and update the status into completed.
 */
public class TaskHelper {

    /**
     * Insert a new task into the database
     *
     * @param task  The whole object of one task
     */
    public static void addTask(Task task) {
        String name = task.getName();
        String description = task.getDescription();
        String deadline = String.valueOf(task.getDeadline());
        String group = String.valueOf(task.getGroup());
        String resource = String.valueOf(task.getResources());
        String availability = String.valueOf(task.getAvailability());
        String numAssigneesRequired = String.valueOf(task.getNumAssigneesRequired());
        String status = String.valueOf(task.getStatus());
        String sql = "CALL Task_addTask(" + "'" + name + "'" + "," + "'" + description + "'" + "," + availability +
				"," + resource + "," + "'" + deadline + "'" + "," + "'" + group + "'" + "," + "'" + status + "'" + "," +
				"" + "'" + numAssigneesRequired + "'" + "," + task.getOwner().getId() + ")";
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
     * @return list id
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
     * Create a new assignees' list to store the assign details of a task
     *
     * @param listId       The id of the new list
     * @param teamMemberId Ids of selected team members
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
	 * Update the task status to pending
     * @param assigneesListId The list of requested assignees
     * @param status The new status of the task
     * @param taskId The id of the task to update
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
     * Update in the database
     * @param taskId The id of the selected task
     * @return The new status of this task for status checking
     */
    public static String updateToAssigned(int taskId) {
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
        return checkStatus(taskId);
    }

    /**
     * Update the status into completed after the team member mart the task as done
     * Update in the database
     *
     * @param taskId The id of the selected task
     * @return The new status of this task for status checking
     */
    public static String updateToCompleted(int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToCompleted(" + taskId + ")";
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
        return checkStatus(taskId);
    }

    /**
     * @param taskId The id of the selected task
     * @return The new status of this task for status checking
     */
    public static String updateToDeclined(int taskId) {
        // Generate database query sentence
        String sql = "CALL Task_updateToDeclined(" + taskId + ")";
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
        return checkStatus(taskId);
    }

    /**
     * To check the newest status of one specific task in database
     *
     * @param taskId The id of the selected task
     * @return The status of the task
     */
    public static String checkStatus(int taskId) {
        // Generate database query sentence
        String status = null;
        // Call the function of database query operation
        String sql = "Call Task_checkStatus(" + taskId + ")";
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            while (rs.next()) {
                status = rs.getString("status");
            }
        } catch (SQLException e) {
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
        return status;
    }

}
