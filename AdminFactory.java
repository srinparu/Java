// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

public class AdminFactory extends AbstractFactory {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractFactory#getPage(java.lang.String)
	 */
	Abstract getPage(String page) {

		// TODO Auto-generated method stub
		if (page.equalsIgnoreCase("admin")) {
			return new AdminPage();
		}
		return null;

	}

}
