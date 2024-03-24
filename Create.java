import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Create extends JFrame implements ActionListener {
	
	JTextField name,emp_id,phoneno, department, display,age,designation,salary;
	JLabel title,name_label,emp_id_label,phoneno_label,department_label,age_label,designaion_label,salary_label;
	JButton create1,reset;
	DefaultTableModel tableModel;
   	private static final String DB_URL = "jdbc:mysql://localhost:3306/dharma"; //dharma is name of the database used
    	private static final String DB_USER = "root";// username
    	private static final String DB_PASSWORD = "root";// password
    	private Connection connection;

	
	public Create() {
		
		setTitle("ADD EMPLOYEE RECORDS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550,550);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(150,131,230));
	
		title = new JLabel("ADD EMPLOYEE RECORDS");
		title.setBounds(80,30,380,30);
		title.setForeground(Color.white);
		title.setFont(new Font(Font.SERIF,Font.BOLD,28));
		title.setOpaque(true);
		title.setBackground(new Color(44,16,106) );
		add(title);
		
		name_label = new JLabel (" Name ");
		name_label.setBounds(100,120,100,20);
		name_label.setForeground(Color.white);
		name_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(name_label);
		
		
		name = new JTextField();
		name.setBounds(220,120,150,20);
		add(name);
		
		emp_id_label = new JLabel("Employee ID ");
		emp_id_label.setBounds(100,170,120,20);
		emp_id_label.setForeground(Color.white);
		emp_id_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(emp_id_label);
		
		emp_id = new JTextField();
		emp_id.setBounds(220,170,150,20);
		add(emp_id);
		
		age_label = new JLabel("Age ");
		age_label.setBounds(100,220,80,20);
		age_label.setForeground(Color.white);
		age_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(age_label);
		
		age = new JTextField();
		age.setBounds(220,220,150,20);
		add(age);
		
		phoneno_label = new JLabel("PhoneNumber");
		phoneno_label.setBounds(100,270,120,25);
		phoneno_label.setForeground(Color.white);
		phoneno_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(phoneno_label);
		
		phoneno = new JTextField();
		phoneno.setBounds(220,270,150,20);
		add(phoneno);
		
		department_label = new JLabel("Department");
		department_label.setBounds(100,320,120,20);
		department_label.setForeground(Color.white);
		department_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(department_label);

		department= new JTextField();
		department.setBounds(220,320,150,20);
		add(department);
		
		designaion_label= new JLabel("Designation");
		designaion_label.setBounds(100,370,120,20);
		designaion_label.setForeground(Color.white);
		designaion_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(designaion_label);
		
		designation= new JTextField();
		designation.setBounds(220,370,150,20);
		add(designation);
		
		salary_label = new JLabel("Salary ");
		salary_label.setBounds(100,420,80,20);
		salary_label.setForeground(Color.white);
		salary_label.setFont(new Font(Font.SERIF,Font.BOLD,18));
		add(salary_label);
		
		salary = new JTextField();
		salary.setBounds(220,420,150,20);
		add(salary);
		
		create1 = new JButton("ADD");
		create1.setBounds(120,470 ,80,25);
		add(create1);
		
		reset = new JButton("RESET");
		reset.setBounds(260,470,100,25);
		add(reset);
		
		reset.setBackground(new Color(44,16,106));
		reset.setForeground(Color.WHITE);
		
		create1.setBackground(new Color(44,16,106));
		create1.setForeground(Color.WHITE);
		
		create1.addActionListener(this);
		reset.addActionListener(this);
		setVisible(true);
		
		connectToDatabase();
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == create1) {
            String name1 = name.getText();
            String id = emp_id.getText();
            String phone = phoneno.getText();
            String dept = department.getText();
            String Designation = designation.getText();
            String Salary = salary.getText();
            String Age = age.getText();
  

            if (name1.isEmpty() || phone.isEmpty() || dept.isEmpty() || Designation.isEmpty() || Salary.isEmpty() || id.isEmpty() || Age.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }else if (!isValidPhone(phone)) {
                JOptionPane.showMessageDialog(this, "Invalid student phone. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidId(id)) {
                JOptionPane.showMessageDialog(this, "Invalid student ID. It should be a alphanumeric", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidPhone(Age)) {
                JOptionPane.showMessageDialog(this, "Invalid age. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }else if (!isValidPhone(Salary)) {
                JOptionPane.showMessageDialog(this, "Invalid Salary. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                name.setText("");
                emp_id.setText("");
                phoneno.setText("");
                department.setText("");
                age.setText("");
                designation.setText("");
                salary.setText("");
                
                insertData(name1, id, phone, Age, dept, Designation, Salary);
            }
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
    private boolean isValidId(String id) {
        return id.matches("^[A-Za-z0-9]+$");
    }
    private boolean isValidPhone(String contact) {
        return contact.matches("^[0-9]+$");
    }
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertData(String Name, String id, String phone, String Age, String dept, String Designation, String Salary) {
        String insertQuery = "INSERT INTO Employee (Name, Emp_id, Phone_No, Age , Department, Designation, Salary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, Age);
            preparedStatement.setString(5, dept);
            preparedStatement.setString(6, Designation);
            preparedStatement.setString(7, Salary);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert employee data", "Error", JOptionPane.ERROR_MESSAGE);
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