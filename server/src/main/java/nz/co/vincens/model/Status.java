package nz.co.vincens.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A {@link Task} can have any one of the following Statuses.
 */
public enum Status {
    @JsonProperty("Draft")
    DRAFT,
    @JsonProperty("Pending")
    PENDING,
    @JsonProperty("Assigned")
    ASSIGNED,
    @JsonProperty("Completed")
    COMPLETED
}
