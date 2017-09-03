package nz.co.vincens.model;

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
	private TeamMember[] assignees;

	public Task() {

	}

	public Task(int id, String name, String description, Attribute attribute, Date deadline, Group group, Status
			status, int numAssigneesRequired, int resources) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.attribute = attribute;
		this.deadline = deadline;
		this.group = group;
		this.status = status;
		this.numAssigneesRequired = numAssigneesRequired;
	}

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

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setNumAssigneesRequired(int numAssigneesRequired) {
		this.numAssigneesRequired = numAssigneesRequired;
	}

	public void setAssignees(TeamMember[] assignees) {
		this.assignees = assignees;
	}
}
