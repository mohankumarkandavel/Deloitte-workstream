package tasks;

import model.Group;
import model.Task;
import model.TeamMember;

/**
 * This class is used to find the best team members and rank them according to their suitability for a task.
 * The algorithm used is 0.2*x + 0.2*y + 0.5*z + 0.1*k where x is the experience, y is the interest, z is the
 * availability, and k is the resources for the task's given {@link model.Group}.
 */
public class Ranker {
	public TeamMember[] findBestTeamMembers(Task task) {
		return null;
	}
}
