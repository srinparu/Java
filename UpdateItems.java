// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri
/*
 * Command Pattern implementing execute
 */
public class UpdateItems implements Execute {

	private MarketRMI newRMI;
	public String opt;
	private int login;
	MarketView view = new MarketView();

	/*
	 * Constructor
	 */
	UpdateItems(MarketRMI RMI) {
		newRMI = RMI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Execute#execute() method implementing updateItems
	 */
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {

			session = newRMI.process(view.output);
			view.updateItems();
			String d;
			if (view.updateOption.equalsIgnoreCase("yes") || view.updateOption.equalsIgnoreCase("y")) {
				if (view.output.equalsIgnoreCase("admin")) {

					d = newRMI.updateItems(session, view.updateItem, view.update_description, view.updateItemQuantity,
							view.price);
					// System.out.println(view.updateItem+view.updateItemQuantity);
					System.out.println(d);

				} else if (view.output.equalsIgnoreCase("customer")) {

					System.out.println("Restricted!!!! Customer cannot update items");
					System.out.println(newRMI.updateItems(session, view.updateItem, view.description,
							view.updateItemQuantity, view.price));

				}
			} else {
				System.out.println("No item updated");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
