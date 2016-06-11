package bms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Addproduct extends JPanel implements ActionListener
{
	
	  JLabel productName, costprice, sellingprice, productquantity, store;
	  JButton addproduct;
	  JTextField productNameField, costpriceField, sellingpriceField, quantityField;
	  JComboBox chooseStore;
	  GridBagConstraints constraint1;
	  String[] storeList=new String[] {"GetAllStore", "TheCenterStore", "WestSideMallStore"};
	  
    public Addproduct()
    {
    	setLayout(new GridBagLayout());
  	  
    	// initializing components
    	  constraint1=new GridBagConstraints();
    	  productName=new JLabel("Product Name");
    	  costprice=new JLabel("Cost Price");
    	  sellingprice=new JLabel("Selling Price");
    	  productquantity=new JLabel("Quantity");
    	  addproduct=new JButton("AddProduct");
    	  productNameField=new JTextField(15);
    	  costpriceField=new JTextField(15);
    	  sellingpriceField=new JTextField(15);
    	  quantityField=new JTextField(15);
    	  store=new JLabel("Choose Store");
		  chooseStore=new JComboBox(storeList);
    	  
    	  
    	  
    	  addproduct.addActionListener(this); // add a Listener
    	  
    	// setting  contraint1 for label productName
		   constraint1.gridx=1;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(productName, constraint1);
		   
		// setting  contraint1 for productNameField 
		   constraint1.gridx=2;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(productNameField, constraint1);
    	  
		// setting  contraint1 for label costprice
		   constraint1.gridx=1;
		   constraint1.gridy=2;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(costprice, constraint1);
		   
		   
		// setting  contraint1 for costpriceField 
		   constraint1.gridx=2;
		   constraint1.gridy=2;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(costpriceField, constraint1);
		   
		// setting  contraint1 for sellingprice
		   constraint1.gridx=1;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(sellingprice, constraint1);
		   
		// setting  contraint1 for sellingpriceField
		   constraint1.gridx=2;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(sellingpriceField, constraint1);
		   
		   
		// setting  contraint1 for productquantity
		   constraint1.gridx=1;
		   constraint1.gridy=4;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(productquantity, constraint1);
		   
		   
		// setting  contraint1 for quantityField
		   constraint1.gridx=2;
		   constraint1.gridy=4;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(quantityField, constraint1);
		   
		   // setting constraint for Store Label
		    
		    constraint1.gridx=1;
		    constraint1.gridy=5;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    add(store, constraint1);
		    
       // setting constraint for chooseStore JCombox
		    
		    constraint1.gridx=2;
		    constraint1.gridy=5;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    add(chooseStore, constraint1);
		   
		// setting  contraint1 for addproduct
		   constraint1.gridx=3;
		   constraint1.gridy=5;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(addproduct, constraint1);
        setBackground(Color.WHITE);
    	setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getSource()==addproduct)
		{
			if(productNameField.getText().equalsIgnoreCase("") || sellingpriceField.getText().equalsIgnoreCase("") || costpriceField.getText().equalsIgnoreCase("") ||quantityField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Please Ensure No TextField is Empty","Attention!", JOptionPane.YES_NO_OPTION);
			}
			else
			{
				String currentStore=(String)chooseStore.getSelectedItem();
				String productname=productNameField.getText();
				String sPrice=sellingpriceField.getText();
				String cPrice=costpriceField.getText();
				String pQuantity=quantityField.getText();
				
                DBConnection konnect=new DBConnection();  // create a connection to the DB.
				String myQuery="INSERT INTO " + currentStore + "(product_name, unit_in_stock, cost_price, selling_price ) VALUES('" + productname +"','" + pQuantity + "','" + cPrice + "','" + sPrice + "');";   // query String.
				konnect.upDate(myQuery);
				
				productNameField.setText("");
				sellingpriceField.setText("");
				costpriceField.setText("");
				quantityField.getText();
				konnect.closeConnection();
				
				JOptionPane.showMessageDialog(this,"Operation Successful!","Message!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}

		
	}

}
