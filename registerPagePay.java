package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class registerPagePay extends JFrame  {
    JLabel title,name,mobile,pass,amount;
    JTextField nameTF,mobileTF,passTF,amountTF;
    JButton submit,back;
    Font font1,font2;
    ImageIcon i1,i2;
    JLabel l1,l2;


    registerPagePay() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,900);
        setTitle("Registration");
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);
        setLocationRelativeTo(null);

        i1 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\login5.png");
        l1 = new JLabel(i1);
        l1.setBounds(0,5,512,512);
        add(l1);
        i2 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\strong4.png");
        l2 = new JLabel(i2);
        l2.setBounds(50,520,800,250);
        add(l2);

        font1 = new Font("Verdana",Font.BOLD+Font.ITALIC,40);
        font2 = new Font("Verdana",Font.BOLD,25);

        title = new JLabel("REGISTRATION",JLabel.CENTER);
        title.setFont(font1);
        title.setBackground(Color.pink);
        title.setOpaque(true);
        title.setBounds(10,20,800,40);
//        add(title);

        name = new JLabel("Name :");
        name.setFont(font2);
        name.setBounds(550,100,100,30);
        add(name);

        pass = new JLabel("Password :");
        pass.setFont(font2);
        pass.setBounds(550,145,200,30);
        add(pass);

        mobile = new JLabel("Mobile No. :");
        mobile.setFont(font2);
        mobile.setBounds(550,190,200,30);

        add(mobile);

        amount = new JLabel("Amount :");
        amount.setFont(font2);
        amount.setBounds(550,235,200,30);
        add(amount);

        nameTF = new JTextField(20);
        nameTF.setBounds(750,105,200,30);
        add(nameTF);

        passTF = new JPasswordField(20);
        passTF.setBounds(750,150,200,30);
        add(passTF);

        mobileTF = new JTextField(20);
        mobileTF.setBounds(750,195,200,30);
        mobileTF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = mobileTF.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8) {
                    mobileTF.setEditable(true);
                } else {
                    mobileTF.setEditable(false);
                }
            }
        });
        add(mobileTF);

        amountTF = new JTextField(20);
        amountTF.setBounds(750,240,200,30);
        amountTF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = amountTF.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8 || ke.getKeyChar() == '.') {
                    amountTF.setEditable(true);
                } else {
                    amountTF.setEditable(false);
                }
            }
        });
        add(amountTF);
        back = new JButton("Back");
//        back.setIcon(i3);
        back.setBounds(750,350,150,40);
        back.setFont(font2);
        add(back);
        back.addActionListener((ActionEvent e) -> {
            java.awt.EventQueue.invokeLater(()-> {
                mainPage p = new mainPage();

            });
            dispose();
        });

        submit = new JButton("Submit");
        submit.setFont(font2);
        submit.setBounds(750,300,150,40);
        add(submit);
        submit.addActionListener((ActionEvent e )-> {
            String u = nameTF.getText();
            String p = passTF.getText();
            String m = mobileTF.getText();
            String a = amountTF.getText();
            double am = 0.00;


            if(u.length() == 0 || p.length() == 0 || m.length() == 0 || a.length() == 0) {
                JOptionPane.showMessageDialog(this,"Something is missing!!","Warning",JOptionPane.WARNING_MESSAGE);
            } else {
                try{
                    am = Double.parseDouble(a);



                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(this,"Invalid amount entered!!","Warning",JOptionPane.WARNING_MESSAGE);

                }
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pay","root","root");
                    Statement smt = con.createStatement();
                    ResultSet rs = smt.executeQuery("SELECT * FROM account_details WHERE mobile = '"+ m +"'");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this,"Mobile number also registered!!","Warning",JOptionPane.WARNING_MESSAGE);
                    } else {
//                    smt.executeUpdate("insert into info (user_id,password,gender,address,city,courses) values('"+u+"','" +
//                            p+"','"+g+"','"+a+"','"+c+"','"+courses+"'");
                        smt.executeUpdate("insert into account_details (name,password,mobile,amount) values('"+u+"','"+p+"','"+m+"','"+a+"')");
                        JOptionPane.showMessageDialog(this,"Registration Done!!","Message",JOptionPane.INFORMATION_MESSAGE);
                        nameTF.setText("");
                        passTF.setText("");
                        mobileTF.setText("");
                        amountTF.setText("");
                    }
                    rs.close();
                    smt.close();
                    con.close();
                } catch (SQLException s) {

                }
            }

        });

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()->{
            registerPagePay rf=new registerPagePay();
        });

    }

}
