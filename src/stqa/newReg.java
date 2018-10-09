package stqa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class newReg extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	 JTextField t1;
	 JButton btn;
	 JPasswordField p1,p2;
	 
	 newReg()
	 {

		  JFrame fr = new JFrame("Signup Form");
		  l1 = new JLabel("Username: ");
		  l2 = new JLabel("Password");
		  l3 = new JLabel("Confirm");
		  t1 = new JTextField();
		  p1 = new JPasswordField();
		  p2 = new JPasswordField();
		  btn = new JButton("Register");
		  
		  l1.setBounds(80, 30, 200, 30);
		  l2.setBounds(80, 70, 100, 30);
		  l3.setBounds(80, 110, 100, 30);
		  t1.setBounds(300, 30, 200, 30);
		  p1.setBounds(300, 70, 200, 30);
		  p2.setBounds(300, 110, 200, 30);
		  btn.setBounds(150, 210, 110, 30);
		  fr.setSize(600, 400);
		  fr.setLayout(null);
		  fr.setVisible(true);
		  fr.add(l1);
		  fr.add(l2);
		  fr.add(t1);
		  fr.add(l3);
		  fr.add(p1);
		  fr.add(p2);
		  fr.add(btn);
		  btn.addActionListener(this);
		  
	 }

	public void actionPerformed(ActionEvent e) {
		String un=t1.getText();
		String pn=p1.getText();
		String cpn=p2.getText();
		
		if(!pn.equals(cpn))
			JOptionPane.showMessageDialog(this,"Passwords dont match!!","Error",JOptionPane.ERROR_MESSAGE); 
		else if(un.equals("") || pn.equals(""))
			JOptionPane.showMessageDialog(this,"Username and Password fields cant be empty","Error",JOptionPane.ERROR_MESSAGE); 

		else
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
		        Statement st = conn.createStatement();
		        st.execute("insert into empl values('"+un+"','"+pn+"');");
	            JOptionPane.showMessageDialog(this,"Registered Successfully"); 

			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}  
		}	
	}	 
}