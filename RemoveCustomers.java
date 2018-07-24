// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri
/*
 * class implementing Execute
 */
public class RemoveCustomers implements Execute {

	private MarketRMI newRMI;
	public String opt;
	private int login;
	MarketView view = new MarketView();

	/*
	 * Constructor
	 */
	RemoveCustomers(MarketRMI RMI) {
		newRMI = RMI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Execute#execute() method implementing remove user
	 */
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {

			session = newRMI.process(view.output);
			view.removeUsers();
			String d;
			if (view.removeOption.equalsIgnoreCase("yes") || view.removeOption.equalsIgnoreCase("y")) {
				if (view.output.equalsIgnoreCase("admin")) {

					d = newRMI.removeUsers(session, view.removeUser);
					System.out.println(d);

				} else if (view.output.equalsIgnoreCase("customer")) {

					System.out.println("Restricted!!!! Customer cannot remove users");
					System.out.println(newRMI.removeUsers(session, view.removeUser));
				}
			} else {
				System.out.println("No users removed");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
