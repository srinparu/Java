
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri
import java.util.ArrayList;

/*
 * class commandpattern implementing execute
 */
public class BrowseItems implements Execute {

	private MarketRMI newRMI;

	MarketView view = new MarketView();

	/*
	 * Constructor
	 */
	BrowseItems(MarketRMI RMI) {
		newRMI = RMI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Execute#execute() browse method implementation
	 */
	public void execute() {
		// TODO Auto-generated method stub
		try {
			view.browse();
			ArrayList<String> browseList = new ArrayList<String>();
			browseList = newRMI.browse();
			for (int i = 0; i < browseList.size(); i++) {
				System.out.println(browseList.get(i));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
