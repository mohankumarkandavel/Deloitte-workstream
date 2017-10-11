package nz.co.vincens.tasks;

import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import nz.co.vincens.service.TaskHelper;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * This class is to test tables related to the task connection to the database.
 * before testing, it need to check the related data has already in the database, otherwise, it would return wrong result.
 * Notice:
 * Sequence 1: 1. Add task; 2. sent request 3. accept 4. mark as done
 * Sequence 2: 1. Add task; 2. sent request 3. decline
 */
public class TaskServiceTest {
    /**
     * Check the task with id=12 is exist in the task table
     * Accepted a task allocated
     */
    @Test
    public void updateToAssignedSucceed() throws Exception {
        assertEquals("Assigned", TaskHelper.updateToAssigned(12));
    }

    /**
     * Check the task with id=12 is exist in the task table
     * Mark a task as done
     */
    @Test
    public void updateToCompletedSucceed() throws Exception {
        assertEquals("Completed", TaskHelper.updateToCompleted(12));
    }

    /**
     * Check the task with id=12 is exist in the task table
     * Decline a task
     */
    @Test
    public void updateToDeclinedSucceed() throws Exception {
        assertEquals("Draft", TaskHelper.updateToDeclined(12, "Reasons"));
    }

    /**
     * Test the add a new task
     */
    @Test
    public void addNewTask() throws Exception {
        Task task = new Task();
        task.setName("Junit2");
        task.setDescription("Junit2 Description");
        task.setDeadline(new Date());
        task.setGroup(Group.FINANCIAL_ANALYSIS);
        task.setStatus(Status.DRAFT);
        task.setNumAssigneesRequired(1);
        assertEquals(1, TaskHelper.addTask(task));
    }

    /**
     * The the send request to the team member
     */
    @Test
    public void sendRequestToTeamMember() throws Exception {
        int taskId = TaskHelper.getIdAfterInsert();
        assertEquals(1, TaskHelper.updateToPending(taskId));
        int lastListId = TaskHelper.getLastAssigneesListId();
        assertEquals(1, TaskHelper.createNewAssigneesList((lastListId + 1), 1));
        assertEquals(1, TaskHelper.updateAssignedDetails((lastListId + 1), taskId));
    }

    /**
     * Team member accept the allocation
     */
    @Test
    public void acceptAllocation() throws Exception {
        int lastListId = TaskHelper.getLastAssigneesListId();
        assertEquals("Assigned", TaskHelper.updateToAssigned(lastListId));
    }

    /**
     * Team member mark a task as done
     */
    public void completeTask() throws Exception {
        int lastListId = TaskHelper.getLastAssigneesListId();
        assertEquals("Completed", TaskHelper.updateToCompleted(lastListId));
    }

    /**
     * Team member decline a task
     */
    public void declineTask() {
        int lastListId = TaskHelper.getLastAssigneesListId();
        assertEquals("Draft", TaskHelper.updateToDeclined(lastListId, "No time"));
    }

}