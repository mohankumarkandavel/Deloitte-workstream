package nz.co.vincens.login;

import nz.co.vincens.model.Manager;
import nz.co.vincens.model.TeamMember;
import nz.co.vincens.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new Manager("1", "Manager", "James Too", "James@hotmail.com", "123"));
        users.add(new TeamMember("1", "Team Member", "Bob Ross", "Bob@ross.com",  "123"));
    }

    public List<User> getUsers() {
        return users;
    }

}
