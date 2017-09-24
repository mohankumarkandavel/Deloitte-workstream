package nz.co.vincens.service;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseHelperTest {
    @Test
    public void Connection() throws Exception {
        assertEquals(0, UserHelper.Login("''", "''"));
    }
}
