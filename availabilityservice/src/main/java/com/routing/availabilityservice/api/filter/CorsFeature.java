package com.routing.availabilityservice.api.filter;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * <b>com.routing.availabilyservice.api.filter.CorsFeature</b>
 * <p>
 * Feature to enable cross origin requests
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Provider
public class CorsFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        context.register(corsFilter);
        return true;
    }

}
