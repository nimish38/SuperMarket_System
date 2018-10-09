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
import javax.swing.JTextField;

class Admin extends JFrame implements ActionListener 
{
	int cnt=0,j=0;
	JButton btn6,btn4,btn5;
	JLabel j1,j2;
	JTextField t1,t2,t3,t4,t5,t6;
	String nam[]=new String[50];
    JTextField test[]=new JTextField[50];
	JFrame fr = new JFrame("Update Values");
	
	Admin()
	{ 	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
	        Statement st = conn.createStatement();
			
	        int ht=0,cnt=0;
			ResultSet rs= st.executeQuery("select * from gro;");
            JLabel x=new JLabel("NAME            PRICE              AVAILABLE(kg)");
            x.setBounds(50, 20, 500, 20);
            fr.add(x);
	        
        	while(rs.next())
        	{
        		nam[cnt++]=rs.getString(1);
        		JLabel y=new JLabel(rs.getString(1));
	        	y.setBounds(50, 60+ht, 100, 20);      
	        	fr.add(y);
	        	test[j]=new JTextField();
	        	test[j+1]=new JTextField();
	        	
	        	test[j].setBounds(130, 60+ht, 80, 20);
	        	test[j+1].setBounds(250, 60+ht, 80, 20);
	        	fr.add(test[j]);
	        	fr.add(test[j+1]);
	        	ht+=30;
	        	j+=2;
        	}
        	 j1=new JLabel("Add item");
        	j1.setBounds(50,375,100,30);
        	fr.add(j1);  	
        	 t1=new JTextField();
        	t1.setBounds(50,400,80,20);
        	fr.add(t1);
        	 t2=new JTextField();
        	t2.setBounds(150,400,80,20);
        	fr.add(t2);
        	 t3=new JTextField();
        	t3.setBounds(250,400,80,20);
        	fr.add(t3);
        	 btn5=new JButton("Add item");
        	btn5.setBounds(350, 400, 180,30);
        	fr.add(btn5);
        	
        	j2=new JLabel("Delete item");
        	j2.setBounds(50,450,100,30);
        	fr.add(j2);  	
        	 t4=new JTextField();
        	t4.setBounds(50,475,80,20);
        	fr.add(t4);
        	 t5=new JTextField();
        	t5.setBounds(150,475,80,20);
        	fr.add(t5);
        	 t6=new JTextField();
        	t6.setBounds(250,475,80,20);
        	fr.add(t6);
        	 btn4=new JButton("Delete item");
        	btn4.setBounds(350, 475, 180,30);
        	fr.add(btn4);
        	
        	
	        btn6 = new JButton("Confirm update");
			btn6.setBounds(35, 250,250, 50);
			fr.add(btn6);
			btn6.addActionListener(this);
			btn5.addActionListener(this);
			btn4.addActionListener(this);
			
			fr.setSize(600, 600);
			fr.setLayout(null);
			fr.setVisible(true);    
		}
		
		catch (Exception e2) {

			e2.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/emp","root","");
			Statement st = conn.createStatement();
			Object o=e.getSource();
			if(o==btn6)
			{
				int cnt=0;
				for(int i=0;i<j;i=i+2)
				{
					if(!test[i].getText().equals(""))
						st.execute("update gro set price='"+test[i].getText()+"' where name='"+nam[cnt]+"';");
					if(!test[i+1].getText().equals(""))
						st.execute("update gro set avai='"+test[i+1].getText()+"' where name='"+nam[cnt]+"';");
					cnt++;
					System.out.println(test[i].getText());
				}
				JOptionPane.showMessageDialog(this,"Updated Succefully","Success",JOptionPane.PLAIN_MESSAGE);
	    		fr.dispose();
			}
			
			if(o==btn5)
			{
				if(!t1.getText().equals(""))
				{
					int p=Integer.parseInt(t2.getText());
					int a=Integer.parseInt(t3.getText());
					st.execute("insert into gro values('"+t1.getText()+"',"+p+","+a+");");
					JOptionPane.showMessageDialog(this,"Added Successfully","Success",JOptionPane.PLAIN_MESSAGE);
					t1.setText("");
					t2.setText("");
					t3.setText("");
				}
			}
			if(o==btn4)
			{
				if(!t4.getText().equals(""))
				{
					st.execute("delete from gro where name='"+t4.getText()+"';");
					JOptionPane.showMessageDialog(this,"Deleted Successfully","Success",JOptionPane.PLAIN_MESSAGE);
					t4.setText("");
					t5.setText("");
					t6.setText("");
				}
			}
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
        
		
	}
}
