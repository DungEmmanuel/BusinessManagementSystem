package bms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Administrator extends JFrame implements Staff
{
	JMenu managestaff, transaction, log, product, customer, branch;
	JMenuItem addstaff, deletestaff, editstaff, viewtrans, viewprofit, logout, addproduct, viewcustomer, getAll, theCenter, westsideMall;
    JMenuBar theBar;
    Color bgcolour=new Color(0.1f, 0.7f, 0.9f);
    FirstPanel intialPage=new FirstPanel();
    DeleteStaff deleteStaff=new DeleteStaff();
    AddStaff addStaff=new AddStaff();
    ViewTransaction viewtransaction=new ViewTransaction();
    ViewProfit viewProfit=new ViewProfit();
    Addproduct addProduct=new Addproduct();
    ViewCustomer viewCustomer=new ViewCustomer();
    String branchName="";
	
	 public Administrator(String theBranchName)
	 {
		 setTitle("DOASCO NIGERIA ENTERPRISE: ADMINISTRATOR'S WINDOW | "+ " Branch : " + theBranchName.toUpperCase());
		 setBounds(200,100,1000,600);
		 setBackground(bgcolour);
		 setLayout(new BorderLayout());
		 
		 theBar=new JMenuBar();
		 managestaff=new JMenu("ManageStaff");
		 transaction=new JMenu("Transactions");
		 log=new JMenu("Logout");
		 product=new JMenu("Product");
		 customer=new JMenu("Customer");
		 //customer.enable(false);
		 branch=new JMenu("Branches");
		 
		 addstaff=new JMenuItem("AddStaff");
		 addstaff.setEnabled(false);
		 
		 deletestaff=new JMenuItem("DeleteStaff");
		 deletestaff.setEnabled(false);
		 
		 editstaff=new JMenuItem("EditStaff");
		 editstaff.setEnabled(false);
		 
		 viewtrans=new JMenuItem("ViewTransactions");
		 viewtrans.setEnabled(false);
		 
		 viewprofit=new JMenuItem("ViewProfit");
		 viewprofit.setEnabled(false);
		 
		 logout=new JMenuItem("LogOut");
		 addproduct=new JMenuItem("AddProduct");
		 addproduct.setEnabled(false);
		 
		 viewcustomer=new JMenuItem("ViewCustomer");
		 viewcustomer.setEnabled(false);
		 
		 getAll=new JMenuItem("GetAll");
		 theCenter=new JMenuItem("The Center");
		 westsideMall=new JMenuItem("Westside Mall");
		 
		 
		// deletestaff.addActionListener(this);
		// editstaff.addActionListener(this);
		// viewtrans.addActionListener(this);
		 //viewprofit.addActionListener(this);
		 
		 managestaff.add(addstaff);
		 managestaff.add(deletestaff);
		 managestaff.add(editstaff);
		 
		 transaction.add(viewtrans);
		 transaction.add(viewprofit);
		 
		 log.add(logout);
		 
		 product.add(addproduct);
		 
		 customer.add(viewcustomer);
		 
		 branch.add(getAll);
		 branch.add(theCenter);
		 branch.add(westsideMall);
		 
		 theBar.add(branch);
		 theBar.add(managestaff);
		 theBar.add(transaction);
		 theBar.add(product);
		 theBar.add(customer);
		 theBar.add(log);
		 
		 setJMenuBar(theBar);
		 add(intialPage, BorderLayout.CENTER);
		 addstaff.addActionListener(
				 new ActionListener() 
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(deleteStaff);
						 remove(viewtransaction);
						 remove(viewProfit);
						 remove(addProduct);
						 remove(viewCustomer);
						 add(addStaff, BorderLayout.NORTH);
						 addStaff.revalidate();
						 repaint();
					 }
				 });
		 deletestaff.addActionListener(
				 new ActionListener() 
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(addStaff);
						 remove(viewtransaction);
						 remove(viewProfit);
						 remove(addProduct);
						 remove(viewCustomer);
						 add(deleteStaff, BorderLayout.NORTH);
						 deleteStaff.revalidate();
						 repaint();
					 }
				 });
		 
		 viewtrans.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(addStaff);
						 remove(deleteStaff);
						 remove(viewProfit);
						 remove(addProduct);
						 remove(viewCustomer);
						 add(viewtransaction, BorderLayout.CENTER);
						 viewtransaction.revalidate();
						 viewtransaction.viewTrans(branchName);
						 repaint();
					 }
				 });

		 
		 addproduct.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(addStaff);
						 remove(deleteStaff);
						 remove(viewtransaction);
						 remove(viewProfit);
						 remove(viewCustomer);
						 add(addProduct, BorderLayout.NORTH);
						 addProduct.revalidate();
						 repaint();
					 }
				 });
		 
		 viewprofit.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(addStaff);
						 remove(deleteStaff);
						 remove(viewtransaction);
						 remove(addProduct);
						 remove(viewCustomer);
						 //removeAll();
						 add(viewProfit, BorderLayout.CENTER);
						 viewProfit.revalidate();
						 viewProfit.performTransaction(branchName);
						 repaint();
					 }
				 });
		 
		 viewcustomer.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 remove(intialPage);
						 remove(addStaff);
						 remove(deleteStaff);
						 remove(viewtransaction);
						 remove(addProduct);
						 remove(viewProfit);
						 add(viewCustomer, BorderLayout.CENTER);
						 viewCustomer.revalidate();
						 viewCustomer.view(branchName);
						 repaint();
					 }
				 });
		 
		logout.addActionListener(
				 new ActionListener()
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 dismiss();
						 new Login();
					 }
				 });
		
		getAll.addActionListener(
				 new ActionListener() 
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 branchName=getAll.getText();
						// System.out.println(branchName);
						 addstaff.setEnabled(true);
						 deletestaff.setEnabled(true);
						 editstaff.setEnabled(true);
						 viewtrans.setEnabled(true);
						 viewprofit.setEnabled(true);
						 addproduct.setEnabled(true);
						 viewcustomer.setEnabled(true);
						 
						 
					 }
				 });
		
		theCenter.addActionListener(
				 new ActionListener() 
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 branchName=theCenter.getText();
						 addstaff.setEnabled(true);
						 deletestaff.setEnabled(true);
						 editstaff.setEnabled(true);
						 viewtrans.setEnabled(true);
						 viewprofit.setEnabled(true);
						 addproduct.setEnabled(true);
						 viewcustomer.setEnabled(true);
						 
					 }
				 });

		westsideMall.addActionListener(
				 new ActionListener() 
				 {
					 public void actionPerformed(ActionEvent event)
					 {
						 branchName=westsideMall.getText();
						 addstaff.setEnabled(true);
						 deletestaff.setEnabled(true);
						 editstaff.setEnabled(true);
						 viewtrans.setEnabled(true);
						 viewprofit.setEnabled(true);
						 addproduct.setEnabled(true);
						 viewcustomer.setEnabled(true);
					 }
				 });
		
		 setVisible(true);
		 
	 }
	
	

	@Override
	public void authentifyStaff() {
		// TODO Auto-generated method stub
		
	}
    public void dismiss()
    {
    	this.dispose();
    }
}
