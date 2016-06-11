package bms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{
	  Color bgColor=new Color(0.2f, 0.6f, 0.9f);
	  Color bgcolour=new Color(0.1f, 0.7f, 0.9f);
	  JPanel pan1, pan2, pan3, pan4, pan5,pan6, pan7, pan8, pan9;
	  JButton butt;
	  JTextField text1;
	  JLabel label1, label2;
	  JPasswordField pass;
	  GridBagConstraints con;
	  
      Login()
      {
    	  setBackground(Color.white);
    	  setTitle("Business Management System");
    	  setBounds(200,100, 1000,600);

    	  //setLayout(new GridBagLayout());
    	  
    	  
    	 
    	  
    	  // initializing staff-id label and field
    	  
    	  label1=new JLabel("Staff-ID");
    	  text1=new JTextField(17);
    	  
    	  
    	  // initializing the password label and field.
    	  label2=new JLabel("PassWord");
    	  pass=new JPasswordField(16);
    	  
    	  
    	  
    	  // setting and add actionListener to the submit button.
    	  butt=new JButton("submit");
    	  butt.addActionListener(this);
    	 
    	  
    	  
    	  
    	  // center panel, holding the input fields
    	  pan1=new JPanel();
    	  pan1.setBackground(bgcolour);
    	  pan1.setBorder((BorderFactory.createLineBorder(Color.cyan)));
    	  pan1.setLayout(new GridBagLayout());
    	  con=new GridBagConstraints();
    	  
    	  
    	  // setting constraint for staff-id label
    	  con.gridx=1;
    	  con.gridy=1;
    	  con.gridwidth=1;
    	  con.gridheight=1;
    	  con.weightx=0.0;
    	  con.weighty=0.0;
    	  con.fill=GridBagConstraints.BOTH;
    	  con.insets= new Insets(2,2,2,2); //t,l,b,r
    	  pan1.add(label1,con);
    	  
    	  
    	  
    	  
    	  // setting constraint for staff-id input field
    	  con.gridx=2;
    	  con.gridy=1;
    	  con.gridwidth=1;
    	  con.gridheight=1;
    	  con.weightx=0.0;
    	  con.weighty=0.0;
    	  con.fill=GridBagConstraints.BOTH;
    	  con.insets= new Insets(2,2,2,2); //t,l,b,r
    	  pan1.add(text1, con);
    	  
    	  
    	  // setting constraint for password label
    	  con.gridx=1;
    	  con.gridy=2;
    	  con.gridwidth=1;
    	  con.gridheight=1;
    	  con.weightx=0.0;
    	  con.weighty=0.0;
    	  con.fill=GridBagConstraints.BOTH;
    	  con.insets= new Insets(2,2,2,2); //t,l,b,r
    	  pan1.add(label2, con);
    	  
    	  // setting constraint for password field
    	  con.gridx=2;
    	  con.gridy=2;
    	  con.gridwidth=1;
    	  con.gridheight=1;
    	  con.weightx=0.0;
    	  con.weighty=0.0;
    	  con.fill=GridBagConstraints.BOTH;
    	  con.insets= new Insets(2,2,2,2); //t,l,b,r
    	  pan1.add(pass, con);
    	  
    	  // setting constraint for Login Butt
    	  con.gridx=2;
    	  con.gridy=3;
    	  con.gridwidth=1;
    	  con.gridheight=1;
    	  con.weightx=0.0;
    	  con.weighty=0.0;
    	  con.fill=GridBagConstraints.BOTH;
    	  con.insets= new Insets(2,2,2,2); //t,l,b,r
    	  pan1.add(butt, con);
    	  
    	  pan1.setBackground(bgColor);
    	  pan1.setBounds(250,150,500,400);
    	  pan1.setVisible(true);
    	  add(pan1);
    	  pan1.revalidate();
    	  
    	  
    	  
    	  setVisible(true);
    	  
      }
      
      public String getName()
      {
    	  return text1.getText();
      }
      
      @SuppressWarnings("deprecation")
	  public String getPass()
      {
    	  return pass.getText();
      }
      
      

	  @Override
	  public void actionPerformed(ActionEvent event) 
	  {
		// TODO Auto-generated method stub
		    String name=getName();
		    String passs=getPass();
		    int counter=0, branchID;
		    AbstractFactory staffFactory=FactoryProducer.getFactory("STAFF"); // create a staff factory class.
		    String username, passwd, role, branchName="";
			ResultSet result=null, result1=null;
			DBConnection konnect=new DBConnection();  // create a connection to the DB.
			//System.out.println("Connection Successful");/
			String myQuery="SELECT * FROM users;";   // query String.
			result=konnect.retrieve(myQuery);
			
			try 
			{
				while(result.next())               // loop through the returned result.
				{
					username=result.getString("user_name");
					passwd=result.getString("password");
					role=result.getString("designation");
					branchID=result.getInt("branch_id");
					
					DBConnection connect=new DBConnection();  // create a connection to the DB.
					String query="SELECT branch_name FROM branch WHERE branch_id=" + branchID + ";";   // query String.
					result1=connect.retrieve(query);
					if(result1.next())
					{
						branchName=result1.getString("branch_name");
					}
					
					
					if(name.equalsIgnoreCase(username) && passs.equalsIgnoreCase(passwd))
					{
						
						//System.out.print(getName() + "  " + getPass());
						if(role.equalsIgnoreCase("salesRep"))
						{
							staffFactory.getStaff("salesRep", branchName);
						}
						if(role.equalsIgnoreCase("storeKeeper"))
						{
							staffFactory.getStaff("storeKeeper", branchName);
						}
						if(role.equalsIgnoreCase("accountant"))
						{
							staffFactory.getStaff("accountant", branchName);
						}
						if(role.equalsIgnoreCase("administrator"))
						{
							staffFactory.getStaff("administrator", branchName);
							
						}
						 counter++;
						 this.dispose(); // calls dispose on this object ie. 
	                      // the login window 
						 //System.exit(ABORT);
						break;
					}
   			   } 
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			konnect.closeConnection();
			
			
            if(counter==0)
            {
            	text1.setText("");
                pass.setText("");

                JOptionPane.showMessageDialog(this,"Username and/or password is incorrect!","Attention!", JOptionPane.WARNING_MESSAGE);

            }
            			  

	  }
     
}
