package nz.co.vincens.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DatabaseHelperTest {
    @Test
    public void connectionSucceed() throws Exception {
        assertEquals(1, DatabaseHelper.databaseConnection());
    }

    @Test
    public void connectionNotFail() throws Exception {
        assertNotEquals(0, DatabaseHelper.databaseConnection());
    }

    @Test
    public void driverUsable() throws Exception {
        assertNotEquals(-1, DatabaseHelper.databaseConnection());
    }
}
