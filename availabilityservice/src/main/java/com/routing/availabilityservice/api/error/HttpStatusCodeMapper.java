package com.routing.availabilityservice.api.error;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.resteasy.util.HttpResponseCodes;

import com.routing.availabilityservice.exception.AvailabilityServiceException;
import com.routing.availabilityservice.feature.user.exception.UserException;
import com.routing.availabilityservice.feature.user.exception.UserNotFoundException;

/**
 * <b>com.routing.availabilyservice.api.error.HttpStatusCodeMapper</b>
 * <p>
 *   Holds http status for each exception
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class HttpStatusCodeMapper {

	private Map<AvailabilityServiceException, Integer> refCodes;
	private Map<Class<? extends AvailabilityServiceException>, Integer> typeCodes;

	@PostConstruct
	private void init() {

		this.refCodes = new HashMap<>(5);
		this.typeCodes = new HashMap<>(5);

		this.refCodes.put(UserException.REQUIRED, HttpResponseCodes.SC_BAD_REQUEST);
		this.refCodes.put(UserException.ID_REQUIRED, HttpResponseCodes.SC_BAD_REQUEST);

		this.typeCodes.put(UserNotFoundException.class, HttpResponseCodes.SC_NOT_FOUND);
	}

	/**
	 * get http status code of exception, if no explicit status is set 500 will be returned
	 * @param e the occured exception
	 * @return http status code
	 */
	public Integer getStatus(final AvailabilityServiceException e) {

		Integer status = this.refCodes.get(e);
		if (null == status) {
			status = this.typeCodes.get(e.getClass());
		}
		if (null == status) {
			status = HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
		}

		return status;
	}

}
