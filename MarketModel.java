
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MarketModel extends UnicastRemoteObject implements MarketRMI {

	private int flag1 = 1, flag2 = 0, flag3 = 1, check = 0;
	public String name, pwd, description, category, userName, user;
	public int stock;
	public static ArrayList<String> list = new ArrayList<String>();
	public static int p_quantity;
	public int price;
	public String regName = null, regPwd = null, itemName;
	public MarketModel model;
	public Connection conn = null;
	public Statement stmt = null;
	public Statement stmt1 = null;
	public Statement stmt2 = null;
	Singleton jdbc = Singleton.getInstance();

	protected MarketModel() throws RemoteException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		super();

	}

	/* User Registration for new user */
	@Override
	public synchronized int register(Session session, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		check = 1;
		regName = name;
		regPwd = pwd;
		try {
			int reg = jdbc.register(session, name, pwd);
			if (reg == 1) {
				System.out.println("User Registered Successfully");

			} else if (reg == 2) {
				System.out.println("Username already exists!! Try with other name");

			} else if (reg == 0) {
				System.out.println("Sorry cannot register");

			}
			return reg;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	/* User Login */
	@Override
	public synchronized int login(String user, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		flag2 = 0;
		this.name = name;
		this.pwd = pwd;
		this.user = user;
		try {
			int loginValue = jdbc.login(user, name, pwd);
			if (loginValue == 1) {
				System.out.println("Admin login successful");
				// return loginValue;
			} else if (loginValue == 2) {
				System.out.println("Customer login successful");
				// return loginValue;
			} else if (loginValue == 0) {
				System.out.println("Invalid credentials");
			}
			return loginValue;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	/* Browsing items */
	@Override
	public synchronized ArrayList<String> browse() throws RemoteException {
		ArrayList<String> browseList = new ArrayList<String>();
		try {
			browseList = jdbc.browse();
			System.out.println(browseList);
			return browseList;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	// TODO Auto-generated method stub

	@Override
	public Session process(String userType) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = new Session(userType);
		return session;
	}

	// add ietms
	@Override
	public synchronized String addItems(Session session, String category, String name, String description, int price,
			int stock) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.category = category;

		// System.out.println("Inside server addItems method, stocks is "+stock);

		this.description = description;
		try {
			String addItems = jdbc.addItems(session, category, name, description, price, stock);
			System.out.println(addItems);
			return addItems;
		} catch (Exception e) {
			e.getMessage();
			return "";
		}
	}

	// purchase items
	@Override
	public synchronized String addToCart(Session session, String userName, String name, int stock)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.userName = userName;
		this.name = name;
		this.stock = stock;
		try {
			String addToCart = jdbc.addToCart(session, userName, name, stock);
			System.out.println(addToCart);
			return addToCart;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// remove items
	@Override
	public synchronized String removeItems(Session session, String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		try {
			String removeItems = jdbc.removeItems(session, name);
			System.out.println(removeItems);
			return removeItems;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// upadet items
	@Override
	public synchronized String updateItems(Session session, String name, String description, int price, int stock)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = description;
		try {
			String updateItems = jdbc.updateItems(session, name, description, price, stock);
			System.out.println(updateItems);
			return updateItems;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// add other admins
	@Override
	public synchronized int addAdmin(Session session, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.pwd = pwd;
		try {
			int addAdmin = jdbc.addAdmin(session, name, pwd);
			if (addAdmin == 1) {
				System.out.println("Admin " + name + " added to DB");
				return addAdmin;
			} else {
				System.out.println("Sorry cannot add " + name + " to the DB");
				return addAdmin;
			}
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}

	// remove customers
	@Override
	public synchronized String removeUsers(Session session, String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		try {
			String removeUser = jdbc.removeUsers(session, name);
			System.out.println(removeUser);
			return removeUser;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
