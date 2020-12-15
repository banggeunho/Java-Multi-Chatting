package Chat.Sources.Client.Src;
import java.util.Date;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton Registration;
    private JLabel label;
    private JPanel contentPane;
    String userName;
    static JLabel lblNewLabel1 = new JLabel();
    /**
     * Launch the application.
     */
    public static void main(String args[])
    {
      /********* Open the Dialog *********/
      UserLogin Login= new UserLogin();   
      Login.setVisible(true);
        while(true) {
        Date date_now = new Date(System.currentTimeMillis()); // 
       lblNewLabel1.setText(date_now.toString());
    }
    }
    /**
     * Create the frame.
     */
    public UserLogin() {
        setBounds(450, 130, 600, 550);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255,255,204));

        JLabel lblNewLabel = new JLabel("Welcome to Chat");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewLabel.setBounds(160, 13, 273, 120);
        contentPane.add(lblNewLabel);
        

        lblNewLabel1.setForeground(Color.BLACK);
        lblNewLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        lblNewLabel1.setBounds(270, 440, 400, 100);
        contentPane.add(lblNewLabel1);
        

       

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(281, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(281, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(50, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(50, 286, 193, 52);
        contentPane.add(lblPassword);

        Registration = new JButton("Registration");
        Registration.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Registration.setBounds(100, 392, 200, 73);
        Registration.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
               UserRegistration regis = new UserRegistration();
               regis.setVisible(true);
           }
        });
        
        
        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(345, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                userName = textField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "");
                    
                    System.out.println(connection);

                   // RSA rsa = new RSA();
                   // password = rsa.decrypt(password, );
                    
                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select user_name, password from account where user_name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                        ChatClient mainFrame = new ChatClient(userName);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
        contentPane.add(Registration);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}