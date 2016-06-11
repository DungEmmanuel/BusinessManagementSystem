package bms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnection 
{
	//this class handles all database connections
	

    Connection connect=null;
    Statement statement=null;
    public ResultSet rs=null;

    /** Creates a new instance of ConnectToDB */
    //initialize the variables in this constructor

    public DBConnection() 
    {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           connect=DriverManager.getConnection("jdbc:mysql://localhost:8889/BMS","root","root");
           //System.out.println("Connection Successful");
           statement=connect.createStatement();
        }
        catch(ClassNotFoundException cnfe)
        {
            //JOptionPane.showMessageDialog(null, cnfe.getMessage(),"ERROR",
                    //javax.swing.JOptionPane.ERROR_MESSAGE);
        	
        	cnfe.printStackTrace();
        }

        catch(SQLException sqle)
        {
           // JOptionPane.showMessageDialog(null,sqle.getMessage(),"ERROR",
                    //javax.swing.JOptionPane.ERROR_MESSAGE);
        	
        	sqle.printStackTrace();
        }


    }
    
    //method to update database; use this for all insert, update statements
    public void upDate(String query)
    {
        try
        {
            statement.executeUpdate(query);
        }

        catch(SQLException sqle)
        {
                JOptionPane.showMessageDialog(null,sqle.getMessage(),"ERROR",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    //method to retrieve records from db;use this for all select statements
    public ResultSet retrieve(String query)
    {
        try
        {
            rs=statement.executeQuery(query);
        }

        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null,sqle.getMessage(),"ERROR",
                    //javax.swing.JOptionPane.ERROR_MESSAGE);
        	sqle.printStackTrace();
        }


        return rs;

    }


    //Close the database connection
    public void closeConnection()
    {
        try
        {
            statement.close();
            connect.close();
        }

        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }



}
