
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

public class Singleton {
	private static Singleton jdbc;
	private int flag1 = 1, flag2 = 0, flag3 = 1, check = 0;
	public String name, pwd, description, category, userName, user;
	public int stock;
	public static ArrayList<String> list = new ArrayList<String>();
	public static int p_quantity;
	public int price;
	public String regName = null, regPwd = null, itemName;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (jdbc == null) {
			jdbc = new Singleton();
		}
		return jdbc;
	}

	// Database connectivity
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		String hostname = "localhost:3306";
		String dbName = "srinparu_db";
		String url = "jdbc:mysql://" + hostname + "/" + dbName;
		String username = "srinparu";
		String password = "srinparu";
		// System.out.println("Connecting to the database...");
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		// System.out.println("Database connected!");
		return con;
	}

	// Register customer
	public synchronized int register(Session session, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		check = 1;
		regName = name;
		regPwd = pwd;

		final ArrayList<String> list1 = new ArrayList<String>();
		list1.clear();
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {

			conn = this.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery("SELECT * FROM customers");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				final String loginName = rs.getString(1);
				list1.add(loginName);
			}
			// Check if the user already exsists, if not enter into DB
			if (list1.contains(regName)) {
				// System.out.println("Username already exists!! Try with other name");
				flag1 = 2;
			} else {
				stmt.executeUpdate(
						"INSERT INTO customers(name, password) VALUES ('" + regName + "', '" + regPwd + "')");
				// System.out.println("User Registered Successfully");
				flag1 = 1;
			}

		}

		catch (Exception e) {
			// System.out.println("Sorry user cannot be registered");
			e.printStackTrace();
			flag1 = 0;
		}

		return flag1;
	}

	// Admin and Customer Login
	public synchronized int login(String user, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		flag2 = 0;
		this.name = name;
		this.pwd = pwd;
		this.user = user;
		synchronized (this) {

			// admin login
			Connection conn = null;
			Statement stmt = null;
			Statement stmt1 = null;
			Statement stmt2 = null;
			if (user.equalsIgnoreCase("admin")) {
				try {
					conn = this.getConnection();
					stmt = conn.createStatement();
					// retrieve name and password
					stmt.executeQuery("SELECT name, password from admin WHERE name = '" + name + "' and password = '"
							+ pwd + "'");
					ResultSet rs = stmt.getResultSet();
					while (rs.next()) {
						String adminUserName = rs.getString("name");
						String adminPwd = rs.getString("password");
						// check admin credentials
						if (name.equals(adminUserName) && pwd.equals(adminPwd)) {

							flag2 = 1;

						} else {

							flag2 = 0;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// customer login
			else if (user.equalsIgnoreCase("customer")) {
				try {
					conn = this.getConnection();
					stmt = conn.createStatement();
					// retrieve name and password
					stmt.executeQuery("SELECT name, password from customers WHERE name = '" + name
							+ "' and password = '" + pwd + "'");
					ResultSet rs1 = stmt.getResultSet();
					while (rs1.next()) {
						String customerUserName = rs1.getString("name");
						String customerPwd = rs1.getString("password");
						if (name.equals(customerUserName) && pwd.equals(customerPwd)) {
							// check customer credentials

							flag2 = 2;
						} else {

							flag2 = 0;

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag2;
	}

	// Browse Items
	public synchronized ArrayList<String> browse() throws RemoteException {

		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM items WHERE name = 'null'");
			// display all available items
			ResultSet rs = stmt.executeQuery("SELECT category, name, description, price, quantity FROM items");

			list.clear();
			while (rs.next()) {

				String d = rs.getString(1) + "-->" + rs.getString(2) + "-->" + rs.getString(3) + "-->" + rs.getString(4)
						+ "-->" + rs.getString(5) + "\n";
				// System.out.println(d);
				list.add(d);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Add Items
	public synchronized String addItems(Session session, String category, String name, String description, int price,
			int stock) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.category = category;

		// System.out.println("Inside server addItems method, stocks is "+stock);
		final ArrayList<String> list2 = new ArrayList<String>();
		this.description = description;
		// System.out.println("From add items method "+name+" stock is "+stock+" price
		// is "+price+" category is "+category);
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {
			conn = this.getConnection();
			list2.clear();
			stmt = conn.createStatement();
			stmt.executeQuery("SELECT * FROM items");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				final String p_name = rs.getString(2);

				list2.add(p_name);
			}
			if (list2.contains(name)) {
				// System.out.println(list2+" Inside if loop in addItems method "+name+" stock
				// is "+stock);
				stmt.execute("SELECT * from items WHERE name = '" + name + "'");
				ResultSet rs1 = stmt.getResultSet();
				while (rs1.next()) {
					p_quantity = rs1.getInt(5);

				}
				int new_quantity = p_quantity + stock;
				// update item based on item name if same item is added
				stmt.executeUpdate("UPDATE items SET quantity = '" + new_quantity + "' WHERE name = '" + name + "'");
				return "Item " + name + " added to database";

			} else {
				// save added details in DB
				stmt.executeUpdate("INSERT INTO items(category, name, description, price, quantity) VALUES ('"
						+ category + "','" + name + "','" + description + "','" + price + "','" + stock + "')");
				// System.out.println("Item " + name + " added to database");
				return "Item " + name + " added to database";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Item " + name + " cannot be added to database";
		}
	}

	// Purchase Items
	public synchronized String addToCart(Session session, String userName, String name, int stock)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.userName = userName;
		this.name = name;
		this.stock = stock;
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		final ArrayList<String> list5 = new ArrayList<String>();
		try {
			conn = this.getConnection();
			list5.clear();
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt1.executeQuery("Select * FROM items");
			ResultSet rs1 = stmt1.getResultSet();
			while (rs1.next()) {
				String addCartItem = rs1.getString(2);
				list5.add(addCartItem);
			}
			if (list5.contains(name)) {
				stmt.executeQuery("SELECT * FROM items WHERE name = '" + name + "'");
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					String item = rs.getString(2);
					int quantity = rs.getInt(5);
					int price = rs.getInt(4);
					if (quantity >= stock) {
						// Insert purchased items to cart in DB
						stmt1.executeUpdate("INSERT INTO cart (item, price, username, quantity) VALUES ('" + name
								+ "','" + price + "','" + userName + "', '" + stock + "')");
						int quantity1 = quantity - stock;
						// update quantity in DB after purchase
						stmt2.executeUpdate(
								"Update items SET quantity = '" + quantity1 + "' WHERE name = '" + item + "'");
						// System.out.println("Item " + name + " added to Cart");
						return "Item " + name + " Added to Cart";

					} else {
						return "Item out of stock";
					}
				}
			} else {
				return "Item not found in the database";
			}
			return "";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// Remove Items
	public synchronized String removeItems(Session session, String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		final ArrayList<String> list4 = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {
			conn = this.getConnection();
			list4.clear();
			stmt = conn.createStatement();
			stmt.executeQuery("Select * FROM items");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String removeName = rs.getString(2);
				list4.add(removeName);
			}
			if (list4.contains(name)) {
				// remove items based on item name
				stmt.executeUpdate("DELETE FROM items WHERE name = '" + name + "'");
				// System.out.println("The item " + name + " has been removed from inventory");

				return "The item " + name + " has been removed from inventory";
			} else {
				// System.out.println("Item " + name + " is not available in the inventory");
				return "Item " + name + " is not available in the inventory";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// Update Items
	public synchronized String updateItems(Session session, String name, String description, int price, int stock)
			throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = description;
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		final ArrayList<String> list3 = new ArrayList<String>();
		try {
			conn = this.getConnection();
			list3.clear();
			stmt = conn.createStatement();
			stmt.executeQuery("Select * from items");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				final String updateName = rs.getString(2);
				list3.add(updateName);
			}
			if (list3.contains(name)) {
				// update item details
				stmt.executeUpdate("UPDATE items SET name = '" + name + "', description = '" + description
						+ "', price = '" + price + "',  quantity = '" + stock + "' WHERE name = '" + name + "'");
				// System.out.println("The item " + name + " is updated");
				return "Item " + name + " Updated in the Inventory";
			} else {
				return "Item " + name + " not found in the database";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Item " + name + " cannot be updated";
		}
	}

	// Add other Administrators
	public synchronized int addAdmin(Session session, String name, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		this.pwd = pwd;
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			// add new admins into the table
			stmt.executeUpdate("INSERT INTO admin(name, password) VALUES ('" + name + "','" + pwd + "')");
			flag2 = 1;
		} catch (Exception e) {
			e.printStackTrace();
			flag2 = 0;
		}
		return flag2;
	}

	// Remove Customers
	public synchronized String removeUsers(Session session, String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		final ArrayList<String> list5 = new ArrayList<String>();
		try {
			conn = this.getConnection();
			list5.clear();
			stmt = conn.createStatement();
			stmt.executeQuery("SELECT * FROM customers");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String removeName = rs.getString(1);
				list5.add(removeName);
			}
			if (list5.contains(name)) {
				// remove customer
				stmt.executeUpdate("DELETE FROM customers WHERE name = '" + name + "'");
				// System.out.println("User " + name + " has been deleted from the database");
				return "User " + name + " has been deleted from the database";
			} else {
				return "User does not exist in the DB";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "User cannot be deleted";
		}
	}
}
