
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.RemoteException;

public class PurchaseItems implements Execute {

	private MarketRMI newRMI;
	private String cart;

	MarketView view = new MarketView();

	PurchaseItems(MarketRMI RMI) {
		newRMI = RMI;
	}

	// Implementing purchase items functionality
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {
			view.addToCart();
			// Assigning value to session variable
			session = newRMI.process(view.output);

			if (view.purchaseOption.equalsIgnoreCase("yes") || view.purchaseOption.equalsIgnoreCase("y")) {
				if (view.output.equalsIgnoreCase("Customer")) {

					cart = newRMI.addToCart(session, view.name1, view.purchasedItem, view.purchased_stock);
					System.out.println(cart);
				} else if (view.output.equalsIgnoreCase("admin")) {
					System.out.println("CANNOT perform this operation !!! Admin cannot purchase items");
					System.out.println(newRMI.addToCart(session, view.name1, view.purchasedItem, view.stock));
				}
			} else {
				System.out.println("No item added to cart");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
