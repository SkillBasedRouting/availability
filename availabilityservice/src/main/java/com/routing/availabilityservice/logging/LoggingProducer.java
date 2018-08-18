package com.routing.availabilityservice.logging;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggingProducer {

	private static final String DEFAULT_CATEGORY = "com.routing.availability";

	@Produces
	public Logger getLogger(final InjectionPoint injectionPoint) {
		if (null != injectionPoint && null != injectionPoint.getMember()) {
			return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
		} else {
			return Logger.getLogger(DEFAULT_CATEGORY);
		}
	}

}
