package nz.co.vincens.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserHelperTest {
    @Test
    public void LoginSucceedManager() throws Exception {
        assertEquals(1, UserHelper.Login("10001", "111111"));
    }

    @Test
    public void LoginSucceedEmployee() throws Exception {
        assertEquals(2, UserHelper.Login("10002", "222222"));
    }

    @Test
    public void LoginFailManager() throws Exception {
        assertEquals(0, UserHelper.Login("10001", "555555"));
    }

    @Test
    public void LoginFailEmployee() throws Exception {
        assertEquals(0, UserHelper.Login("10002", "555555"));
    }

    @Test
    public void LoginFailEmpty() throws Exception {
        assertEquals(0, UserHelper.Login("''", "''"));
    }

}
