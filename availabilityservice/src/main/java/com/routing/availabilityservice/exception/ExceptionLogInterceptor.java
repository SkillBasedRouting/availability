package com.routing.availabilityservice.exception;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * <b>com.routing.availabilyservice.exception.ExceptionLogInterceptor</b>
 * <p>
 *   Interceptor that logs all occured exceptions and throws generic error if exception is unexpected
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ExceptionLogInterceptor {

	@Inject
	private Logger logger;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		try {
			return ctx.proceed();
		} catch (AvailabilityServiceException e) {
			this.logger.error("error: " + e);
			throw e;
		} catch (Exception e) {
			this.logger.error("unexpected error: " + e);
			e.printStackTrace();
			throw AvailabilityServiceException.GENERIC_ERROR;
		}
	}

}
