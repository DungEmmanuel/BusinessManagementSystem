package bms;

public class TempContainer 
{
	 int productID;
	 int productAmount;
	 int productQuantity;
	 String productTransid;
	 
     public TempContainer(int prodid,int amount, int quantity, String transid)
     {
    	 productID=prodid;
    	 productAmount=amount;
    	 productQuantity=quantity;
    	 productTransid=transid;
     }
     
     public int getProductid()
     {
    	 return productID;
     }
     
     public int getAmount()
     {
    	 return productAmount;
     }
     
     public int getQuantity()
     {
    	 return productQuantity;
     }
     
     public String getTransactionID()
     {
    	 return productTransid;
     }
}

