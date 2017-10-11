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
     * @param task The whole object of one task
     * @return If execute succeed, return 1
     */
    public static int addTask(Task task) {
        String name = task.getName();
        String description = task.getDescription();
        String deadline = String.valueOf(task.getDeadline());
        String group = String.valueOf(task.getGroup());
        String resource = String.valueOf(task.getResources());
        String availability = String.valueOf(task.getAvailability());
        String numAssigneesRequired = String.valueOf(task.getNumAssigneesRequired());
        String status = String.valueOf(task.getStatus());
        String sql = "CALL Task_addTask(" + "'" + name + "'" + "," + "'" + description + "'" + "," + availability + "," + resource + "," + "'" + deadline + "'" + "," + "'" + group + "'" + "," + "'" + status + "'" + "," + "'" + numAssigneesRequired + "'" + "," + task.getOwner().getId() + ")";
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
        return 1;
    }

    /**
     * Get the last list id of assignee details list
     * Call before creating new list record
     *
     * @return list id
     */
    public static int getLastAssigneesListId() {
        int listId = 0;
        // Generate database query command
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
     * @return If execute succeed, return 1
     */
    public static int createNewAssigneesList(int listId, int teamMemberId) {
        // Generate database query command
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
        return 1;
    }

    /**
     * Update the task status to pending
     *
     * @param taskId The id of the task to update
     * @return If execute succeed, return 1
     */
    public static int updateToPending(int taskId) {
        // Generate database query command
        String sql = "CALL Task_updateToPending(" + taskId + ")";
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
        return 1;
    }

    /**
     * After team member accepting a task, change the status to ASSIGNED
     * Update in the database
     *
     * @param taskId The id of the selected task
     * @return The new status of this task for status checking
     */
    public static String updateToAssigned(int taskId) {
        // Generate database query command
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
        // Generate database query command
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
     * @param reason The decline reason from the team member's respond
     * @return The new status of this task for status checking
     */
    public static String updateToDeclined(int taskId, String reason) {
        // Generate database query command
        String sql = "CALL Task_updateToDeclined(" + taskId + "," + "'" + reason + "'" + ")";
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
        // Generate database query command
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

    /**
     * Link assigned list id to the task table
     *
     * @param taskId task id
     * @param listId assigneeslist id
     * @return If execute succeed, return 1
     */
    public static int updateAssignedDetails(int taskId, int listId) {
        // Generate database query command
        String sql = "CALL Task_updateRequestDetails(" + taskId + "," + listId + ")";
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
        return 1;
    }


    /**
     * Get the id of the recode after insert a new task
     *
     * @return The id of the new task
     */
    public static int getIdAfterInsert() {
        int id = 0;
        // Generate database query command
        String sql = "CALL Task_getIdAfterInsert()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            while (rs.next()) {
                id = rs.getInt("MAX(id)");
            }
            // Clean-up environment
            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
