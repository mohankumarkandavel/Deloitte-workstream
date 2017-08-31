package nz.co.vincens.model;

/**
 * Attribute contains the list of fields required by the algorithm.
 */
public class Attribute {

	private int experience;
	private int interest;
	private int availability;
	private int resource;

	public Attribute(int experience, int interest, int availability, int resource) {
		this.experience = experience;
		this.interest = interest;
		this.availability = availability;
		this.resource = resource;
	}

	public int getExperience() {
		return experience;
	}

	public int getInterest() {
		return interest;
	}

	public int getAvailability() {
		return availability;
	}

	public int getResource() {
		return resource;
	}

}
