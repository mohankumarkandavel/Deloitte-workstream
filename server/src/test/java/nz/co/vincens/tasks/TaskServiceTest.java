package nz.co.vincens.tasks;

import nz.co.vincens.service.TaskHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskServiceTest {
    @Test
    public void updateToAssignedSucceed() throws Exception {
        assertEquals("Assigned", TaskHelper.updateToAssigned(12));
    }

    @Test
    public void updateToCompletedSucceed() throws Exception {
        assertEquals("Completed", TaskHelper.updateToCompleted(12));
    }

    @Test
    public void updateToDeclinedSucceed() throws Exception {
        assertEquals("Draft", TaskHelper.updateToDeclined(12));
    }
}