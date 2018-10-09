package stqa;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
 
public class LoginForm extends JFrame implements ActionListener
{
 JLabel l1, l2, l3;
 JTextField t1;
 JButton btn1,btn2,btn3,btn4;
 JPasswordField p1;
 LoginForm()
 {
  JFrame frame = new JFrame("Login Form");
  l1 = new JLabel("Login Form");
  l2 = new JLabel("Username");
  l3 = new JLabel("Password");
  t1 = new JTextField();
  p1 = new JPasswordField();
  btn1 = new JButton("Login");
  btn2 = new JButton("Clear");
  btn3 = new JButton("Register");
  btn4 = new JButton("Admin");
  frame.setSize(600, 600);
  frame.setLayout(null);
  frame.setVisible(true);
  l1.setBounds(100, 30, 200, 30);
  l2.setBounds(80, 70, 100, 30);
  l3.setBounds(80, 110, 100, 30);
  t1.setBounds(300, 70, 200, 30);
  p1.setBounds(300, 110, 200, 30);
  btn1.setBounds(150, 160, 80, 30);
  btn2.setBounds(350, 160, 80, 30);
  btn3.setBounds(220,210,110,30);
  btn4.setBounds(250,160,80,30);
 
  frame.add(l1);
  frame.add(l2);
  frame.add(t1);
  frame.add(l3);
  frame.add(p1);
  frame.add(btn1);
  frame.add(btn2);
  frame.add(btn3);
  frame.add(btn4);
 
  frame.setSize(600, 300);
  frame.setLayout(null);
  frame.setVisible(true);

  btn1.addActionListener(this);
  btn2.addActionListener(this);
  btn3.addActionListener(this);
  btn4.addActionListener(this);

 }
 public void actionPerformed(ActionEvent e)
 {
   Object o=e.getSource();
   if(o==btn1)
  {
   String uname = t1.getText();
   String pass = p1.getText();

    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
        Statement st = conn.createStatement();
        ResultSet rs= st.executeQuery("select * FROM empl where usr='"+uname+"';");

        if(rs.next() == false)
        	JOptionPane.showMessageDialog(this,"No such user registered","Error",JOptionPane.ERROR_MESSAGE);

        else
        {
        	rs.previous();	
	        while(rs.next())
	        {
	            //System.out.println("xx");
	            String val=rs.getString("pwd");
		        if(val.equals(pass))
		        {
		        	new items();
		        	break;
		        }
		         else
		            JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE); 
	        }
        }
    }


catch(Exception ex)
    {
        System.out.println(ex);
    }


  }
   
   if(o==btn3)
   {
	   new newReg();
   }
   if(o==btn2)
   {
    t1.setText("");
    p1.setText("");
   }

   	if(o==btn4)
   	{
   		String uname = t1.getText();
   	    String pass = p1.getText();

   	    try{
   	        Class.forName("com.mysql.jdbc.Driver");
   	        Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
   	        Statement st = conn.createStatement();
   	        ResultSet rs= st.executeQuery("select * FROM admin where usr='"+uname+"';");

   	        if(rs.next() == false)
   	        	JOptionPane.showMessageDialog(this,"No such user registered","Error",JOptionPane.ERROR_MESSAGE);

   	        else
   	        {
   	        	rs.previous();	
   		        while(rs.next())
   		        {
   		            String val=rs.getString("pwd");
   			        if(val.equals(pass))
   			        {
   			        	new Admin();
   			        	break;
   			        }
   			         else
   			            JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE); 
   		        }
   	        }
   	    }


   	catch(Exception ex)
   	    {
   	        System.out.println(ex);
   	    }
   	}
}
 
 public static void main(String[] args)
 {
  new LoginForm();
 }
}
