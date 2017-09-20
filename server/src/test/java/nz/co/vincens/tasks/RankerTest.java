package nz.co.vincens.tasks;

import nz.co.vincens.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Task the {@link Ranker} class.
 */
public class RankerTest {

	private Ranker ranker;
	private List<TeamMember> teamMembers;
	private Task task;

	@Before
	public void setUp() {
		ranker = new Ranker();
		teamMembers = new ArrayList<>();
	}

	@Test
	public void findBestTeamMembers() throws Exception {
		task = new Task(1, "Prepare Financial Model", "For the period ending 1/09/2018", new Attribute(2, 1, 1,
				1), new Date(), Group.BUSINESS_DEVELOPMENT, Status.PENDING, 1);

		for (int i = 0; i < 5; i++) {
			HashMap<Group, Attribute> weightings = new HashMap<>();
			weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(i, i, i + 1, i));
			TeamMember teamMember = new TeamMember("Member " + i, i + "@email.com", String.valueOf(i), weightings);
			teamMembers.add(teamMember);
		}

		List<TeamMember> rankedTeamMembers = ranker.findBestTeamMembers(task, teamMembers);

		Assert.assertEquals("4", rankedTeamMembers.get(0).getId());
		Assert.assertEquals("3", rankedTeamMembers.get(1).getId());
		Assert.assertEquals("2", rankedTeamMembers.get(2).getId());
		Assert.assertEquals("1", rankedTeamMembers.get(3).getId());
		Assert.assertEquals("0", rankedTeamMembers.get(4).getId());
		Assert.assertEquals(rankedTeamMembers.size(), 5);
	}

	@Test
	public void unavailableTeamMember() {
		task = new Task(1, "Prepare Financial Model", "For the period ending 1/09/2018", new Attribute(2, 1, 1,
				1), new Date(), Group.BUSINESS_DEVELOPMENT, Status.PENDING, 1);

		HashMap<Group, Attribute> weightings = new HashMap<>();
		weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(1,1, 0, 1));
		TeamMember teamMember = new TeamMember("Member " + 0, 0 + "@email.com", String.valueOf(0), weightings);
		teamMembers.add(teamMember);
		List<TeamMember> rankedTeamMembers = ranker.findBestTeamMembers(task, teamMembers);

		Assert.assertEquals(0, rankedTeamMembers.size());
	}

}