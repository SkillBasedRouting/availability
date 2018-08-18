package com.routing.availabilityservice.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * <b>com.routing.availabilyservice.api.ApiActivator</b>
 * <p>
 *   JAX-RS activator
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationPath("/api/v1")
public class ApiActivator extends Application {

	public ApiActivator() {
		super();

		// configure values for swagger doc
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("AvailabilityService");
		beanConfig.setVersion("0.0.1-SNAPSHOT");
		beanConfig.setHost("localhost");
		beanConfig.setBasePath("api/v1");
		beanConfig.setScan(true);
		beanConfig.setResourcePackage("com.routing.availabilityservice");
		beanConfig.setContact("Arndt Schwenkschuster (arndt@schwenkschuster.de)");
	}

}
