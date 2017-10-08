package nz.co.vincens.login;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Manager;
import nz.co.vincens.model.TeamMember;
import nz.co.vincens.model.User;
import nz.co.vincens.service.DatabaseHelper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    HashMap<Group, Attribute> weightings;
    int teamMemberQuantity = 0;
    int teamMemberId = 0;
    int managerQuantity = 0;
    int managerId = 0;

    public void loadAllTeamMembers(int userId) {
        String userName = null;
        String name = null;
        String password = null;
        String email = null;
        String groupName = null;
        int experience;
        int interest;
        int availability;
        int resource;
        // Generate database query sentence
        String sql = "CALL User_getAllTeamMembers(" + userId + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            int i = 1;
            weightings = new HashMap<>();
            // Extract data from result set
            while (rs.next()) {
                userName = rs.getString("userName");
                name = rs.getString("name");
                password = rs.getString("password");
                email = rs.getString("email");
                //groupName = rs.getString("groupName");
                experience = Integer.parseInt(rs.getString("experience"));
                interest = Integer.parseInt(rs.getString("interest"));
                availability = Integer.parseInt(rs.getString("availability"));
                resource = Integer.parseInt(rs.getString("resource"));
                switch (i) {
                    case 1:
                        weightings.put(Group.FINANCIAL_ANALYSIS, new Attribute(experience, interest, availability, resource));
                        break;
                    case 2:
                        weightings.put(Group.PROJECT_MANAGEMENT, new Attribute(experience, interest, availability, resource));
                        break;
                    case 3:
                        weightings.put(Group.STRATEGY, new Attribute(experience, interest, availability, resource));
                        break;
                    case 4:
                        weightings.put(Group.OPERATIONS, new Attribute(experience, interest, availability, resource));
                        break;
                    case 5:
                        weightings.put(Group.TECHNOLOGY, new Attribute(experience, interest, availability, resource));
                        break;
                    case 6:
                        weightings.put(Group.HUMAN_CAPITAL, new Attribute(experience, interest, availability, resource));
                        break;
                    case 7:
                        weightings.put(Group.SOFTWARE, new Attribute(experience, interest, availability, resource));
                        break;
                    case 8:
                        weightings.put(Group.TEACHING_AND_TRAINING, new Attribute(experience, interest, availability, resource));
                        break;
                    case 9:
                        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(experience, interest, availability, resource));
                        break;
                    case 10:
                        weightings.put(Group.MARKETING_AND_SALES, new Attribute(experience, interest, availability, resource));
                        break;
                }
                i++;
            }
            users.add(new TeamMember(name, userName, email, String.valueOf(userId), weightings, password));
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

    public static int getTeamMemberQuantity() {
        int count = 0;
        // Generate database query sentence
        String sql = "CALL User_countNumberOfTeamMember()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                count = Integer.parseInt(rs.getString("count(*)"));
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
        return count;
    }

    public static int getFirstTeamMemberId() {
        int userId = 0;
        // Generate database query sentence
        String sql = "CALL User_getFirstTeamMemberId()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                userId = Integer.parseInt(rs.getString("id"));
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
        return userId;
    }

    public void loadAllManagers(int userId) {
        String userName = null;
        String name = null;
        String password = null;
        String email = null;
        // Generate database query sentence
        String sql = "CALL User_getAllManagers(" + userId + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            int i = 1;
            // Extract data from result set
            while (rs.next()) {
                userName = rs.getString("userName");
                name = rs.getString("name");
                password = rs.getString("password");
                email = rs.getString("email");
                users.add(new Manager(String.valueOf(userId), userName, name, email, password));
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

    public static int getManagerQuantity() {
        int count = 0;
        // Generate database query sentence
        String sql = "CALL User_countNumberOfManagers()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                count = Integer.parseInt(rs.getString("count(*)"));
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
        return count;
    }

    public static int getFirstManagerId() {
        int userId = 0;
        // Generate database query sentence
        String sql = "CALL User_getFirstManagerId()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                userId = Integer.parseInt(rs.getString("id"));
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
        return userId;
    }

    public UserService() {
        managerQuantity = getManagerQuantity();
        managerId = getFirstManagerId();
        for (int i = 1; i <= managerQuantity; i++) {
            loadAllManagers(managerId);
            managerId++;
        }

        HashMap<Group, Attribute> weightings;
        teamMemberQuantity = getTeamMemberQuantity();
        teamMemberId = getFirstTeamMemberId();
        for (int i = 1; i <= teamMemberQuantity; i++) {
            loadAllTeamMembers(teamMemberId);
            teamMemberId++;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (Integer.valueOf(user.getId()) == id) {
                return user;
            }
        }
        return null;
    }

    public List<TeamMember> getTeamMembers() {
        List<TeamMember> teamMembers = new ArrayList<>();
        users.stream()
                .filter(TeamMember.class::isInstance)
                .forEach(user -> teamMembers.add((TeamMember) user));
        return teamMembers;
    }
}