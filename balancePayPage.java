package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class balancePayPage extends JFrame {
    JLabel b;
    JLabel c;
    Font f;
    JButton b2;
    ImageIcon i1,i2;
    JLabel l1,l2;

    balancePayPage(String s) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,1000);
        setTitle("Balance");
        setLayout(null);
        setLocationRelativeTo(null);

        i1 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\moneyLogo.jpg");
        l1 = new JLabel(i1);
        l1.setBounds(0,5,800,800);
        add(l1);
        f = new Font("Comic Sans" , Font.BOLD,50);
        b =  new JLabel("Balance :");
//        b.setBounds(400,850,500,200);
        b.setFont(f);
//        add(b);
        c =  new JLabel("Balance :");
        c.setBounds(900,300,300,40);
        c.setFont(f);
        add(c);
        b2 = new JButton("Back");
        b2.setFont(f);
        b2.setBounds(900,500,200,40);
        add(b2);

        b2.addActionListener((ActionEvent e)->{
            welcomePagePay w = new welcomePagePay(s);
            dispose();
        });

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay", "root", "root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("Select * from account_details where mobile = '"+s+"'");
            if(rs.next()) {
                double d = rs.getInt("amount");
                String x = Double.toString(d);
                x = "Rs " + x;
                c.setText(x);
//                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid details!","Error",JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
