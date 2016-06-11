package bms;



public class FactoryProducer
{
	public static AbstractFactory getFactory(String choice)
	{
	       if(choice.equalsIgnoreCase("STAFF"))
	       { 
	    	   return new StaffFactory();
	       } 
	       
           return null;
   }
}
