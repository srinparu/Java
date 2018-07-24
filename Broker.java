
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.util.ArrayList;
import java.util.List;

/**
 * Invoker Object
 */
public class Broker {
	private List<Execute> orderList = new ArrayList<Execute>();

	/**
	 * Given an order request take the order.
	 * 
	 * @param order
	 */
	public void get(Execute exec) {
		orderList.add(exec);
	}

	/**
	 * Execute each order that has been taken.
	 */
	public void post() {
		for (Execute exec : orderList) {
			exec.execute();
		}

		orderList.clear();
	}
}