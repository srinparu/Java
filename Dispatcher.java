// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

/**
 * Dispatcher Class
 */
public class Dispatcher {
	// Concrete Views...
	/**
	 * Based upon the request - dispatch the view.
	 * 
	 * @param request
	 */
	public static AbstractFactory dispatch(String choice) {
		// System.out.println("Inside Dispatcher to check choice:"+choice);
		// Faculty or Student Views...
		// System.out.println("In Dispatcher");
		if (choice.equalsIgnoreCase("admin")) {
			return new AdminFactory();
		} else if (choice.equalsIgnoreCase("customer")) {
			return new CustomerFactory();
		}
		return null;
	}
}
