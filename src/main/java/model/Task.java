package model;

import java.util.Date;

/**
 * A task which team members get assigned to.
 */
public class Task {
	private int id;
	private String name;
	private String description;

	private Attribute attribute;
	private Date deadline;
	private Group group;
	private Status status;
	private int numAssigneesRequired;
	private int resources;
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

	public int getAvailability() {
		return attribute.getAvailability();
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

	public int getNumAssigneesRequired() {
		return numAssigneesRequired;
	}

	public int getResources() {
		return attribute.getResource();
	}

	public TeamMember[] getAssignees() {
		return assignees;
	}

}
