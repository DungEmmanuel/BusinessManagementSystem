package bms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DeleteStaff extends JPanel implements ActionListener
{
	  JButton delete;
	  JLabel staffName, staffPassword;
	  JTextField staffNameField, staffPasswordField;
	  GridBagConstraints constraint1;

     public DeleteStaff()
     {
    	 setLayout(new GridBagLayout());
   	  
     	// initializing components
     	  constraint1=new GridBagConstraints();
     	  staffName=new JLabel("StaffName");
     	  staffPassword=new JLabel("Staff Password");
     	  delete=new JButton("Delete Staff");
     	  staffNameField=new JTextField(15);
     	  staffPasswordField=new JTextField(15);
     	  
     	  delete.addActionListener(this);
     	  
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
		   
		
		   
		// setting  contraint1 for button addButton
		   constraint1.gridx=2;
		   constraint1.gridy=3;
		   constraint1.gridwidth=1;
		   constraint1.gridheight=1;
		   constraint1.weightx=0.0;
		   constraint1.weighty=0.0;
		   add(delete, constraint1);
		   
		   setBackground(Color.WHITE);
		   setBounds(100,100,800, 350);
		   setVisible(true);

     }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		// TODO Auto-generated method stub
		if(event.getSource()==delete)
		{

			if(staffNameField.getText().equalsIgnoreCase("") || staffPasswordField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(this,"Please Ensure No TextField is Empty","Attention!", JOptionPane.YES_NO_OPTION);
			}
			else
			{
				String staffname=staffNameField.getText();
				String staffpassword=staffPasswordField.getText();
				
				
                DBConnection konnect=new DBConnection();  // create a connection to the DB.
				String myQuery="DELETE FROM users WHERE user_name='" + staffname + "' AND password='" + staffpassword + "';";   // query String.
				konnect.upDate(myQuery);
				
				staffNameField.setText("");
				staffPasswordField.setText("");
				konnect.closeConnection();
				
			}
			JOptionPane.showMessageDialog(this,"Operation Successful!","Message!", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
