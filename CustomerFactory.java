// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

public class CustomerFactory extends AbstractFactory {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractFactory#getPage(java.lang.String)
	 */
	Abstract getPage(String page) {
		// System.out.println("Inside Customerfactory to check choice:"+page);
		if (page.equalsIgnoreCase("customer")) {
			return new CustomerPage();
		}
		return null;
		// TODO Auto-generated method stub

	}
}
