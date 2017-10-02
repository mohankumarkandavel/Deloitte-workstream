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
    public void loadInfoSuccessManager() throws Exception {
        assertEquals("1" + "" + "james" + "James Too" + "123" + "James@hotmail.com" + "manager", UserHelper.loadUserInfo(1).getId() + UserHelper.loadUserInfo(1).getUsername() + UserHelper.loadUserInfo(1).getName() + UserHelper.loadUserInfo(1).getPassword() + UserHelper.loadUserInfo(1).getEmail() + UserHelper.loadUserInfo(1).getRole());
    }

    @Test
    public void loadInfoSuccessTeamMember() throws Exception {
        assertEquals("12" + "bob" + "Bob Ross" + "123" + "Bob@ross.com" + "team-member", UserHelper.loadUserInfo(12).getId() + UserHelper.loadUserInfo(12).getUsername() + UserHelper.loadUserInfo(12).getName() + UserHelper.loadUserInfo(12).getPassword() + UserHelper.loadUserInfo(12).getEmail() + UserHelper.loadUserInfo(12).getRole());
    }
}
