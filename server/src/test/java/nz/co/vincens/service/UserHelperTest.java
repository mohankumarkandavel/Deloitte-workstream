package nz.co.vincens.service;

import nz.co.vincens.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class UserHelperTest {

    @Test
    public void loginSucceedManager() throws Exception {
        assertEquals(1, UserHelper.login("james", "123"));
    }

    @Test
    public void loginSucceedTeamMember() throws Exception {
        assertEquals(12, UserHelper.login("bob", "123"));
    }

    @Test
    public void loginFailManager() throws Exception {
        assertEquals(0, UserHelper.login("james", "000000"));
    }

    @Test
    public void loginFailTeamMember() throws Exception {
        assertEquals(0, UserHelper.login("bob", "000000"));
    }

    @Test
    public void loginFailEmptyInput() throws Exception {
        assertEquals(0, UserHelper.login("''", "''"));
    }

    @Test
    public void getManagerDetailSuccessManager() throws Exception {
       // assertEquals("1" + "" + "james" + "James Too" + "123" + "James@hotmail.com" + "manager", UserHelper.getUserRole(1).getId() + UserHelper.getUserDetail(1).getUsername() + UserHelper.getUserDetail(1).getName() + UserHelper.getUserDetail(1).getPassword() + UserHelper.getUserDetail(1).getEmail() + UserHelper.getUserDetail(1).getRole());
    }

    @Test
    public void getManagerDetailSuccessTeamMember() throws Exception {
        //assertEquals("12" + "bob" + "Bob Ross" + "123" + "Bob@ross.com" + "team-member", UserHelper.getUserRole(12).getId() + UserHelper.getUserDetail(12).getUsername() + UserHelper.getUserDetail(12).getName() + UserHelper.getUserDetail(12).getPassword() + UserHelper.getUserDetail(12).getEmail() + UserHelper.getUserDetail(12).getRole());
    }
}
