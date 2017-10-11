package nz.co.vincens.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The possible groups a {@link Task} can belong to. A task can only belong to one group.
 */
public enum Group {
	@JsonProperty("Financial Analysis")
	FINANCIAL_ANALYSIS,
	@JsonProperty("Project Management")
	PROJECT_MANAGEMENT,
	@JsonProperty("Strategy")
	STRATEGY,
	@JsonProperty("Operations")
	OPERATIONS,
	@JsonProperty("Technology")
	TECHNOLOGY,
	@JsonProperty("Human Capital")
	HUMAN_CAPITAL,
	@JsonProperty("Software")
	SOFTWARE,
	@JsonProperty("Teaching and Training")
	TEACHING_AND_TRAINING,
	@JsonProperty("Business and Development")
	BUSINESS_AND_DEVELOPMENT,
	@JsonProperty("Marketing and Sales")
	MARKETING_AND_SALES
}
