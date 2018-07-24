// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

public class AdminPage implements Abstract {
	AdminView adminview = new AdminView();

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see Abstract#send(java.lang.String)
	 */
	public void send(String a) throws Exception {
		// TODO Auto-generated method stub

		if (a.equalsIgnoreCase("admin")) {
			adminview.showView();
		} else {
			System.out.print("Wrong input entered");
		}
	}

}
