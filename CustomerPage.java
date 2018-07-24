// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

public class CustomerPage implements Abstract {

	CustomerView custview = new CustomerView();

	@Override
	/*
	 * void send method
	 * 
	 * @ param a
	 */
	public void send(String a) throws Exception {
		// System.out.println("Inside Customer page to check choice:"+a);
		// TODO Auto-generated method stub
		if (a.equalsIgnoreCase("customer")) {
			custview.showView();
		} else {
			System.out.print("Wrong input entered");
		}

	}

}
