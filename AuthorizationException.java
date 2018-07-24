// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

/*
 * AuthorizationException class 
 * @param serialversionUID : version control in a serializable class
 */
public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 6631212690278423524L;

	public AuthorizationException(String methodName) {
		super("Invalid Authorization - Access Denined to " + methodName + "() function!");
	}
}