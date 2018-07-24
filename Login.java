// Honor Pledge:
// I pledge that I have neither given nor received any help on this assignment.
// Sri Navya Paruchuri

import java.rmi.RemoteException;
public class Login implements Execute{
	private MarketRMI newRMI;
	public String opt;
	public int d;
	MarketView view=new MarketView();
	Session session;
	Login(MarketRMI RMI)
	{
		newRMI=RMI;
	}
	
	@Override
	
	//Implementing login functionality 
	public void execute() {
		// TODO Auto-generated method stub
		
		try
		{
			// Assigning value to session variable
			session = newRMI.process(view.output);
			 // System.out.println(session+view.output);
			   if(view.output.equalsIgnoreCase("customer"))
			   {
				view.login();
				 if (view.option1.equalsIgnoreCase("y")&& (!(view.option1.equalsIgnoreCase("n"))))
				   {
					// System.out.println(view.name1+view.pwd1+view.name1+view.pwd1);
					 d=newRMI.login(view.output, view.name1, view.pwd1);
				   }
				 else if((view.option1.equalsIgnoreCase("t")))
					{
				
				d=newRMI.login(view.output, view.name1, view.pwd1);
				 }
			   }
			   else if(view.output.equalsIgnoreCase("admin"))
			   {
				   d=0;
				   view.adminlogin();
				   d=newRMI.login(view.output, view.nameadmin, view.pwdadmin);
			   }
				if(d==2)
				{
					System.out.println("User Login Successful");
				}
				else if(d==1)
				{
					System.out.println("Admin Login Successful");
				}
				
				else if(d==0)
				{
					System.out.println("Sorry cannot login!! Please try again");
					System.exit(0);
				}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		opt = view.output;
		//System.out.println("Inside c4printing the opt: "+opt);
	}
}
