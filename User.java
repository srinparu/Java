
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.io.Serializable;

/*
 * class User
 * @param serialversionUID : version control in a serializable class
 */
public class User implements Serializable {
	private static final long serialVersionUID = 8084523177681775893L;

	private String roleType;

	public User(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

}