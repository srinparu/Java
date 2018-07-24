/*Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment. 
 * Sri Navya Paruchuri
 */

public class Registration implements Execute {

	private MarketRMI newRMI;
	public String opt;
	private int login;
	MarketView view = new MarketView();
	// String output=view.output;
	// String view.output = view.output;

	// Constructor
	Registration(MarketRMI RMI) {
		newRMI = RMI;
	}

	// Implements register functionality
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {
			int d;
			view.functions();
			session = newRMI.process(view.output);
			// Validating admin or customer
			if (view.output.equalsIgnoreCase("customer")) {
				view.register();
				if (view.option1.equalsIgnoreCase("y") && (!(view.option1.equalsIgnoreCase("n")))) {
					System.out.println("Welcome registered Customer");
				} else if (view.option1.equalsIgnoreCase("t")) {

					d = newRMI.register(session, view.name, view.pwd);

					if (d == 1) {
						System.out.println("Registered Successfully");
					} else if (d == 0) {
						System.out.println("Sorry cannot be registered");
					} else if (d == 2) {
						System.out.println("Username already exists, Try again!!!");
						System.exit(0);
					}
				}
			} else if (view.output.equalsIgnoreCase("Admin")) {

				System.out.println("Sorry, Admin cannot register");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
