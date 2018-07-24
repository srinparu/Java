
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MarketRMI extends Remote {
	public int login(String user, String name, String pwd) throws RemoteException;

	public ArrayList<String> browse() throws RemoteException;

	@RequiresRole("admin")
	public String addItems(Session session, String category, String name, String description, int price, int stock)
			throws RemoteException;

	@RequiresRole("admin")
	public String removeUsers(Session session, String name) throws RemoteException;

	@RequiresRole("admin")
	public int addAdmin(Session session, String name, String pwd) throws RemoteException;

	@RequiresRole("admin")
	public String removeItems(Session session, String name) throws RemoteException;

	@RequiresRole("admin")
	public String updateItems(Session session, String name, String description, int qunatity, int price)
			throws RemoteException;

	@RequiresRole("customer")
	public String addToCart(Session session, String userName, String name, int stock) throws RemoteException;

	@RequiresRole("customer")
	public int register(Session session, String name, String pwd) throws RemoteException;

	public Session process(String userType) throws RemoteException;
}
