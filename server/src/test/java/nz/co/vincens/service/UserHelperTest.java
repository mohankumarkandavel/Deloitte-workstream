package nz.co.vincens.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserHelperTest {

    @Test
    public void loginSucceedManager() throws Exception {
        assertEquals(1, UserHelper.login("Manager", "123"));
    }

    @Test
    public void loginSucceedTeamMember() throws Exception {
        assertEquals(5, UserHelper.login("Shenghong", "123"));
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
}
