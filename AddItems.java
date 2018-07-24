
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.RemoteException;

public class AddItems implements Execute {

	private MarketRMI newRMI;
	public String opt;
	private int login;
	MarketView view = new MarketView();

	AddItems(MarketRMI RMI) {
		newRMI = RMI;
	}

	// Implementing add items functionality
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		view.addItems();
		if (view.addItemOption.equalsIgnoreCase("yes") || view.addItemOption.equalsIgnoreCase("y")) {
			try {

				// Assigning value to session variable
				session = newRMI.process(view.output);

				String d;
				if (view.output.equalsIgnoreCase("admin")) {

					d = newRMI.addItems(session, view.category, view.addItem, view.description, view.addItemPrice,
							view.addQuantity);
					System.out.println(d);
				} else if (view.output.equalsIgnoreCase("customer")) {

					System.out.println("Restricted!!!! Customer cannot add items");
					System.out.println(newRMI.addItems(session, view.category, view.addItem, view.description,
							view.addItemPrice, view.addQuantity));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No items add to the DB");
		}
		opt = view.output;
	}
}
