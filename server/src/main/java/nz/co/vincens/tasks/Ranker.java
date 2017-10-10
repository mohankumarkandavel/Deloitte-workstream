package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to find the best team members and rank them according to their suitability for a task.
 * The algorithm used is 0.2*x + 0.2*y + 0.5*z + 0.1*k where x is the experience, y is the interest, z is the
 * availability, and k is the resources for the task's given {@link nz.co.vincens.model.Group}. In addition, anyone who doesn't
 * have the required availability should automatically be disqualified from being considered.
 */
public class Ranker {

    /**
     * Find the best members to complete a task according to the algorithm.
     *
     * @param task        The task which needs completing
     * @param teamMembers All possible team members which may be assigned to the task
     * @return A ranked list of team members best suited for job in descending order
     */
    public List<TeamMember> findBestTeamMembers(Task task, List<TeamMember> teamMembers) {

        List<TeamMember> rankedTeamMembers = new ArrayList<>();

        Group taskGroup = task.getGroup();
        // get the availability, experience, interest and resource values for each team member
        for (TeamMember member : teamMembers) {
            Attribute attribute = member.getWeightings().get(taskGroup);
            if (attribute.getAvailability() >= task.getAvailability()) {
                insert(member, rankedTeamMembers, taskGroup);
            }
        }
        return rankedTeamMembers;
    }

    /**
     * Insert in descending order the best team members for the task after calculating the suitability.
     *
     * @param teamMember        The team member to calculate the suitability for, and the insert into the list to be returned
     * @param rankedTeamMembers List containing the ranked team members to return
     * @param group             The group to calculate the suitability for
     */
    private void insert(TeamMember teamMember, List<TeamMember> rankedTeamMembers, Group group) {
        double teamMemberSuitability = calculateSuitability(teamMember.getWeightings().get(group));

        boolean success = false;
        for (int i = 0; i < rankedTeamMembers.size(); i++) {
            double currentTeamMemberSuitability = calculateSuitability(rankedTeamMembers.get(i).getWeightings().get
                    (group));
            if (currentTeamMemberSuitability < teamMemberSuitability) {
                rankedTeamMembers.add(i, teamMember);
                success = true;
                break;
            }
        }
        if (!success) rankedTeamMembers.add(teamMember);
    }

    private double calculateSuitability(Attribute attribute) {
        return calculateSuitability(attribute.getExperience(), attribute.getInterest(), attribute.getAvailability(),
                attribute.getResource());
    }

    private double calculateSuitability(int experience, int interest, int availability, int resources) {
        return 0.2 * (double) experience + 0.2 * (double) interest + 0.5 * (double) availability + 0.1 * (double)
                resources;
    }
}