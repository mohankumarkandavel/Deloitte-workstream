import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Fraser Lewis-Smith on 22/08/2017.
 */
public class Task {
    private int id;
    private String name;
    private String description;
    private Date deadline;
    private BigDecimal cost;
    private Group group;
    private int numAssigneesRequired;
    private String resources;
    private Status status;
    private TeamMember[] assignees;
}
