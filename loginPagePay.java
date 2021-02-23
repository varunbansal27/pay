package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginPagePay extends JFrame implements ActionListener {

    JLabel welcomeText;
    JLabel welcomeText2;
    JLabel userName;
    JLabel passWord;
    JTextField userText;
    JPasswordField passwordField;
    JButton submit,back;
    Font font1,font2;
    ImageIcon i1,i2,i3;
    JLabel i,j;

    loginPagePay() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.GRAY);
        setTitle("Login Page");

        setLayout(null);
        i1 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\loginImg.jpg");
        i = new JLabel(i1);
        i.setBounds(0,0,512,512);
        add(i);
        i2 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\pass.png");
        j = new JLabel(i2);
        j.setBounds(0,520,900,200);
        add(j);

        i3 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\back.jpg");
        back = new JButton("Back");
//        back.setIcon(i3);
        back.setBounds(750,230,100,38);
        add(back);

        font1 = new Font("Comic Sans" , Font.BOLD,50);
        font2 = new Font("Comic Sans" , Font.BOLD,25);
        welcomeText = new JLabel("WELCOME");
        welcomeText2 = new JLabel("Please login to continue...");
        welcomeText.setFont(font1);
//        add(welcomeText);
        welcomeText.setBounds(20,20,500,200);
        welcomeText2.setBounds(20,50,300,200);
        welcomeText2.setFont(font2);
//        add(welcomeText2);

        userName = new JLabel("Mobile :");
        userName.setBounds(550,20,200,200);
        userName.setFont(font2);
        add(userName);

        passWord = new JLabel("Password :");
        passWord.setBounds(550,60,200,200);
        passWord.setFont(font2);
        add(passWord);

        userText = new JTextField(20);
        userText.setBounds(750,115,200,20);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(750,155,200,20);
        add(userText);
        add(passwordField);

        submit = new JButton("Login");
        add(submit);
        submit.addActionListener(this);
        submit.setBounds(750,185,100,38);

        back.addActionListener((ActionEvent e) -> {
            java.awt.EventQueue.invokeLater(()-> {
                mainPage p = new mainPage();

            });
            dispose();
        });


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = userText.getText();
        String pass = passwordField.getText();
        if (userName.length() == 0) {
            JOptionPane.showMessageDialog(this,"UserName can't be empty!!","Error",JOptionPane.WARNING_MESSAGE);
            userName=userText.getText();
        }
        if (pass.length() == 0) {
            JOptionPane.showMessageDialog(this,"Password can't be empty!!","Error",JOptionPane.WARNING_MESSAGE);
            pass = passwordField.getText();
        }
        if(userName.length() != 0 && pass.length() != 0) {


            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay", "root", "root");
                Statement smt = con.createStatement();
                ResultSet rs = smt.executeQuery("Select * from account_details where mobile = '"+userName+"' and password = '"+pass+"'");
                if(rs.next()) {
                    welcomePagePay w = new welcomePagePay(userName);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,"Invalid details!","Error",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()-> {
            loginPagePay p = new loginPagePay();
        });
    }
}
