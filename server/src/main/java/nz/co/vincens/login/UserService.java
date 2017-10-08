package nz.co.vincens.login;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Manager;
import nz.co.vincens.model.TeamMember;
import nz.co.vincens.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new Manager(String.valueOf(users.size()+1), "Manager", "James Too", "James@hotmail.com", "123"));

        HashMap<Group, Attribute> weightings;

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(4, 2, 3, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Amy Lin", "xlin504", "xlin504@aucklanduni.ac.nz", String.valueOf(users.size()+1),
				weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(1, 1, 2, 2));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Kelvin Lau", "klau158", "klau158@aucklanduni.ac.nz",String.valueOf(users.size()+1),
				weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(3, 2, 4, 3));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Bowen Wu", "bowen", "bowen@aucklanduni.ac.nz",String.valueOf(users.size()+1),
				weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(1, 3, 5, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Shenghong Huang", "shenghong", "shenghong@aucklanduni.ac.nz", String.valueOf(users
				.size()+1), weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(2, 4, 3, 2));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Rick Ying", "rick", "rick@aucklanduni.ac.nz", String.valueOf(users.size()+1),
				weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(4, 3, 1, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Mohan Kumar", "mohan", "mohan@aucklanduni.ac.nz",String.valueOf(users.size()+1),
				weightings, "123"));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(5, 5, 5, 5));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        users.add(new TeamMember("Fraser Lewis-Smith", "fralewsmi", "fraser@aucklanduni.ac.nz",String.valueOf(users
				.size()+1), weightings, "123"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user:users) {
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
