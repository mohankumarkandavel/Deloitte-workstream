package nz.co.vincens.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The possible groups a {@link Task} can belong to. A task can only belong to one group.
 */
public enum Group {
	@JsonProperty("Financial Analysis")
	FINANCIAL_ANALYSIS,
	PROJECT_MANAGEMENT,
	STRATEGY,
	OPERATIONS,
	TECHNOLOGY,
	HUMAN_CAPITAL,
	SOFTWARE,
	TEACHING_AND_TRAINING,
	BUSINESS_DEVELOPMENT,
	MARKETING_AND_SALES
}
