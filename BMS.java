package bms;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;




public class BMS 
{
	 public static void main(String[] args)
 	  {
		 
		 try 
		   {
			   SwingUtilities.invokeAndWait(new Runnable () 
			   {
				      public void run() 
				      {
				    	  new Login(); // a method that initializes the Swing components
				      }
				});
		    } 
		    catch (InterruptedException e) 
		    {
			   // TODO Auto-generated catch block
			    e.printStackTrace();
		    }  
		    catch (InvocationTargetException e) 
		    {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
		    }
		 
		 
		 // DBConnection konnect=new DBConnection();
		 
		 
 		
 	  }
}

