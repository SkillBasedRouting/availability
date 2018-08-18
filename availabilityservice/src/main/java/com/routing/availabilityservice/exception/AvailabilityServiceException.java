package com.routing.availabilityservice.exception;

import javax.ejb.ApplicationException;
import javax.ejb.EJBTransactionRolledbackException;

/**
 * <b>com.routing.availabilyservice.exception.AvailabilityServiceException</b>
 * <p>
 *   base exception for all custom exceptions, triggers a rollback in a transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationException(rollback = true)
public class AvailabilityServiceException extends EJBTransactionRolledbackException {

	private static final long serialVersionUID = 1L;

	public static final AvailabilityServiceException GENERIC_ERROR = new AvailabilityServiceException(0,
			"unexpected error");

	private Integer reasonCode;

	public AvailabilityServiceException(Integer reasonCode, String message) {
		super(message);
		this.reasonCode = reasonCode;
	}

	public Integer getReasonCode() {
		return reasonCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailabilityServiceException [reasonCode=");
		builder.append(reasonCode);
		builder.append(", message=");
		builder.append(super.getMessage());
		builder.append("]");
		return builder.toString();
	}

}
