package nz.co.vincens.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A task which team members get assigned to.
 */
public class Task {
	private int id;
	private String name;
	private String description;

	@JsonProperty("attribute")
	private Attribute attribute;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	private Date deadline;
	private Group group;
	private Status status;
	private int numAssigneesRequired;
	@JsonSerialize(using=ManagerSerializer.class)
	@JsonDeserialize(using=ManagerDeserializer.class)
	private Manager owner;
	private List<TeamMember> requestedAssignees = new ArrayList<>();
	private List<TeamMember> assignees = new ArrayList<>();

	public Task() {

	}

	public Task(int id, String name, String description, Attribute attribute, Date deadline, Group group, Status
			status, int numAssigneesRequired, Manager owner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.attribute = attribute;
		this.deadline = deadline;
		this.group = group;
		this.status = status;
		this.numAssigneesRequired = numAssigneesRequired;
		this.owner = owner;
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

	public List<TeamMember> getRequestedAssignees() {
		return requestedAssignees;
	}

	public void setRequestedAssignees(List<TeamMember> requestedAssignees) {
		this.requestedAssignees = requestedAssignees;
	}

	public void addRequestedAssignee(TeamMember teamMember) {
		requestedAssignees.add(teamMember);
	}

	public void addAssignee(TeamMember teamMember) {
		requestedAssignees.remove(teamMember);
		assignees.add(teamMember);
	}

	public List<TeamMember> getAssignees() {
		return assignees;
	}


	public Manager getOwner() {
		return owner;
	}

	public void setOwner(Manager owner) {
		this.owner = owner;
	}
}
