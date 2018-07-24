
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.Naming;

public class marketController {

	public static void main(String args[]) {
		Session session = null;
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		// For remote exceptions try and catch
		try {
			// Locate the Market Server
			String name = "//in-csci-rrpc01.cs.iupui.edu:2018/Server";
			MarketRMI mRMI = (MarketRMI) Naming.lookup(name);
			Registration command = new Registration(mRMI);
			RemoveItems command1 = new RemoveItems(mRMI);
			Login command4 = new Login(mRMI);
			BrowseItems command5 = new BrowseItems(mRMI);
			AddItems command2 = new AddItems(mRMI);
			PurchaseItems command3 = new PurchaseItems(mRMI);
			UpdateItems command6 = new UpdateItems(mRMI);
			AddAdmins command7 = new AddAdmins(mRMI);
			RemoveCustomers command8 = new RemoveCustomers(mRMI);

			Broker bro = new Broker();
			bro.get(command);
			bro.get(command4);
			bro.get(command7);
			bro.get(command5);
			bro.get(command2);
			bro.get(command1);
			bro.get(command6);
			bro.get(command3);
			bro.get(command8);

			bro.post();

			FrontController frontController = new FrontController();
			// Dispatch request for respective views...
			frontController.dispatchRequest(command4.opt, command4.d);
			AbstractFactory abstrac = Dispatcher.dispatch(command4.opt);
			Abstract abstr = abstrac.getPage(command4.opt);
			abstr.send(command4.opt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
