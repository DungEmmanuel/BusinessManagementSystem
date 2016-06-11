package bms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class Accountant extends JFrame implements ActionListener, Staff
{
	   ResultSet result=null, result2=null;
	   JButton submitButt, confirmPaymentButt, cancelTransactionButt, logoutButt;
	   GridBagConstraints constraint1;
	   JLabel transactionID;
	   JTextField transactionIDField;
	   JTable itemsCart;
	   JScrollPane scroller;
	   Color bgcolour=new Color(0.1f, 0.7f, 0.9f);
	   
	   String pseudoStore;
	   DefaultTableModel model;
	   
	   public Accountant(String theBranchName)
	   {
		   setTitle("DOASCO NIGERIA ENTERPRISE: Accountant's Window | " + " Branch : "+ theBranchName.toUpperCase());
		   setBounds(200,100,1000,600);
		   setBackground(bgcolour);
		   setLayout(new GridBagLayout());
		   
		   // intialising components
		   constraint1=new GridBagConstraints();
		   submitButt=new JButton("Submit");
		   confirmPaymentButt=new JButton("ConfirmPaymentButt");
		   cancelTransactionButt=new JButton("CancelTransaction");
		   logoutButt=new JButton("Logout");
		   transactionID=new JLabel("Enter TransactionID");
		   transactionIDField=new JTextField(15);
		   itemsCart=new JTable();
		   
		   itemsCart.setPreferredSize(new Dimension(800,250));
		   model=(DefaultTableModel)itemsCart.getModel();
		   model.addColumn("ITEM");
		   //model.addColumn("PRICE");
		   model.addColumn("QUANTITY");
		   model.addColumn("AMOUNT");
		   model.addColumn("TOTAL");
		   model.addColumn("PAYMENT STATUS");
		   
		   scroller=new JScrollPane(itemsCart, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   scroller.setPreferredSize(new Dimension(900, 300));
		   scroller.revalidate();
		   scroller.setVisible(true);
		   
		   // adding listener to buttons
		   submitButt.addActionListener(this);
		   confirmPaymentButt.addActionListener(this);
		   cancelTransactionButt.addActionListener(this);
		   logoutButt.addActionListener(this);
		    
		   
		   // setting  contraint1 for label transactionID
		   constraint1.gridx=1;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.insets= new Insets(0,2,0,0); //t,l,b,r
		   add(transactionID, constraint1);
		   
		   // setting  contraint1 for transactionIDField
		   constraint1.gridx=2;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.HORIZONTAL;
		   add(transactionIDField, constraint1);
		   
		   // setting  contraint1 for submitButt Button
		   constraint1.gridx=3;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.NONE;
		   add(submitButt, constraint1);
		   
		   // setting  contraint1 for logoutButt Button
		   constraint1.gridx=4;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   constraint1.fill=GridBagConstraints.NONE;
		   add(logoutButt, constraint1);
		   
		   
		   // setting constraint for scroller.
		    
		    constraint1.gridx=1;
		    constraint1.gridy=2;
		    constraint1.gridwidth = GridBagConstraints.REMAINDER; 
		    constraint1.gridheight = 1;
		    constraint1.weightx= 1.0;
		    constraint1.weighty= 1.0;
		    constraint1.fill= GridBagConstraints.BOTH;
		    constraint1.insets= new Insets(2,2,2,2); //t,l,b,r
		    add(scroller, constraint1);
		    //scrollerPan.revalidate();
		    
		 // setting  contraint1 for confirmPaymentButt Button
			   constraint1.gridx=2;
			   constraint1.gridy=4;
			   constraint1.gridwidth=1;
			   constraint1.gridheight=1;
			   constraint1.weightx=0.0;
			   constraint1.weighty=0.0;
			   add(confirmPaymentButt, constraint1); 
			   
			// setting  contraint1 for cancelTransactionButt Button
			   constraint1.gridx=3;
			   constraint1.gridy=4;
			   constraint1.gridwidth=0;
			   constraint1.gridheight=1;
			   constraint1.weightx=0.0;
			   constraint1.weighty=0.0;
			   constraint1.fill=GridBagConstraints.NONE;
			   add(cancelTransactionButt, constraint1);
			   
			   setVisible(true);
			   pseudoStore=theBranchName + "store";
		   
	   }
	   
	  

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		
		if(event.getSource()==submitButt)
		{
			String collectedValue=transactionIDField.getText();
			//System.out.println(collectedValue);
			if(!(collectedValue.equalsIgnoreCase("")))
			{
				String productName="null";
				int productAmount, productQuantity;
				int productTotal=0;
				DBConnection konnect=new DBConnection();  // create a connection to the DB.
				
				String myQuery="SELECT * FROM orders WHERE transaction_id ='" + collectedValue + "';";   // query String.
				result=konnect.retrieve(myQuery);
				
				try 
				{
					while(result.next())               // loop through the returned result.
					{
						
						int prodid=result.getInt("product_id");
						productAmount=result.getInt("amount");
						productQuantity=result.getInt("quantity");
						//paymentStatus=result.getString("payment");
						productTotal=productTotal + productAmount;
						
					    DBConnection connect=new DBConnection();  // create a connection to the DB.
						String query="SELECT product_name FROM " + pseudoStore + " WHERE product_id=" + prodid + ";";
						result2=connect.retrieve(query);
						if(result2.next())
						{
							productName=result2.getString("product_name");
						}
						
						
						Object[] rows={productName, productQuantity, productAmount, "", ""};
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
					Object[] row={"----", "----", "----", productTotal, ""};
					model.addRow(row);
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please Ensure The Search TextField is not Empty","Attention!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(event.getSource()==confirmPaymentButt)
		{
			String paymentstat="PAID";
			JOptionPane.showMessageDialog(this,"Please Ensure That The Displayed Order Has Been Paid for","Attention!", JOptionPane.WARNING_MESSAGE);
			String collectedValue=transactionIDField.getText();
			
			DBConnection konnect=new DBConnection();  // create a connection to the DB.
			String alterQuery="UPDATE orders SET payment='"+ paymentstat + "' WHERE transaction_id='"+ collectedValue + "';";
			//String myQuery="INSERT INTO orders (payment_status) VALUES('" + paymentstat +"');";   // query String.
			konnect.upDate(alterQuery);;
			JOptionPane.showMessageDialog(this,"Confirmed!!","Attention!", JOptionPane.WARNING_MESSAGE);
		}
		
		if(event.getSource()==cancelTransactionButt)
		{
			
			//JOptionPane.showOptionDialog(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7)(this,"Are You Sure You Want To Cancel This Transaction?","Attention!", JOptionPane.YES_NO_OPTION);
			JOptionPane.showMessageDialog(this,"Are You Sure You Want To Cancel This Transaction?","Attention!", JOptionPane.YES_NO_OPTION);
		}
		
		if(event.getSource()==logoutButt)
		{
			JOptionPane.showMessageDialog(this,"Are You Sure You Want To Logout?","Attention!", JOptionPane.YES_NO_OPTION);
			this.dispose();
			new Login();
		}
	}



	@Override
	public void authentifyStaff() {
		// TODO Auto-generated method stub
		
	}
	

	   
}
