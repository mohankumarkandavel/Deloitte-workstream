package nz.co.vincens.login;

import nz.co.vincens.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User("1", "James123", "James Too", "James@hotmail.com", "manager", "123"));
        users.add(new User("1", "Bob123", "Bob Ross", "Bob@ross.com", "team-member", "123"));
    }

    public List<User> getUsers() {
        return users;
    }

}
