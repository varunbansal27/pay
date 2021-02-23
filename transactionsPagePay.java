package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class transactionsPagePay extends JFrame {
    JTable t1,t2;
//    String data1[][];
//    String data2[][];
    Font f;
    JButton back;

    transactionsPagePay(String s) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setTitle("Recent Transactions");
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.lightGray);

        setLayout(null);
        f = new Font("Comic Sans" , Font.BOLD,25);
        t1 = new JTable();
//        JTable t3 = new JTable();
        JLabel l1,l2;
        l1 = new JLabel("Send To");
        l1.setFont(f);
        l1.setBounds(20,20,150,30);
        add(l1);
        l2 = new JLabel("Amount");
        l2.setFont(f);
        l2.setBounds(200,20,150,30);
        add(l2);
        JLabel l3,l4;
        l3 = new JLabel("Received From");
        l3.setFont(f);
        l3.setBounds(350,20,180,30);
        add(l3);
        l4 = new JLabel("Amount");
        l4.setFont(f);
        l4.setBounds(550,20,150,30);
        add(l4);

        back = new JButton("Back");
        back.setBounds(400,700,100,38);
        back.setFont(f);
        back.addActionListener((ActionEvent e ) -> {
            welcomePagePay w = new welcomePagePay(s);
            dispose();
        });
        add(back);


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay", "root", "root");
            Statement smt = con.createStatement();
            Statement smt2 = con.createStatement();
            ResultSet rs = smt.executeQuery("Select * from trans where first = '"+s+"'");
            int i = 20;

            while(rs.next()) {
                String s1 =  rs.getString("second");
                double d2 = rs.getFloat("amount");
                String s2 = Double.toString(d2);
                s2 = "- " + s2;
                JLabel l = new JLabel(s1);
                l.setBounds(20,25+i,150,30);
                l.setFont(f);
                add(l);

                JLabel m = new JLabel(s2);
                m.setBounds(200,25+i,150,30);
                m.setFont(f);
                add(m);
                i += 20;

            }
            ResultSet rs2 = smt2.executeQuery("Select * from trans where second = '"+s+"'");
            int i2 = 20;

            while(rs2.next()) {
                String s1 =  rs2.getString("first");
                double d2 = rs2.getFloat("amount");
                String s2 = Double.toString(d2);
                s2 = "+ " + s2;
                JLabel l = new JLabel(s1);
                l.setBounds(350,25+i2,150,30);
                l.setFont(f);
                add(l);

                JLabel m = new JLabel(s2);
                m.setBounds(550,25+i2,150,30);
                m.setFont(f);
                add(m);
                i2 += 20;

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()-> {
            transactionsPagePay p = new transactionsPagePay("9878554311");
        });
    }
}
