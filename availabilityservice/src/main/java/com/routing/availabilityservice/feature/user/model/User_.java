package com.routing.availabilityservice.feature.user.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * <b>com.routing.availabilyservice.feature.user.model.User_</b>
 * <p>
 *   JPA meta model class for {@link User}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(User.class)
public class User_ {

	public static transient SingularAttribute<User, Integer> internId;
	public static transient SingularAttribute<User, String> id;

}
