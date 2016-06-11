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
public class StoreKeeper extends JFrame implements Staff, ActionListener
{
	
	   ResultSet result=null, result2;
	   JButton verifyButt, clearButt, logoutButt;
	   GridBagConstraints constraint;
	   JLabel transactionID;
	   JTextField transactionIDField;
	   JTable itemsCart;
	   JScrollPane scroller;
	   Color bgcolour=new Color(0.1f, 0.7f, 0.9f);
	   DefaultTableModel model;
	   String pseudoStore;
	   
	public StoreKeeper(String theBranchName)
	{
		 
		   
		   
			   setTitle("DOASCO NIGERIA ENTERPRISE: StoreKeeper's Window | "+ " Branch :" + theBranchName.toUpperCase());
			   setBounds(200,100,1000,600);
			   setBackground(bgcolour);
			   setLayout(new GridBagLayout());
			   
			   // Initializing components
			   constraint=new GridBagConstraints();
			   verifyButt=new JButton("VerifyPayment");
			   clearButt=new JButton("Clear");
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
			   verifyButt.addActionListener(this);
			   clearButt.addActionListener(this);
			   logoutButt.addActionListener(this);
			   
			   
			// setting  constraint for label transactionID
			   constraint.gridx=1;
			   constraint.gridy=1;
			   constraint.gridwidth=1;
			   constraint.gridheight=1;
			   constraint.weightx=0.0;
			   constraint.weighty=0.0;
			   constraint.insets= new Insets(0,2,0,0); //t,l,b,r
			   add(transactionID, constraint);
			   
			   // setting  constraint for transactionIDField
			   constraint.gridx=2;
			   constraint.gridy=1;
			   constraint.gridwidth=1;
			   constraint.gridheight=1;
			   constraint.weightx=0.0;
			   constraint.weighty=0.0;
			   constraint.fill=GridBagConstraints.HORIZONTAL;
			   add(transactionIDField, constraint);
			   
			   // setting  contraint1 for verifyButt Button
			   constraint.gridx=3;
			   constraint.gridy=1;
			   constraint.gridwidth=1;
			   constraint.gridheight=1;
			   constraint.weightx=0.0;
			   constraint.weighty=0.0;
			   constraint.fill=GridBagConstraints.NONE;
			   add(verifyButt, constraint);
			   
			   // setting  contraint1 for logoutButt Button
			   constraint.gridx=4;
			   constraint.gridy=1;
			   constraint.gridwidth=1;
			   constraint.gridheight=1;
			   constraint.weightx=0.0;
			   constraint.weighty=0.0;
			   constraint.fill=GridBagConstraints.NONE;
			   add(logoutButt, constraint);
			   
			// setting constraint for scroller.
			    
			    constraint.gridx=1;
			    constraint.gridy=2;
			    constraint.gridwidth = GridBagConstraints.REMAINDER; 
			    constraint.gridheight = 1;
			    constraint.weightx= 1.0;
			    constraint.weighty= 1.0;
			    constraint.fill= GridBagConstraints.BOTH;
			    constraint.insets= new Insets(2,2,2,2); //t,l,b,r
			    add(scroller, constraint);
			    //scrollerPan.revalidate();
			    
			 // setting  constraint for clearButt Button
				   constraint.gridx=2;
				   constraint.gridy=4;
				   constraint.gridwidth=1;
				   constraint.gridheight=1;
				   constraint.weightx=0.0;
				   constraint.weighty=0.0;
				   add(clearButt, constraint);
				   
				   
				   setVisible(true);
				   pseudoStore=theBranchName + "store";
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		
		if(event.getSource()==verifyButt)
		{
			String collectedValue=transactionIDField.getText();
			//System.out.println(collectedValue);
			if(!(collectedValue.equalsIgnoreCase("")))
			{
				String productName="null", paymentStatus="";
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
						paymentStatus=result.getString("payment");
						productTotal=productTotal + productAmount;
						
					    DBConnection connect=new DBConnection();  // create a connection to the DB.
						String query="SELECT product_name FROM" + pseudoStore + " WHERE product_id=" + prodid + ";";
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
				if(productTotal>0 && paymentStatus.equalsIgnoreCase("paid"))
				{
					Object[] row={"----", "----", "----", productTotal, "PAID"};
					model.addRow(row);
				}
			}
			else
			{
					JOptionPane.showMessageDialog(this,"Please Ensure The Search TextField is not Empty","Attention!", JOptionPane.WARNING_MESSAGE);
			}
			
		
		
	     }
		
		 if(event.getSource()==clearButt)
		 {
			 transactionIDField.setText("");
			 model.setRowCount(0);
		 }
         if(event.getSource()==logoutButt)
         {
        	 JOptionPane.showMessageDialog(this,"Are You Sure You Want To Logout?","Attention!", JOptionPane.WARNING_MESSAGE);
        	 this.dispose();
        	 new Login();
         }
      }
	
	
	@Override
	public void authentifyStaff() {
		// TODO Auto-generated method stub
		
	}

	
}
	

