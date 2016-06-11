package bms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class SalesRep extends JFrame implements Staff, ActionListener
{
	   ResultSet result=null;
	   JPanel customerPan, customerCartPan, searchPan, scrollerPan, logoutPan;
	   JTabbedPane thePane;
	   JButton customerButt, searchButt, customerCartButt1, customerCartButt2, printCartButt, logoutButt;
	   GridBagConstraints constraint1;
	   JLabel customerName, customerMobile, customerAddrs, cartItemName, cartItemPrice, cartItemQty, searchItem, stores;
	   JTextField customerNameField, customerMobileField, customerAddrsField, cartNameField, cartPriceField, cartQtyField, searchField;
	   JTable itemsCart;
	   JScrollPane scroller;
	   Color bgcolour=new Color(0.1f, 0.7f, 0.9f);
	   JComboBox chooseStore;
	   
	   //Object[] heading1={"TransactionID", val, ""};
	   Object[] heading={"ITEM","PRICE","QTY"};
	   DefaultTableModel model;
	   JTextArea searchResult;
	   JProgressBar searchProgress;
	   
	   long transactionID;
	   static int checker;
	   LinkedList <TempContainer> bank=new LinkedList<TempContainer>();
	   static String val="";
	   String[] storeList=new String[] {"GetAllStore", "TheCenterStore", "WestSideMallStore"};
	   String pseudo, thebranchName;
	   int branchID;
	   
	   
	   SalesRep(String theBranchName)
	   { 
		   setTitle("DOASCO NIGERIA ENTERPRISE: SalesRepresentative's Window | " + " Branch :" + theBranchName.toUpperCase() );
		   setBounds(200,100,1000,600);
		   setBackground(Color.WHITE);
		   setLayout(new BorderLayout());
		   customerPan=new JPanel();
		   customerPan.setBackground(bgcolour);
		   customerButt=new JButton("Submit");
		   
		   logoutButt=new JButton("Logout");
		   logoutPan=new JPanel();
		   logoutPan.setBackground(bgcolour);
		   logoutPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		   
		   customerNameField=new JTextField(20);
		   customerMobileField=new JTextField(20);
		   customerAddrsField=new JTextField(20);
		
		   customerName=new JLabel("CustomeName");
		   customerMobile=new JLabel("CustomerMobile");
		   customerAddrs=new JLabel("CustomerAddress");
		   
		   val = ""+ createTransactionId();
		   itemsCart=new JTable();
		   itemsCart.setPreferredSize(new Dimension(800,250));
		   model=(DefaultTableModel)itemsCart.getModel();
		   model.addColumn("ITEM");
		   //model.addColumn("PRICE");
		   model.addColumn("QUANTITY");
		   model.addColumn("AMOUNT");
		   model.addColumn("PAYMENT STATUS");
		   model.addColumn("TransactionID=");
		   model.addColumn(val);
		  
		   //new DefaultTableModel(heading,0);
		   thePane=new JTabbedPane();
		   

		   scroller=new JScrollPane(itemsCart, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   scroller.setPreferredSize(new Dimension(900, 300));
		   //scroller.add();
		   //itemsCart.setPreferredScrollableViewportSize( new Dimension(900, 300));
		   
		   scroller.revalidate();
		   scroller.setVisible(true);
		   
		   
		   cartItemName=new JLabel("Item Name");
		   cartItemPrice=new JLabel("Item Price");
		   customerCartButt1=new JButton("Add Item");
		   customerCartButt2=new JButton("Submit");
		   printCartButt=new JButton("Print Cart");
		   cartNameField=new JTextField(10);
		   cartPriceField=new JTextField(10);
		   cartQtyField=new JTextField(10);
		   cartItemQty=new JLabel("Quantity");
		   customerCartPan=new JPanel();
		   customerCartPan.setBackground(bgcolour);
		   
		   customerPan.setLayout(new GridBagLayout());
		   constraint1=new GridBagConstraints();
		   
		   customerCartPan.setLayout(new GridBagLayout());
		   
		   stores=new JLabel("Choose Store");
		   chooseStore=new JComboBox(storeList);
		   
		   // initializing components on the searchPanel Tab.
		   
		   searchPan=new JPanel();
		   searchButt=new JButton("Search");
		   searchField=new JTextField(10);
		   searchResult=new JTextArea(2,1);
		   
		   searchItem=new JLabel("Search for Item");
		   searchPan.setBackground(bgcolour);
		   searchPan.setLayout(new GridBagLayout());
		   
		   customerCartButt1.addActionListener(this);
		   customerCartButt2.addActionListener(this);
		   printCartButt.addActionListener(this);
		   customerButt.addActionListener(this);
		   searchButt.addActionListener(this);
		   logoutButt.addActionListener(this);
		   chooseStore.addActionListener(this);
		   
		   /* setting constraint for customer Registration 
		    * Pane.
		    */
		   // setting  contraint1 for label customerName
		   constraint1.gridx=1;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   customerPan.add(customerName,constraint1);
		   
		   // setting contraint1 for jtextfield customerNameField
		   
		   constraint1.gridx=3;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=2;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.HORIZONTAL;
		   customerPan.add(customerNameField,constraint1);
		   
		// setting  contraint1 for label customerMobile
		   constraint1.gridx=1;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   customerPan.add(customerMobile,constraint1);
		   
		   
   // setting contraint1 for jtextfield customerMobileField
		   
		   constraint1.gridx=3;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=2;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.HORIZONTAL;
		   customerPan.add(customerMobileField,constraint1);
		   
		   
		// setting  contraint1 for label customerAddrs
		   constraint1.gridx=1;
		   constraint1.gridy=5;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   customerPan.add(customerAddrs,constraint1);
		   
    // setting contraint1 for jtextfield customerAddrsField
		   
		   constraint1.gridx=3;
		   constraint1.gridy=5;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=2;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.HORIZONTAL;
		   customerPan.add(customerAddrsField,constraint1);
		   
		   
		   // setting  contraint1 for button customerButts
		   constraint1.gridx=3;
		   constraint1.gridy=7;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   customerPan.add(customerButt,constraint1);
		   
		   customerPan.setVisible(true);
		   
		   
		   thePane.add("RegisterCustomer",customerPan);
		   customerPan.revalidate();
		   
		   /*setting constraint for the Customer 
		    * Item Cart Pane.
		    */
		   
		    
		    // setting constraint for scroller.
		    
		    constraint1.gridx=1;
		    constraint1.gridy=1;
		    constraint1.gridwidth = GridBagConstraints.REMAINDER; 
		    constraint1.gridheight = 1;
		    constraint1.weightx= 1.0;
		    constraint1.weighty= 1.0;
		    constraint1.fill= GridBagConstraints.BOTH;
		    constraint1.insets= new Insets(2,2,2,2); //t,l,b,r
		    customerCartPan.add(scroller, constraint1);
		    //scrollerPan.revalidate();
		    
		    // setting constraint for itemName Label
		    constraint1.gridx=1;
		    constraint1.gridy=3;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    constraint1.fill=GridBagConstraints.HORIZONTAL;
		    customerCartPan.add(cartItemName, constraint1);
		    
		    // setting constraint for cartNameField 
		    constraint1.gridx=2;
		    constraint1.gridy=3;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    constraint1.fill=GridBagConstraints.HORIZONTAL;
		    customerCartPan.add(cartNameField, constraint1);
		    
		   
		    
		    
		    // setting constraint for cartItemQty Label
		    constraint1.gridx=4;
		    constraint1.gridy=3;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    
		    customerCartPan.add(cartItemQty, constraint1);
		    
		    // setting constraint for cartQtyField
		    constraint1.gridx=5;
		    constraint1.gridy=3;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    constraint1.insets= new Insets(0,0,0,20); //t,l,b,r
		    customerCartPan.add(cartQtyField, constraint1);
		    
		    
		    // setting constraint for customerCartButt1 
		    constraint1.gridx=4;
		    constraint1.gridy=4;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    customerCartPan.add(customerCartButt1, constraint1);
		    
		 // setting constraint for printCartButt 
		    constraint1.gridx=5;
		    constraint1.gridy=4;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    customerCartPan.add(printCartButt, constraint1);
		    
		    // setting constraint for customerCartButt2
		    checker=0;
		    constraint1.gridx=6;
		    constraint1.gridy=4;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    customerCartPan.add(customerCartButt2, constraint1);
		    
		    thePane.add("CreateCustomerCart", customerCartPan);
		    
		    customerCartPan.revalidate();
		    
		    
		    /* setting constraint for Search Item  
			    * Pane.
			    */
           // setting constraint for Store Label
		    
		    constraint1.gridx=1;
		    constraint1.gridy=1;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(stores, constraint1);
		    
        // setting constraint for chooseStore JCombox
		    
		    constraint1.gridx=2;
		    constraint1.gridy=1;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(chooseStore, constraint1);
		    
		    // setting constraint for searchItem Label
		    
		    constraint1.gridx=3;
		    constraint1.gridy=1;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(searchItem, constraint1);
		    
		    // setting constraint for searchField
		    
		    constraint1.gridx=4;
		    constraint1.gridy=1;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(searchField, constraint1);
		    
		    // setting constraint for searchButt
		    
		    constraint1.gridx=5;
		    constraint1.gridy=1;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(searchButt, constraint1);
		    
		    // setting constraint for searchResult Area
		    
		    constraint1.gridx=1;
		    constraint1.gridy=3;
		    constraint1.gridwidth=GridBagConstraints.REMAINDER;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(searchResult, constraint1);
		    
		    // setting constraint for searchProgress Bar
		    searchProgress=new JProgressBar(0,100);
		    searchProgress.setStringPainted(true);
		    searchProgress.setValue(0);
		    constraint1.gridx=1;
		    constraint1.gridy=5;
		    constraint1.gridwidth=GridBagConstraints.REMAINDER;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    searchPan.add(searchProgress, constraint1);
		    
		    thePane.add("Search for Item", searchPan);
		    searchPan.setVisible(true);
		    searchPan.validate();;
		    
		   logoutPan.add(logoutButt);
		   add(logoutPan, BorderLayout.NORTH);
		   
		   thePane.setVisible(true);
		   add(thePane, BorderLayout.CENTER);
		   thePane.validate();
		   
		   
		   setVisible(true);
		   pseudo=theBranchName+"store";
		   thebranchName=theBranchName;
	   }
	   
	   
	 // method to create transactionID.
	 public long createTransactionId()
	 {
		 int randomPIN = (int)(Math.random()*9000)+1000;
		 return randomPIN;
	 }
	 
	 


	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getSource()==customerCartButt1)
		{
			int productID, productPrice, Amount, qty;
			productPrice=1;
			int exist=0;
		    //TempContainer store;
		
			String item=cartNameField.getText();
			//String price=cartPriceField.getText();
			String quantity=cartQtyField.getText();
			
			//get item id from product table and compute Amount
			DBConnection konnect=new DBConnection();  // create a connection to the DB.
			
			String myQuery="SELECT * FROM " + pseudo + ";";   // query String.
			result=konnect.retrieve(myQuery);
			try 
			{
				while(result.next())               // loop through the returned result.
				{
					
					String itemname=result.getString("product_name");
					
					if((item.equalsIgnoreCase(itemname)))
					{
						    productID=result.getInt("product_id");
						    productPrice=result.getInt("selling_price");
						 
						    qty=Integer.parseInt(quantity);
							Amount=productPrice * qty;    // Compute Amount.
							Object[] rows={item, quantity, Amount, "",""};
							//model=(DefaultTableModel)itemsCart.getModel();
							model.addRow(rows);
							
							cartNameField.setText("");
							//cartPriceField.setText("");
							cartQtyField.setText("");
							
							
							checker=1;
							exist=1;
						    TempContainer	store=new TempContainer(productID, Amount, qty, val);
							bank.add(store);
						    
						 break;
					}
			
				}
		     }
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			konnect.closeConnection();    // close database connection.
			
			if(exist==0)
			{
				JOptionPane.showMessageDialog(this,item.toUpperCase() + " Does not exist in store","Attention!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		// TODO Auto-generated method stub
		if(event.getSource()==printCartButt)
		{
			JOptionPane.showMessageDialog(this,"Please Ensure There Are Items on the Cart","Attention!", JOptionPane.WARNING_MESSAGE);
			try
			{
				itemsCart.print();
			} 
			catch (PrinterException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(event.getSource()==searchButt)
		{
			//JOptionPane.showMessageDialog(this,"Please Ensure There Are Items on the Cart","Attention!", JOptionPane.WARNING_MESSAGE);
			String currentStore=(String)chooseStore.getSelectedItem();
			if(searchField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Please Ensure The Search TextField is not Empty","Attention!", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String productname;
				int unitStock, count=0 ;
				String collectedValue=searchField.getText();
				DBConnection konnect=new DBConnection();  // create a connection to the DB.
				
				String myQuery="SELECT * FROM " + currentStore + ";";   // query String.
				result=konnect.retrieve(myQuery);
				try 
				{
					while(result.next())               // loop through the returned result.
					{
						productname=result.getString("product_name");
						unitStock=result.getInt("unit_in_stock");
						//role=result.getString("designation");
						
						if((collectedValue.equalsIgnoreCase(productname)) && (unitStock >= 1))
						{
							searchResult.setText("");
							searchResult.setText(collectedValue.toUpperCase() + " IS AVALIABLE " + ":Unit_In_Stock = " + unitStock);
							count++;
						}
				
					}
			     }
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				konnect.closeConnection();
				
				if(count==0)
				{
					searchResult.setText("");
					searchResult.setText(collectedValue.toUpperCase() + " IS NOT AVALIABLE IN "+ currentStore.toUpperCase());
				}
				searchField.setText("");
		     }
			
			
			
	     }
		if(event.getSource()==customerButt)
		{
			//System.out.print("customerButt is clicked");
			if(customerNameField.getText().equalsIgnoreCase("") || customerMobileField.getText().equalsIgnoreCase("") || customerAddrsField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Please Ensure That All The TextFields Are Filled Properly","Attention!", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				
				DBConnection connect=new DBConnection();  // create a connection to the DB.
				String Query="SELECT branch_id FROM branch WHERE branch_name='" + thebranchName + "';";  // query String.
				result=connect.retrieve(Query);
				try 
				{
					if(result.next())
					{
						branchID=result.getInt("branch_id");
					}
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connect.closeConnection();
				
				java.util.Date utilDate = new java.util.Date();
		        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				String customername=customerNameField.getText();
				String customermobile=customerMobileField.getText();
				String customeraddr=customerAddrsField.getText();
				
                DBConnection konnect=new DBConnection();  // create a connection to the DB.
				String myQuery="INSERT INTO customer (customer_mobile, customer_name, customer_address, branch_id, date) VALUES('" + customermobile +"','" + customername + "','" + customeraddr + "'," + branchID + ",'" + sqlDate + "');";   // query String.
				konnect.upDate(myQuery);
				
				customerNameField.setText("");
				customerMobileField.setText("");
				customerAddrsField.setText("");
				konnect.closeConnection();
			}
			
			
		}
		
		if(event.getSource()==customerCartButt2)
		{
			//System.out.print("customerCartButt has been clicked");
			if(checker==0)
			{
				JOptionPane.showMessageDialog(this,"Please Ensure That Items Have Been Added To The Table-Cart","Attention!", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				DBConnection connect=new DBConnection();  // create a connection to the DB.
				String Query="SELECT branch_id FROM branch WHERE branch_name='" + thebranchName + "';";  // query String.
				result=connect.retrieve(Query);
				try 
				{
					if(result.next())
					{
						branchID=result.getInt("branch_id");
					}
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connect.closeConnection();
				
				    java.util.Date utilDate = new java.util.Date();
			        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				    DBConnection konnect=new DBConnection();  // create a connection to the DB.
				    String query="INSERT INTO transactions (transaction_id, date, branch_id) VALUES('" + val + "','" + sqlDate + "'," + branchID + ");";   // query String.
				    konnect.upDate(query);
				    
				    TempContainer placeHolder;
				    int size=bank.size();
				    for(int i=0; i<size; i++)
				    {
				    	placeHolder=bank.get(i);
				    	int prodID=placeHolder.getProductid();
				    	int prodquan=placeHolder.getQuantity();
				    	int prodamount=placeHolder.getAmount();
				    	String prodtrans=placeHolder.getTransactionID();
				    	String myQuery="INSERT INTO orders (product_id, quantity, amount, transaction_id, branch_id) VALUES(" + prodID +"," + prodquan + "," + prodamount + ",'" + prodtrans + "'," + branchID + ");";   // query String.
						konnect.upDate(myQuery);
				    }
					konnect.closeConnection();
					JOptionPane.showMessageDialog(this,"Orders Sucessfully Added to the DataBase","Attention!", JOptionPane.WARNING_MESSAGE);
					
			}
			
			
		}
		
		if(event.getSource()==logoutButt)
		{
			JOptionPane.showMessageDialog(this,"Are You Sure You Want To Logout?","Attention!", JOptionPane.WARNING_MESSAGE);
			this.dispose();
			new Login();
		}
  }
	
	@SuppressWarnings("unused")
	private static class MySwingWorker extends SwingWorker<String, Double>
	{
	    private final JProgressBar fProgressBar;
	    private final JLabel fLabel;
	    private MySwingWorker( JProgressBar aProgressBar, JLabel aLabel ) 
	    {
	      fProgressBar = aProgressBar;
	      fLabel = aLabel;
	    }

	    @Override
	    protected String doInBackground() throws Exception 
	    {
	      int maxNumber = 10;
	      for( int i = 0; i < maxNumber; i++ )
	      {
	        Thread.sleep( 2000 );//simulate long running process
	        double factor = ((double)(i+1) / maxNumber);
	        System.out.println("Intermediate results ready");
	        publish( factor );//publish the progress
	      }
	      return "Finished";
	    }

	    @Override
	    protected void process( List<Double> aDoubles ) 
	    {
	      //update the percentage of the progress bar that is done
	      int amount = fProgressBar.getMaximum() - fProgressBar.getMinimum();
	      fProgressBar.setValue( ( int ) (fProgressBar.getMinimum() + ( amount * aDoubles.get( aDoubles.size() - 1 ))) );
	    }

	    @Override
	    protected void done() 
	    {
	      try 
	      {
	        fLabel.setText( get() );
	      } 
	      catch ( InterruptedException e ) 
	      {
	        e.printStackTrace();
	      } 
	      catch ( ExecutionException e ) 
	      {
	        e.printStackTrace();
	      }
	    }
	  }

	@Override
	public void authentifyStaff() 
	{
		// TODO Auto-generated method stub
		
	}

}