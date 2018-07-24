
// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.util.Scanner;

/*
 * param @output, @ans
 */
public class MarketView {
	public static String output, option1, name, pwd, name1, pwd1, nameadmin, pwdadmin;
	public static int check;
	public String ans = "", output1;
	public String itemName, name2, pwd2;
	Scanner sc = new Scanner(System.in).useDelimiter("\\n");
	public String addItem, removeItem, updateItem, category, description, update_description, removeOption,
			addItemOption, addOption, purchaseOption, updateOption, removeUserOption;
	public int addItemQuantity, stock, removeItemQuantity, updateItemQuantity, addQuantity, addItemPrice, price,
			purchased_stock;
	public MarketView view;
	public String purchase, purchasedItem, addoption;
	public String removeUser;

	void functions() {
		/*
		 * taking input whether customer or admin
		 */
		System.out.println("Customer or Admin");
		output = sc.next().toLowerCase();
		output1 = output;
		if (output.equalsIgnoreCase("Admin") || output.equalsIgnoreCase("customer")) {
			System.out.println("Welcome to the Online Market Place " + output);
		} else {
			System.out.println("Invalid entry!! Please try again");
			System.exit(0);
		}
	}

	/* User Registration */
	void register() {

		System.out.println("New Customer !! Do you want to register?");
		String option = sc.next();
		// Register for new customer
		if (option.equalsIgnoreCase("yes") || option.equalsIgnoreCase("y")) {
			System.out.println("Welcome to Registration Page");
			System.out.println("Enter Username");
			name = sc.next();
			System.out.println("Enter Password");
			pwd = sc.next();
			option1 = "t";
		}
		// Check if customer registered previously
		else if (option.equalsIgnoreCase("no") || option.equalsIgnoreCase("n")) {
			System.out.println("Are you a registered customer?");
			option1 = sc.next();
			if (option1.equalsIgnoreCase("yes") || option1.equalsIgnoreCase("y")) {
				System.out.println("Please login to continue..");

			} else if (option1.equalsIgnoreCase("no") || option1.equalsIgnoreCase("n")) {
				System.out.println("Please Register to continue..");
				System.exit(0);
			} else {
				System.out.println("Please enter valid option");
				System.exit(0);
			}

		} else {
			System.out.println("Please enter valid option");
			System.exit(0);
		}

	}

	/* Login */
	// customer login
	void login() {
		System.out.println("Welcome to Login Page");
		if (!(option1.equalsIgnoreCase("yes") || option1.equalsIgnoreCase("y"))) {
			option1 = "t";
		}
		System.out.println("Enter the Username");
		name1 = sc.next();
		System.out.println("Enter Password");
		pwd1 = sc.next();

	}

	// admin login
	void adminlogin() {
		System.out.println("Welcome to Login Page");
		System.out.println("Enter the Username");
		nameadmin = sc.next();
		System.out.println("Enter Password");
		pwdadmin = sc.next();
	}

	/* Browse all the available items */
	void browse() {
		System.out.println("Welcome to Browsing Page");
	}

	/* Add items */
	void addItems() {
		System.out.println("Welcome to add items Page");
		System.out.println("Do you want to add items??");
		addItemOption = sc.next();
		if (addItemOption.equalsIgnoreCase("yes") || addItemOption.equalsIgnoreCase("y")) {
			// Give item details
			System.out.println("Enter the item category to be added");
			category = sc.next();
			System.out.println("Enter the item name to be added");
			addItem = sc.next();
			System.out.println("Enter the description of the new item");
			description = sc.next();
			System.out.println("Enter price of the new item");
			addItemPrice = sc.nextInt();
			System.out.println("Enter the quantity of new item");
			addQuantity = sc.nextInt();
		} else if (addItemOption.equalsIgnoreCase("no") || addItemOption.equalsIgnoreCase("n")) {
			System.out.println("Leaving add items page!!");
		} else {
			System.out.println("Invalid Input");
			System.exit(0);
		}

	}

	/* Purchase items */
	void addToCart() {
		System.out.println("Welcome to Purchase Page");
		System.out.println("Do you want to purchase items??");
		purchaseOption = sc.next();
		if (purchaseOption.equalsIgnoreCase("yes") || purchaseOption.equalsIgnoreCase("y")) {
			// Item name and quantity to search
			System.out.println("Enter the name of item you want to purchase");
			purchasedItem = sc.next();
			System.out.println("Enter the stock of item you want to purchase");
			purchased_stock = sc.nextInt();
		} else if (purchaseOption.equalsIgnoreCase("n") || purchaseOption.equalsIgnoreCase("no")) {
			System.out.println("Leaving purchase items page!!");
		} else {
			System.out.println("Invalid Input");
			System.exit(0);
		}

	}

	/* Remove items */
	void removeItems() {
		System.out.println("Welcome to remove items Page");
		System.out.println("Do you want to remove items??");
		removeOption = sc.next();
		if (removeOption.equalsIgnoreCase("yes") || removeOption.equalsIgnoreCase("y")) {
			// Give item name to remove
			System.out.println("Enter the item name to be removed");
			removeItem = sc.next();
		} else if (removeOption.equalsIgnoreCase("no") || removeOption.equalsIgnoreCase("n")) {
			System.out.println("Leaving remove items page!!");
		} else {
			System.out.println("Invalid Input");
			System.exit(0);
		}
	}

	/* Update items */
	void updateItems() {
		System.out.println("Welcome to update items Page");
		System.out.println("Do you want to update items??");
		updateOption = sc.next();
		if (updateOption.equalsIgnoreCase("yes") || updateOption.equalsIgnoreCase("y")) {
			// Give item name to update
			System.out.println("Enter the item name to be updated");
			updateItem = sc.next();
			// Give updated item details
			System.out.println("Enter new description to be updated");
			update_description = sc.next();
			System.out.println("Enter new price for the updated item");

			updateItemQuantity = sc.nextInt();
			System.out.println("Enter new quantity to be updated");
			price = sc.nextInt();
		} else if (updateOption.equalsIgnoreCase("no") || updateOption.equalsIgnoreCase("n")) {
			System.out.println("Leaving update items page!!");
		} else {
			System.out.println("Invalid Input");
			System.exit(0);
		}
	}

	/* Add admins - where an admin can add other admins */
	void addAdmin() {
		System.out.println("Welcome to add Admin page");
		System.out.println("Enter the username of the new admin");
		name2 = sc.next();
		System.out.println("Enter Password");
		pwd2 = sc.next();
	}

	/* Remove users - where admin can remove customer */
	void removeUsers() {
		System.out.println("Welcome to Remove Users page");
		System.out.println("Do you want to remove users??");
		removeOption = sc.next();
		if (removeOption.equalsIgnoreCase("yes") || removeOption.equalsIgnoreCase("y")) {
			// Give customer name to remove
			System.out.println("Enter the username you want to delete");
			removeUser = sc.next();
		} else if (removeOption.equalsIgnoreCase("no") || removeOption.equalsIgnoreCase("n")) {
			System.out.println("Leaving remove users page!!");
		} else {
			System.out.println("Invalid Input");
			System.exit(0);
		}
	}
}
