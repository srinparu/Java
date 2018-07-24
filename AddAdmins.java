// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.util.Scanner;
/*
 * Command pattern class implementing execute
 */
public class AddAdmins implements Execute{

	private MarketRMI newRMI;
	public String opt;
	private int login;
	Scanner sc = new Scanner(System.in);
	MarketView view=new MarketView();
	//String output=view.output;
	//String view.output = view.output;
/*
 * 	Constructor
 */
	AddAdmins(MarketRMI RMI)
	{
		newRMI=RMI;
	}
	/*
	 * (non-Javadoc)
	 * @see Execute#execute()
	 * add admin functionality
	 */
	public void execute() {
		// TODO Auto-generated method stub
		Session session;
		try {
			
	      
			session = newRMI.process(view.output);
			
		    int d=0;
			if(view.output.equalsIgnoreCase("admin")) 
			{
				System.out.println("How many admins do you want to add?");
				login=sc.nextInt();
				for(int i=0;i<login;i++) {
					view.addAdmin();
					if(!((view.name2.isEmpty()) && (view.pwd2.isEmpty())))
					{
					d=newRMI.addAdmin(session,view.name2,view.pwd2);
					if(d==1)
					{
						System.out.println("Admin added");
					}
					else
					{
						System.out.println("Failed to add new admin");
					}
					}
					else
					{
						System.out.println("name and pwd are empty");
					}
				}
						
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
	
			