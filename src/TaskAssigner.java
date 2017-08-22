/**
 * Created by Fraser Lewis-Smith on 22/08/2017.
 */
public class TaskAssigner {
    public void assignTask(Task task, TeamMember[] teamMembers) {
        double priority = calculatePriority(0,0,0,0);
    }
    private Double calculatePriority(int x, int y, int z, int k) {
        return 0.2*x + 0.2*y + 0.5*z + 0.1*k;
    }
}
