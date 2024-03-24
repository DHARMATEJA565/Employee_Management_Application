import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Display extends JFrame implements ActionListener {
	JLabel title, emp_id_label;
	
	JTextField emp_id ;
	
	JButton show;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dharma";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private Connection connection;
	
	public Display() {
		
		setTitle("VIEW EMPLOYEE RECORDS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(173,210,230));
		
		title = new JLabel("VIEW EMPLOYEE RECORDS");
		title.setBounds(50,30,400,30);
		title.setForeground(Color.black);
		title.setFont(new Font(Font.SERIF,Font.BOLD,28));
		title.setOpaque(false);
		add(title);
		
		emp_id_label = new JLabel("Enter Employee Id ");
		emp_id_label.setBounds(170,100,200,30);
		emp_id_label.setForeground(Color.black);
		emp_id_label.setFont(new Font(Font.SERIF,Font.BOLD,16));
		add(emp_id_label);
		
		emp_id = new JTextField();
		emp_id.setBounds(140,140,200,25);
		add(emp_id);
		
		show = new JButton(" SHOW ");
		show.setBounds(190,185,80,20);
		add(show);
		show.setBackground(Color.blue);
		show.setForeground(Color.white);
		
		show.addActionListener(this);
		
		setVisible(true);
		connectToDatabase();
		
	}

	public void actionPerformed(ActionEvent ae) {
	
		
		if(ae.getSource() == show) {
			String Emp_id = emp_id.getText();
			loadData(Emp_id);
			
			emp_id.setText("");
		}
		
	}

    private void loadData(String Emp_id) {
        try {
            String selectQuery = "SELECT * FROM Employee WHERE Emp_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, Emp_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int c =0 ;
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String id = resultSet.getString(2);
                String phone = resultSet.getString(3);
                String age = resultSet.getString(4);
                String department = resultSet.getString(5);
                String designation = resultSet.getString(6);
                String salary = resultSet.getString(7);
               
                JOptionPane.showMessageDialog(this, "Name :"+ name +"\nEmployee_Id :"+id + "\nAge : "+  age + "\nPhone : "+ phone +"\nDepartment : "+ department+ "\nDesignation : "+ designation + " \nSalary : "+ salary, "Success", JOptionPane.INFORMATION_MESSAGE);
                c++;
            }
            if (c == 0)  JOptionPane.showMessageDialog(this, "Failed to fetch employee data", "Error", JOptionPane.ERROR_MESSAGE);
            

        } catch (SQLException e) {
            e.printStackTrace();
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