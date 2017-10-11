package nz.co.vincens.tasks;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.*;
import nz.co.vincens.service.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A service which provides access to tasks
 */
@Service
public class TaskService {
    Manager manager;
    Task task;
    private List<Task> tasks = new ArrayList<>();
    @Autowired
    private UserService userService;

    public TaskService() {
    }

    public void getAllTasksDraft() {
        int id;
        String name;
        String description;
        int experience;
        int interest;
        int availability;
        int resource;
        Date deadline;
        String group;
        //String status;
        int numAssigneesRequired;
        int ownerId;
        String ownerName;
        int assigneesId;
        String assigneesName;
        // Generate database query sentence
        String sql = "CALL Task_getTasksDraft()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                id = Integer.parseInt(rs.getString("id"));
                name = rs.getString("name");
                description = rs.getString("description");
                experience = Integer.parseInt(rs.getString("experience"));
                interest = Integer.parseInt(rs.getString("interest"));
                availability = Integer.parseInt(rs.getString("resource"));
                resource = Integer.parseInt(rs.getString("resource"));
                //deadline = formatter.parse(rs.getString("deadline"));
                group = rs.getString("group");
                numAssigneesRequired = Integer.parseInt(rs.getString("numAssigneesRequired"));
                ownerId = Integer.parseInt(rs.getString("owner"));
                manager = (Manager) userService.getUsers().get(0);
                switch (group) {
                    case "FINANCIAL_ANALYSIS":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.FINANCIAL_ANALYSIS, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "PROJECT_MANAGEMENT":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.PROJECT_MANAGEMENT, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "STRATEGY":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.STRATEGY, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "OPERATIONS":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.OPERATIONS, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "TECHNOLOGY":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.TECHNOLOGY, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "HUMAN_CAPITAL":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.HUMAN_CAPITAL, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "SOFTWARE":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.SOFTWARE, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "TEACHING_AND_TRAINING":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.TEACHING_AND_TRAINING, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                    case "BUSINESS_AND_DEVELOPMENT":
                        tasks.add(new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date(),
                                Group.BUSINESS_AND_DEVELOPMENT, Status.DRAFT, numAssigneesRequired, manager));
                        break;
                }
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
    }

    public void getAllTasksPending() {
        int id;
        String name;
        String description;
        int experience;
        int interest;
        int availability;
        int resource;
        Date deadline;
        String group;
        //String status;
        int numAssigneesRequired;
        int ownerId;
        String ownerName;
        int assigneesId;
        String assigneesName;
        // Generate database query sentence
        String sql = "CALL Task_getTasksPending()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                id = Integer.parseInt(rs.getString("id"));
                name = rs.getString("name");
                description = rs.getString("description");
                experience = Integer.parseInt(rs.getString("experience"));
                interest = Integer.parseInt(rs.getString("interest"));
                availability = Integer.parseInt(rs.getString("resource"));
                resource = Integer.parseInt(rs.getString("resource"));
                //deadline = formatter.parse(rs.getString("deadline"));
                group = rs.getString("group");
                numAssigneesRequired = Integer.parseInt(rs.getString("numAssigneesRequired"));
                ownerId = Integer.parseInt(rs.getString("owner"));
                assigneesId = Integer.parseInt(rs.getString("teammemberId"));
                manager = (Manager) userService.getUsers().get(0);
                switch (group) {
                    case "FINANCIAL_ANALYSIS":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.FINANCIAL_ANALYSIS, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "PROJECT_MANAGEMENT":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.PROJECT_MANAGEMENT, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "STRATEGY":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.STRATEGY, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "OPERATIONS":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.OPERATIONS, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "TECHNOLOGY":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.TECHNOLOGY, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "HUMAN_CAPITAL":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.HUMAN_CAPITAL, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "SOFTWARE":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.SOFTWARE, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "TEACHING_AND_TRAINING":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.TEACHING_AND_TRAINING, Status.PENDING, numAssigneesRequired, manager);
                        break;
                    case "BUSINESS_AND_DEVELOPMENT":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.BUSINESS_AND_DEVELOPMENT, Status.PENDING, numAssigneesRequired, manager);
                        break;
                }
                task.addRequestedAssignee((TeamMember) userService.getUser(assigneesId));
                tasks.add(task);
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
    }

    public void getAllTasksAssigned() {
        int id;
        String name;
        String description;
        int experience;
        int interest;
        int availability;
        int resource;
        Date deadline;
        String group;
        //String status;
        int numAssigneesRequired;
        int ownerId;
        String ownerName;
        int assigneesId;
        String assigneesName;
        // Generate database query sentence
        String sql = "CALL Task_getTasksAssigned()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
                name = rs.getString("name");
                description = rs.getString("description");
                experience = Integer.parseInt(rs.getString("experience"));
                interest = Integer.parseInt(rs.getString("interest"));
                availability = Integer.parseInt(rs.getString("resource"));
                resource = Integer.parseInt(rs.getString("resource"));
                //deadline = formatter.parse(rs.getString("deadline"));
                group = rs.getString("group");
                numAssigneesRequired = Integer.parseInt(rs.getString("numAssigneesRequired"));
                ownerId = Integer.parseInt(rs.getString("owner"));
                assigneesId = Integer.parseInt(rs.getString("teammemberId"));
                manager = (Manager) userService.getUsers().get(0);
//                manager = (Manager) userService.getUsers().get(ownerId);
                switch (group) {
                    case "FINANCIAL_ANALYSIS":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.FINANCIAL_ANALYSIS, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "PROJECT_MANAGEMENT":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.PROJECT_MANAGEMENT, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "STRATEGY":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.STRATEGY, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "OPERATIONS":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.OPERATIONS, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "TECHNOLOGY":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.TECHNOLOGY, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "HUMAN_CAPITAL":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.HUMAN_CAPITAL, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "SOFTWARE":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.SOFTWARE, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "TEACHING_AND_TRAINING":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.TEACHING_AND_TRAINING, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                    case "BUSINESS_AND_DEVELOPMENT":
                        task = new Task(id, name, description, new Attribute(experience, interest, availability, resource), new Date()
                                , Group.BUSINESS_AND_DEVELOPMENT, Status.ASSIGNED, numAssigneesRequired, manager);
                        break;
                }
                task.addAssignee((TeamMember) userService.getUser(assigneesId));
                tasks.add(task);
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
    }


    @PostConstruct
    private void init() {
        getAllTasksDraft();
        getAllTasksPending();
        getAllTasksAssigned();
    }

    /**
     * @return all tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param id the task id
     * @return task with specified id
     */
    public Task getTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }

    /**
     * Adds the specified task
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
}
