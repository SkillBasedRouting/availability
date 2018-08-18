package com.routing.availabilityservice.feature.user.exception;

/**
 * <b>com.routing.availabilyservice.feature.user.exception.UserNotFoundException</b>
 * <p>
 *   Exception that is thrown when user was not found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserNotFoundException extends UserException {

	private static final long serialVersionUID = 1L;

	private String userId;

	public UserNotFoundException(final String userId) {
		super(0, "user " + userId + " not found");
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserNotFoundException [base=");
		builder.append(super.toString());
		builder.append("userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
