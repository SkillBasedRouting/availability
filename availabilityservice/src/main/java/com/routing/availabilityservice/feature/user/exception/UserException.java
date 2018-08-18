package com.routing.availabilityservice.feature.user.exception;

import com.routing.availabilityservice.exception.AvailabilityServiceException;

/**
 * <b>com.routing.availabilyservice.feature.user.exception.UserException</b>
 * <p>
 *   Exception for all user exceptions
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserException extends AvailabilityServiceException {

	private static final long serialVersionUID = 1L;

	public static final UserException REQUIRED = new UserException(101, "user is required");
	public static final UserException ID_REQUIRED = new UserException(103, "user id is required");

	public UserException(final int reasonCode, final String message) {
		super(reasonCode, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserException [base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
