package com.routing.availabilityservice.feature.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>com.routing.availabilyservice.feature.user.model.User</b>
 * <p>
 *   Model for user
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "AvailabilityService_User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer internId;

	@Column(name = "id", unique = true)
	private String id;

	public Integer getInternId() {
		return internId;
	}

	public void setInternId(Integer internId) {
		this.internId = internId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((internId == null) ? 0 : internId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (internId == null) {
			if (other.internId != null)
				return false;
		} else if (!internId.equals(other.internId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [internId=");
		builder.append(internId);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}