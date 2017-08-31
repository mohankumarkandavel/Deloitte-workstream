package nz.co.vincens.tasks;

import nz.co.vincens.model.TeamMember;
import org.junit.Before;
import org.junit.Test;

/**
 * Task the {@link Ranker} class.
 */
public class RankerTest {

	TeamMember[] teamMembers;

	@Before
	public void setUp() {
		teamMembers = new TeamMember[10];
		for (int i = 0; i < 10; i++) {
			TeamMember teamMember = new TeamMember("TeamMember" + i, i + "@email.com", "i");
			teamMembers[0] = teamMember;
		}
	}

	@Test
	public void findBestTeamMembers() throws Exception {
	}

	@Test
	public void insert() throws Exception {
	}

}