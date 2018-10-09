package stqa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class items extends JFrame implements ActionListener
{
	int ht=0,cnt=0;
	int bill=0;
	int tot[]=new int[100];
	int mot[]=new int[100];
	JButton btn6;
	JFrame fr = new JFrame("Order Groceries");
	JSpinner z[]=new JSpinner[50];
	
	items()
	{ 	
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
	        Statement st = conn.createStatement();
			
			ResultSet rs= st.executeQuery("select * from gro;");
            JLabel x=new JLabel("NAME            PRICE              AVAILABLE(kg)                ORDER(kg)");
            x.setBounds(50, 20, 500, 20);
            fr.add(x);
	        while(rs.next())
	        {
	        	JLabel y=new JLabel(rs.getString(1)+"              "+rs.getString(2)+"                     "+rs.getString(3));
	        	tot[cnt]=Integer.parseInt(rs.getString(3));
	        	mot[cnt++]=Integer.parseInt(rs.getString(2));
	        	y.setBounds(50, 60+ht, 500, 20);      
	        	fr.add(y);
	        	ht+=30;
	        }
	       final JLabel status=new JLabel();
	       status.setBounds(100, 300, 200, 50);
	       fr.add(status);
	       ht=0;
	       final int cost[]=new int[cnt];
	        
	        for(int i=0;i<cnt;i++)
	        	{
	        		SpinnerModel val=new SpinnerNumberModel(0,0,tot[i],1); 
	        		z[i]= new JSpinner(val);
	        		z[i].setBounds(350,70+ht,50,20);
	        		fr.add(z[i]);
	        		ht+=30;
	        		z[i].addChangeListener(new ChangeListener() 
	        		{
	        	         public void stateChanged(ChangeEvent e)
	        	         {	
	        	        	 Object ob=e.getSource();
	        	        	 
	        	        	 int k;
	        	        	 for(k=0;k<cnt;k++)
	        	        	 {
	        	        		 if(ob==z[k])
	        	        			 break;
	        	        	 }
	        	        	 int r=Integer.parseInt(((JSpinner)e.getSource()).getValue().toString());
 	        	        	 cost[k]=r*mot[k];
	        	        	 
	        	        	 
	        	        	 bill=0;
	        	        	 for(int i=0;i<cnt;i++)
	        	        	 {
	        	        		 bill+=cost[i];
	        	        		 
	        	        	 }
	        	        		 
	        	        	 status.setText("Total bill : "+bill);
	        	         }
	        	      });
	        	}
        	
	        btn6 = new JButton("Confirm Order");
			btn6.setBounds(35, 400,250, 50);
			fr.add(btn6);
			
			fr.setSize(600, 600);
			fr.setLayout(null);
			fr.setVisible(true);
			
			btn6.addActionListener(this);		
	       
		}
		
		catch (Exception e2) {

			e2.printStackTrace();
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
    	if(o==btn6 && bill!=0)
    	{
    		JOptionPane.showMessageDialog(this,"Total bill amount:  "+bill,"Order Success",JOptionPane.PLAIN_MESSAGE);
    		fr.dispose();
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(this,"Add Food Items!!","Error Message", JOptionPane.ERROR_MESSAGE);
    	}
	
	}
}