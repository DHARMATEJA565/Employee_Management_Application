import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Remove extends JFrame implements ActionListener {
	JLabel title, emp_id_label;
	
	JTextField emp_id ;
	
	JButton remove;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/dharma";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private Connection connection;

	public Remove() {
		
		setTitle("DELETE AN EMPLOYEE RECORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(60,210,60));
		
		title = new JLabel("DELETE AN EMPLOYEE RECORD");
		title.setBounds(60,30,400,30);
		title.setForeground(new Color(1,50,32));
		title.setFont(new Font(Font.SERIF,Font.BOLD,24));
		title.setOpaque(false);
		add(title);
		
		emp_id_label = new JLabel("Enter Employee Id ");
		emp_id_label.setBounds(170,100,200,30);
		emp_id_label.setForeground(new Color(1,50,32));
		emp_id_label.setFont(new Font(Font.SERIF,Font.BOLD,16));
		add(emp_id_label);
		
		emp_id = new JTextField();
		emp_id.setBounds(140,140,200,25);
		add(emp_id);
		
		remove = new JButton("Remove");
		remove.setBounds(190,185,80,20);
		add(remove);
		remove.setBackground(new Color(1,50,32));
		remove.setForeground(Color.WHITE);
		
		remove.addActionListener(this);
		connectToDatabase();
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == remove) {
        	String id = emp_id.getText();
                deleteData(id);
            emp_id.setText("");
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
    private void deleteData(String emp_id) {
        String deleteQuery = "DELETE FROM Employee WHERE Emp_id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, emp_id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected == 1) {
                JOptionPane.showMessageDialog(this, " Employee data deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete employee data", "Error", JOptionPane.ERROR_MESSAGE);
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