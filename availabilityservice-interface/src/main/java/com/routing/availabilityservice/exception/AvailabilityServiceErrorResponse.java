package com.routing.availabilityservice.exception;

/**
 * <b>com.routing.availabilityservice.exception.AvailabilityServiceErrorResponse</b>
 * <p>
 *   Error entity of api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class AvailabilityServiceErrorResponse {

	private Integer reasonCode;
	private String message;

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailabilityServiceError [reasonCode=");
		builder.append(reasonCode);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
