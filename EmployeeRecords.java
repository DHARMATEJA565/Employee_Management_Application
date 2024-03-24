import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeRecords {
	public static void main(String [] args) {
		
		Record obj = new Record();
	}
}

class Record extends JFrame implements ActionListener {
	
	JLabel title, name, rollno, address, phoneno;
	JButton create, remove, display, update ;

	public Record () {
		setTitle("EMPLOYEE RECORD APPLICATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(13,152,186));
		
		title = new JLabel("EMPLOYEE RECORD APPLICATION ");
		title.setBounds(45,30,500,30);
		title.setForeground(new Color(0,0,0));
		title.setFont(new Font(Font.SERIF,Font.BOLD,28));
		title.setOpaque(false);
		add(title);

		
		create = new JButton("Add an Employee Record");
		create.setBounds(180,100,200,30);
		create.setBackground(new Color(0,0,139));
		create.setForeground(Color.WHITE);
		add(create);
		create.addActionListener(this);
		
		display = new JButton("View an Employee Record ");
		display.setBounds(180,160,200,30);
		display.setBackground(new Color(0,0,139));
		display.setForeground(Color.WHITE);
		add(display);
		display.addActionListener(this);
		
		update = new JButton("Update an Employee Record ");
		update.setBounds(180,220,200,30);
		update.setBackground(new Color(0,0,139));
		update.setForeground(Color.WHITE);
		add(update);
		update.addActionListener(this);
		
		remove = new JButton("Delete an Employee Record");
		remove.setBounds(180,280,200,30);
		remove.setBackground(new Color(0,0,139));
		remove.setForeground(Color.WHITE);
		add(remove);
		remove.addActionListener(this);



		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == create) {
			new Create();
			
		}
		else if(ae.getSource() == remove) {
			new Remove();
		}
		else if(ae.getSource()== display){
			new Display();
			
		}else if(ae.getSource() == update){
		  	new Update();  
		}
	}
}