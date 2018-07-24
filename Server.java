
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String args[]) {
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		// For remote exceptions try and catch
		try {
			// Locate Market Server
			String name = "//in-csci-rrpc01.cs.iupui.edu:2018/Server";

			System.out.println("MarketServer: Binding to " + name);
			MarketRMI server = (MarketRMI) Proxy.newProxyInstance(MarketRMI.class.getClassLoader(),
					new Class<?>[] { MarketRMI.class }, new AuthorizationHandler(new MarketModel()));
			// Binding of the new server instance
			Naming.rebind(name, server);
			System.out.println("Server Created !!");
		} catch (Exception e) {
			System.out.println("Server error: " + e.getMessage());
		}
	}

}
