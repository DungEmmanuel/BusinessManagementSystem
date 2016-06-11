package bms;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewProfit extends JPanel implements ActionListener
{
	
	   ResultSet result=null, result2=null, result1=null;
	   JButton printButt;
	   GridBagConstraints constraint1;
	   JTable itemsCart;
	   JScrollPane scroller;
	   Color bgcolour=new Color(0.1f, 0.7f, 0.9f); 
	   DefaultTableModel model;
	   String productName="null", paymentStatus;
	   int productAmount, productQuantity, productCostPrice, profit, branchID;
	   int productTotal=0, productTotalProfit=0;
	   
	public ViewProfit()
	{
		   setLayout(new GridBagLayout());
		   
		// intializing components
		   constraint1=new GridBagConstraints();
		   printButt=new JButton("Print");
		   itemsCart=new JTable();
		    
		   
		   itemsCart.setPreferredSize(new Dimension(800,350));
		   model=(DefaultTableModel)itemsCart.getModel();
		   model.addColumn("ITEM");
		   //model.addColumn("PRICE");
		   model.addColumn("QUANTITY");
		   model.addColumn("AMOUNT");
		   model.addColumn("PAYMENT STATUS");
		   model.addColumn("PROFIT");
		   model.addColumn("TOTAL");
		   
		   scroller=new JScrollPane(itemsCart, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   scroller.setPreferredSize(new Dimension(900, 300));
		   scroller.revalidate();
		   scroller.setVisible(true);
		   
		   // adding listener to button
		   printButt.addActionListener(this);
		   
		   
		// setting constraint for scroller.
		    
		    constraint1.gridx=1;
		    constraint1.gridy=2;
		    constraint1.gridwidth=GridBagConstraints.REMAINDER; 
		    constraint1.gridheight=1;
		    constraint1.weightx=1.0;
		    constraint1.weighty=1.0;
		    constraint1.fill= GridBagConstraints.BOTH;
		    constraint1.insets= new Insets(5,5,5,5); //t,l,b,r
		    add(scroller, constraint1);
		    
		    // setting  contraint1 for printButt Button
			   constraint1.gridx=1;
			   constraint1.gridy=3;
			   constraint1.gridwidth=0;
			   constraint1.gridheight=0;
			   constraint1.weightx=0.0;
			   constraint1.weighty=0.0;
			   constraint1.fill=GridBagConstraints.NONE;
			   add(printButt, constraint1);
			   
			   
				

                setVisible(true);

	}
	
	public void performTransaction(String branchName)
	{
		
		String pseudoStore=branchName + "store";
		DBConnection firstkonnect=new DBConnection();  // create a connection to the DB.branches
		String branchQuery="SELECT branch_id FROM branch WHERE branch_name='"+ branchName + "';";   // query String.
		result1=firstkonnect.retrieve(branchQuery);
		try {
			if(result1.next())
			{
				branchID=result1.getInt("branch_id");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		firstkonnect.closeConnection();
		
		DBConnection konnect=new DBConnection();  // create a connection to the DB.
		
		String myQuery="SELECT * FROM orders WHERE branch_id=" + branchID + ";";   // query String.
		result=konnect.retrieve(myQuery);
		int checkcheck=0;
		
		try 
		{
			while(result.next())               // loop through the returned result.
			{
				checkcheck++;
				int prodid=result.getInt("product_id");
				productAmount=result.getInt("amount");
				productQuantity=result.getInt("quantity");
				paymentStatus=result.getString("payment");
				productTotal=productTotal + productAmount;
				
			    DBConnection connect=new DBConnection();  // create a connection to the DB.
				String query="SELECT * FROM " + pseudoStore + " WHERE product_id=" + prodid + ";";
				result2=connect.retrieve(query);
				if(result2.next())
				{
					productName=result2.getString("product_name");
					productCostPrice=result2.getInt("cost_price");
				}
				
				profit=productAmount - (productCostPrice * productQuantity);
				productTotalProfit=productTotalProfit + profit;
				Object[] rows={productName, productQuantity, productAmount,paymentStatus, profit,  "", ""};
				//model=(DefaultTableModel)itemsCart.getModel();
				model.addRow(rows);
				
		       
		        
		        
		        
			}
	     }
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		konnect.closeConnection();
		if(productTotal>0)
		{
			Object[] row={"----", "----", "----","----", productTotalProfit, productTotal, ""};
			model.addRow(row);
		}
		
		if(checkcheck==0)
		{
			JOptionPane.showMessageDialog(this,"Hey there, No Transaction Yet from  " + branchName,"Attention!", JOptionPane.WARNING_MESSAGE);
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getSource()==printButt)
		  {
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
	}

}
