package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class sendPayPage extends JFrame {
    JLabel welcomeText;
    JLabel welcomeText2;
    JLabel payTo;
    JLabel amount;
    JTextField payT;
    JTextField amountP;
    JButton submit;
    JButton back;
    Font font1,font2;
    ImageIcon i1;
    JLabel l1;

    sendPayPage(String s) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setTitle("Paying Page");
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.lightGray);

        i1 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\transfer2.jpg");
        l1 = new JLabel(i1);
        l1.setBounds(0,5,500,461);
        add(l1);

        font1 = new Font("Comic Sans" , Font.BOLD,50);
        font2 = new Font("Comic Sans" , Font.BOLD,25);
        welcomeText = new JLabel("Waiting...");
        welcomeText2 = new JLabel("Enter valid values...");
        welcomeText.setFont(font1);
//        add(welcomeText);
        welcomeText.setBounds(20,20,500,200);
        welcomeText2.setBounds(20,60,500,200);
        welcomeText2.setFont(font2);
//        add(welcomeText2);

        payTo = new JLabel("Mobile :");
        payTo.setBounds(550,20,500,200);
        payTo.setFont(font2);
        amount = new JLabel("Amount :");
        amount.setBounds(550,60,500,200);
        amount.setFont(font2);
        add(payTo);
        add(amount);

        payT = new JTextField(20);
        payT.setBounds(760,115,200,20);
        payT.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = payT.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8 ) {
                    payT.setEditable(true);
                } else {
                    payT.setEditable(false);
                }
            }
        });
        amountP = new JTextField(20);
        amountP.setBounds(760,155,200,20);
        amountP.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountP.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8 || ke.getKeyChar() == '.') {
                    amountP.setEditable(true);
                } else {
                    amountP.setEditable(false);
                }
            }
        });
        add(payT);
        add(amountP);

        submit = new JButton("Send");
        add(submit);
        submit.setBounds(760,185,100,38);
        submit.addActionListener((ActionEvent e)-> {
            String payTo = payT.getText();
            String am = amountP.getText();
            double d = 0.00;
            if(payTo.length() == 0 || am.length() == 0) {
                JOptionPane.showMessageDialog(this,"Please fill details!!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else{

                try {
                    d = Double.parseDouble(am);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(this,"Invalid amount entered!!","Warning",JOptionPane.WARNING_MESSAGE);
                }

                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay","root","root");
                    Statement smt = con.createStatement();
                    Statement smt2 = con.createStatement();
                    ResultSet rs = smt.executeQuery("SELECT * FROM account_details WHERE mobile = '"+ payTo +"'");
                    ResultSet rs2 = smt2.executeQuery("SELECT * FROM account_details WHERE mobile = '"+ s +"'");
                    if(rs.next()){
                        double y = rs.getInt("amount");
//                        rs.close();
                        System.out.println("1");
//                        double x = 0;
//                        try {
//                            wait(1000);
//                        } catch (InterruptedException interruptedException) {
//                            interruptedException.printStackTrace();
//                        }
                        double x = 0.00;
                        if(rs2.next())
                            x = rs2.getInt("amount");
                        System.out.println("2");
                        if(d > x || d <= 0) {
                            JOptionPane.showMessageDialog(this,"Balance is low!!","Warning",JOptionPane.WARNING_MESSAGE);
                        } else {
                            smt.executeUpdate("update account_details set amount = " + (x - d) + "where mobile =  '" + s + "'");
                            smt.executeUpdate("update account_details set amount = " + (y + d) + "where mobile =  '" + payTo + "'");
                            smt.executeUpdate("insert into trans (first,second,amount) values('"+s+"','"+payTo+"','"+am+"')");
                            JOptionPane.showMessageDialog(this,"Done!","info",JOptionPane.INFORMATION_MESSAGE);


                        }
                        rs2.close();
                    } else {
                        JOptionPane.showMessageDialog(this,"Invalid Mobile Number!!","Warning",JOptionPane.WARNING_MESSAGE);
//                    smt.executeUpdate("insert into info (user_id,password,gender,address,city,courses) values('"+u+"','" +
//                            p+"','"+g+"','"+a+"','"+c+"','"+courses+"'");
//                        smt.executeUpdate("insert into account_details (name,password,mobile,amount) values('"+u+"','"+p+"','"+m+"','"+a+"')");
//                        JOptionPane.showMessageDialog(this,"Registration Done!!","Message",JOptionPane.INFORMATION_MESSAGE);
//                        nameTF.setText("");
//                        passTF.setText("");
//                        mobileTF.setText("");
//                        amountTF.setText("");
                    }
//                    rs.close();
                    smt.close();
                    con.close();
                } catch (SQLException sqlEX) {
                    System.out.println(sqlEX);
                }
            }



        });


        back = new JButton("Back");
        add(back);
        back.setBounds(760,240,100,38);
        back.addActionListener((ActionEvent e ) -> {
            welcomePagePay w = new welcomePagePay(s);
            dispose();
        });

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()-> {
            sendPayPage p = new sendPayPage("9878554316");
        });
    }
}
