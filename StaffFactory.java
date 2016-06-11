package bms;



public class StaffFactory extends AbstractFactory
{
	
	@Override
	
	Staff getStaff(String staff, String name) 
	{ 
	      if(staff == null)
	      {
	         return null;
	      } 
	      if(staff.equalsIgnoreCase("SALESREP"))
	      {
	    	  
	            return new SalesRep(name);
	      } 
	      else if(staff.equalsIgnoreCase("ACCOUNTANT"))
	      {
	           return new Accountant(name);
	      } 
	      else if(staff.equalsIgnoreCase("STOREKEEPER"))
	      {
	         return new StoreKeeper(name);
	      }
	      
	      else if(staff.equalsIgnoreCase("ADMINISTRATOR"))
	      {
	         return new Administrator(name);
	      }
	      return null;
  }
}
