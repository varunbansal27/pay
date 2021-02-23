package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class welcomePagePay extends JFrame {
    JButton b1pay;
    JButton b2balance;
    JButton b3trans;
    JButton b4logout;
    Font f;
    ImageIcon i1;
    JLabel l1,l2;

    welcomePagePay(String s) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setTitle("Welcome");
        setLayout(null);
        f = new Font("Comic Sans" , Font.BOLD,25);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.lightGray);

        i1 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\moneyTransfer.png");
        l1 = new JLabel(i1);
        l1.setBounds(0,5,840,811);
        add(l1);

        b1pay = new JButton("Transfer Money");
        b1pay.setFont(f);
        b1pay.setBounds(850,155,300,30);
        add(b1pay);

        b2balance = new JButton("Balance");
        b2balance.setFont(f);
        b2balance.setBounds(850,305,300,30);
        add(b2balance);

        b3trans = new JButton("Recent Transactions");
        b3trans.setFont(f);
        b3trans.setBounds(850,455,300,30);
        add(b3trans);

        b4logout = new JButton("Log Out");
        b4logout.setFont(f);
        b4logout.setBounds(850,605,300,30);
        add(b4logout);

        l2 = new JLabel();
        l2.setFont(f);
        l2.setBounds(845,5,300,40);
        add(l2);

        b1pay.addActionListener((ActionEvent e)->{
            java.awt.EventQueue.invokeLater(()->{
                sendPayPage rf=new sendPayPage(s);

            });
            dispose();
        });
        b2balance.addActionListener((ActionEvent e)->{
            java.awt.EventQueue.invokeLater(()->{
                balancePayPage rf=new balancePayPage(s);
            });
            dispose();
        });
        b3trans.addActionListener((ActionEvent e)->{
            java.awt.EventQueue.invokeLater(()->{
                transactionsPagePay rf=new transactionsPagePay(s);
            });
            dispose();
        });
        b4logout.addActionListener((ActionEvent e)->{
            java.awt.EventQueue.invokeLater(()->{
                mainPage rf=new mainPage();
            });
            dispose();
        });
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay", "root", "root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("Select * from account_details where mobile = '"+s+"'");
            if(rs.next()) {
                String x = rs.getString("name");
                x = "Hello " + x + " ...";
                l2.setText(x);

            } else {
                JOptionPane.showMessageDialog(this,"Invalid details!","Error",JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()->{
            welcomePagePay rf=new welcomePagePay("9878554311");
        });
    }
}
