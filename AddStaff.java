package bms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class AddStaff extends JPanel implements ActionListener
{
	  JLabel staffName, staffPassword, staffDesignation, branches;
	  JButton addStaff;
	  JTextField staffNameField, staffPasswordField, designationField;
	  GridBagConstraints constraint1;
	  ResultSet result=null;
	  JComboBox chooseBranch;
	  String[] branchList=new String[] {"GetAll", "TheCenter", "WestSideMall"};
	  int branchID;
      public AddStaff()
      {
    	  setLayout(new GridBagLayout());
    	  
    	// initializing components
    	  constraint1=new GridBagConstraints();
    	  staffName=new JLabel("Staff Name");
    	  staffPassword=new JLabel("Staff Password");
    	  staffDesignation=new JLabel("Staff Designation");
    	  addStaff=new JButton("AddStaff");
    	  staffNameField=new JTextField(15);
    	  staffPasswordField=new JTextField(15);
    	  designationField=new JTextField(15);
    	  branches=new JLabel("Choose Branch");
		  chooseBranch=new JComboBox(branchList);
    	  addStaff.addActionListener(this);
    	  
    	  
    	// setting  contraint1 for label staffName
		   constraint1.gridx=1;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(staffName, constraint1);
		   
		// setting  contraint1 for TextField staffNameField
		   constraint1.gridx=2;
		   constraint1.gridy=1;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(staffNameField, constraint1);
    	  
		// setting  contraint1 for label staffPassword
		   constraint1.gridx=1;
		   constraint1.gridy=2;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(staffPassword, constraint1);
		   
		// setting  contraint1 for TextField staffPasswordField
		   constraint1.gridx=2;
		   constraint1.gridy=2;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(staffPasswordField, constraint1);
		   
		// setting  contraint1 for label staffDesignation
		   constraint1.gridx=1;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(staffDesignation, constraint1);
		   
		// setting  contraint1 for TextField staffDesignationField
		   constraint1.gridx=2;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(designationField, constraint1);
		   
		// setting constraint for Store Label
		    
		    constraint1.gridx=1;
		    constraint1.gridy=4;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    add(branches, constraint1);
		    
      // setting constraint for chooseStore JCombox
		    
		    constraint1.gridx=2;
		    constraint1.gridy=4;
		    constraint1.gridwidth=1;
		    constraint1.gridheight=1;;
		    constraint1.weightx=0.0;
		    constraint1.weighty=0.0;
		    //constraint1.fill=GridBagConstraints.HORIZONTAL;
		    add(chooseBranch, constraint1);
		    
		// setting  contraint1 for button addButton
		   constraint1.gridx=3;
		   constraint1.gridy=4;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(addStaff, constraint1);
		   
		   setBackground(Color.WHITE);
		   setBounds(100,100,800, 350);
		   setVisible(true);
      }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		if(event.getSource()==addStaff)
		{
			if(staffNameField.getText().equalsIgnoreCase("") || staffPasswordField.getText().equalsIgnoreCase("") || designationField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Please Ensure No TextField is Empty","Attention!", JOptionPane.YES_NO_OPTION);
			}
			else
			{
				String choosenBranch=(String)chooseBranch.getSelectedItem();
				DBConnection connect=new DBConnection();  // create a connection to the DB.
				String Query="SELECT branch_id FROM branch WHERE branch_name='" + choosenBranch + "';";  // query String.
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
				
				String staffname=staffNameField.getText();
				String staffpassword=staffPasswordField.getText();
				String staffdesignation=designationField.getText();
				
                DBConnection konnect=new DBConnection();  // create a connection to the DB.
				String myQuery="INSERT INTO users (user_name, password, designation,branch_id) VALUES('" + staffname +"','" + staffpassword + "','" + staffdesignation + "'," + branchID + ");";   // query String.
				konnect.upDate(myQuery);
				
				staffNameField.setText("");
				staffPasswordField.setText("");
				designationField.setText("");
				konnect.closeConnection();
			}
			
			JOptionPane.showMessageDialog(this,"Operation Successful!","Message!", JOptionPane.INFORMATION_MESSAGE);
		}
		// TODO Auto-generated method stub
		
	}
}
