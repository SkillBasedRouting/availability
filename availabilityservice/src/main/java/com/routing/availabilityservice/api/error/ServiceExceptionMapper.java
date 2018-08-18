package com.routing.availabilityservice.api.error;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.routing.availabilityservice.exception.AvailabilityServiceErrorResponse;
import com.routing.availabilityservice.exception.AvailabilityServiceException;

/**
 * <b>com.routing.availabilyservice.api.error.ServiceExceptionMapper</b>
 * <p>
 *   Catches any exception and returns it as json
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<AvailabilityServiceException> {

	@Inject
	private HttpStatusCodeMapper statusCodeMapper;

	@Override
	public Response toResponse(final AvailabilityServiceException e) {

		final Integer status = this.statusCodeMapper.getStatus(e);

		final AvailabilityServiceErrorResponse respEntity = this.map(e);

		return Response.status(status).entity(respEntity).build();
	}

	private AvailabilityServiceErrorResponse map(final AvailabilityServiceException e) {
		final AvailabilityServiceErrorResponse resp = new AvailabilityServiceErrorResponse();
		resp.setMessage(e.getMessage());
		resp.setReasonCode(e.getReasonCode());
		return resp;
	}

}
