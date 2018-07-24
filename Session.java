
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.io.Serializable;
/*
 * class User
 * @param serialversionUID : version control in a serializable class
 */

public class Session implements Serializable {
	private static final long serialVersionUID = -3653573220581903527L;

	private Role type;

	public Session(String str) {
		type = new Role(str);
	}

	public Role getUser() {
		return type;
	}
}