
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.io.Serializable;

/*
 * Role class 
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 4002893177681775893L;

	private String str;

	public Role(String str) {
		this.str = str;
	}

	/*
	 * getting type of role
	 */
	public String getRoleType() {
		return str;
	}

}