package nz.co.vincens.tasks;

import nz.co.vincens.service.TaskHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskServiceTest {
    /**
     * Accepted a task allocated
     */
    @Test
    public void updateToAssignedSucceed() throws Exception {
        assertEquals("Assigned", TaskHelper.updateToAssigned(12));
    }

    /**
     * Mark a task as done
     */
    @Test
    public void updateToCompletedSucceed() throws Exception {
        assertEquals("Completed", TaskHelper.updateToCompleted(12));
    }

    /**
     * Decline a task
     */
    @Test
    public void updateToDeclinedSucceed() throws Exception {
        assertEquals("Draft", TaskHelper.updateToDeclined(12, "Reasons"));
    }
}