// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

public class RemoveItems implements Execute {

	private MarketRMI newRMI;
	public String opt;
	private int login;
	MarketView view = new MarketView();

	RemoveItems(MarketRMI RMI) {
		newRMI = RMI;
	}

	// Implementing remove items functionality
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {

			// Assigning value to session variable
			session = newRMI.process(view.output);
			view.removeItems();
			String d;
			if (view.removeOption.equalsIgnoreCase("yes") || view.removeOption.equalsIgnoreCase("y")) {
				if (view.output.equalsIgnoreCase("admin")) {

					d = newRMI.removeItems(session, view.removeItem);

					System.out.println(d);

				} else if (view.output.equalsIgnoreCase("customer")) {

					System.out.println("Restricted!!!! Customer cannot remove items");
					System.out.println(newRMI.removeItems(session, view.removeItem));

				}
			} else {
				System.out.println("No item removed from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
