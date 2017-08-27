package model;

import java.util.Date;

/**
 * A task which team members get assigned to.
 */
public class Task {
	private int id;
	private String name;
	private String description;

	private Date deadline;
	private Group group;
	private Status status;
	private long cost;
	private int numAssigneesRequired;
	private String resources;
	private TeamMember[] assignees;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public Group getGroup() {
		return group;
	}

	public Status getStatus() {
		return status;
	}

	public long getCost() {
		return cost;
	}

	public int getNumAssigneesRequired() {
		return numAssigneesRequired;
	}

	public String getResources() {
		return resources;
	}

	public TeamMember[] getAssignees() {
		return assignees;
	}
}
