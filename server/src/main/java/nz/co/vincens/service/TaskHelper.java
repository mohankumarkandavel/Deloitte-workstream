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
    public static void addTask(Task task) {
        String name = task.getName();
        String description = task.getDescription();
        String deadline = String.valueOf(task.getDeadline());
        String group = String.valueOf(task.getGroup());
        String resource = String.valueOf(task.getResources());
        String availability = String.valueOf(task.getAvailability());
        String numAssigneesRequired = String.valueOf(task.getNumAssigneesRequired());
        String status = "Draft";
        String sql = "CALL User_getAddTask(" + "'" + name + "'" + "'" + description + "'" + "'" + deadline + "'" + "'" + group + "'" + "'" + resource + "'" + "'" + availability + "'" + "'" + numAssigneesRequired + "'" + "'" + status + "'" + ")";
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

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "CALL Task_getTasks()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date deadline = format1.parse(rs.getString("deadline"));
                String group = rs.getString("group");
                int experience = Integer.parseInt(rs.getString("experience"));
                int interest = Integer.parseInt(rs.getString("interest"));
                int availability = Integer.parseInt(rs.getString("availability"));
                int resource = Integer.parseInt(rs.getString("resource"));
                int numAssigneesRequired = Integer.parseInt(rs.getString("numAssigneesRequired"));
                String status = rs.getString("status");
                int owner = Integer.parseInt(rs.getString("owner"));
                Manager manager = UserHelper.getManagerById(owner);
                tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), deadline, Group.valueOf(group), Status.valueOf(status), numAssigneesRequired, manager));
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
        } catch (ParseException e) {
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
        return tasks;
    }

    public static void getTask() {
    }

    public static void updateTaskStatus() {
    }

    public static void sendInviteToSelectTeamMembers() {
    }

    public static void updateTaskAssignees() {
    }
}
