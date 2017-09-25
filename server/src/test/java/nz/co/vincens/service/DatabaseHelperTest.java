package nz.co.vincens.service;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DatabaseHelperTest {
    @Test
    public void ConnectionSucceed() throws Exception {
        assertEquals(1, DatabaseHelper.DatabaseConnection());
    }

    @Test
    public void ConnectionNotFail() throws Exception {
        assertNotEquals(0, DatabaseHelper.DatabaseConnection());
    }

    @Test
    public void DriverUsable() throws Exception {
        assertNotEquals(-1, DatabaseHelper.DatabaseConnection());
    }
}
