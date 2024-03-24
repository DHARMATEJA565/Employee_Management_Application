import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class Update extends JFrame implements ActionListener {
	
	JTextField name,emp_id,phoneno, department, display,age,designation,salary;
	JLabel title,name_label,emp_id_label,phoneno_label,department_label,age_label,designaion_label,salary_label, text;
	JButton update,reset;
	DefaultTableModel tableModel;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dharma";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private Connection connection;

	
	public Update() {
		
		setTitle("UPDATE EMPLOYEE RECORDS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550,600);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(196,164,132));
	
		title = new JLabel("UPDATE EMPLOYEE DETAILS");
		title.setBounds(80,30,500,30);
		title.setForeground(Color.black);
		title.setFont(new Font(Font.SERIF,Font.BOLD,28));
		title.setOpaque(false);
		add(title);
		
		emp_id_label = new JLabel("Employee ID ");
		emp_id_label.setBounds(100,100,100,20);
		add(emp_id_label);
		
		emp_id = new JTextField();
		emp_id.setBounds(220,100,150,20);
		add(emp_id);
		
		text = new JLabel ("Enter the details that are to be updated (Leave the remaning)");
		text.setBounds(80, 150,500,20);
		text.setForeground(Color.black);
		text.setFont(new Font(Font.SERIF,Font.BOLD,14));
		add(text);
		
		name_label = new JLabel (" Name ");
		name_label.setBounds(100,200,100,20);
		add(name_label);

		name = new JTextField();
		name.setBounds(220,200,150,20);
		add(name);
	
		age_label = new JLabel("Age ");
		age_label.setBounds(100,250,80,20);
		add(age_label);
		
		age = new JTextField();
		age.setBounds(220,250,150,20);
		add(age);
		
		phoneno_label = new JLabel("Phone Number ");
		phoneno_label.setBounds(100,300,100,25);
		add(phoneno_label);
		
		phoneno = new JTextField();
		phoneno.setBounds(220,300,150,20);
		add(phoneno);
		
		department_label = new JLabel("Department ");
		department_label.setBounds(100,350,80,20);
		add(department_label);

		department= new JTextField();
		department.setBounds(220,350,150,20);
		add(department);
		
		designaion_label= new JLabel("Designation ");
		designaion_label.setBounds(100,400,80,20);
		add(designaion_label);
		
		designation= new JTextField();
		designation.setBounds(220,400,150,20);
		add(designation);
		
		salary_label = new JLabel("Salary ");
		salary_label.setBounds(100,450,80,20);
		add(salary_label);
		
		salary = new JTextField();
		salary.setBounds(220,450,150,20);
		add(salary);
		
		update = new JButton("UPDATE");
		update.setBounds(120,500 ,80,30);
		add(update);
		
		reset = new JButton("RESET");
		reset.setBounds(260,500,100,30);
		add(reset);
		
		reset.setBackground(new Color(133,70,30));
		reset.setForeground(Color.WHITE);
		
		update.setBackground(new Color(133,70,30));
		update.setForeground(Color.WHITE);
		
		update.addActionListener(this);
		reset.addActionListener(this);
		setVisible(true);
		
        name.setText("");
        emp_id.setText("");
        phoneno.setText("");
        department.setText("");
        age.setText("");
        designation.setText("");
        salary.setText("");
		
		connectToDatabase();
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == update) {
			String id = emp_id.getText();
			updateData(id);
        }

        if (ae.getSource() == reset) {
            name.setText("");
            emp_id.setText("");
            phoneno.setText("");
            department.setText("");
            age.setText("");
            designation.setText("");
            salary.setText("");
        }
	}
	
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData(String id) {
    	String Query= "SELECT * FROM Employee WHERE Emp_id = ?";
    	String updateQuery = "Update Employee set Name = ?, Phone_No = ? , Age = ?, Department= ?, Designation = ? , Salary = ? WHERE Emp_id = ?";
         
    	String Name,Phone,Age,Department,Designation,Salary;
    	String Name1,Phone1,Age1,Department1,Designation1,Salary1;
    	int rowsAffected = 0;
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
            
            Name = resultSet.getString(1);
            Phone = resultSet.getString(3);
            Age = resultSet.getString(4);
            Department = resultSet.getString(5);
            Designation = resultSet.getString(6);
            Salary = resultSet.getString(7);
            
            Name1 = name.getText();
            Phone1 = phoneno.getText();
     		Department1 = department.getText();
     		Designation1 = designation.getText();
        	Salary1 = salary.getText();
        	Age1 = age.getText();
        
        	String NAN = "";
  
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
            
            if (Name1.equals(NAN)) preparedStatement1.setString(1, Name);
            else preparedStatement1.setString(1, Name1);
            if (Phone1.equals(NAN)) preparedStatement1.setString(2,Phone);
            else preparedStatement1.setString(2,Phone1);
            if (Age1.equals(NAN)) preparedStatement1.setString(3, Age);
            else preparedStatement1.setString(3, Age1);
            if (Department1.equals(NAN)) preparedStatement1.setString(4, Department);
            else preparedStatement1.setString(4, Department1);
            if (Designation1.equals(NAN)) preparedStatement1.setString(5, Designation);
            else preparedStatement1.setString(5, Designation1);
            if ( Salary1.equals(NAN))preparedStatement1.setString(6, Salary);
            else preparedStatement1.setString(6, Salary1);
            preparedStatement1.setString(7, id);
            preparedStatement1.executeUpdate();
            rowsAffected = 1;
            }


            
            if (rowsAffected ==1) {
                JOptionPane.showMessageDialog(this, "Data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert student data/nPlease Enter Valid Employee Id ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void closeDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}