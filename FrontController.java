// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

/**
 * Java implementation of Front Controller Design Pattern.
 */
public class FrontController {
	// Dispatcher instance...
	private Dispatcher dispatcher;

	private int flag = 0;

	/**
	 * Front Controller Constructor
	 */
	public FrontController() {
		dispatcher = new Dispatcher();

	}

	/**
	 * Attempt to authentic user login.
	 * 
	 * @return T/F
	 */
	private int isAuthenticUser(int j) {
		// System.out.println("Inside front controller :"+j);
		if (j == 2) {
			System.out.println("User is authenticated successfully.");
			return flag = 1;
		} else if (j == 1) {
			System.out.println("Admin is authenticated successfully.");
			return flag = 1;
		} else {
			System.out.println("Authentication failed");
			return flag = 0;
		}

	}

	/**
	 * Responsible for dispatching the request to the Dispatcher.
	 * 
	 * @param request
	 */
	public void dispatchRequest(String request, int j) {
		System.out.println("Page Requested: " + request);

		// If the user has been authenticated - dispatch request...
		int a = isAuthenticUser(j);
		if (a == 1) {
			dispatcher.dispatch(request);
		}
	}
}