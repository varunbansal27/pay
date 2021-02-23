package PayApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mainPage extends JFrame{
    JFrame j;
    ImageIcon i,i2,i3;
    JButton tf1;
    JButton tf2;
    Font f;
    JLabel l;
    mainPage() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        setTitle("Paying Page");
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white  );

//        pack();


        i = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\PayLogo3.jpg");
        i2 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\login4.png");
        i3 = new ImageIcon("C:\\Users\\varun\\Desktop\\CDAC_assignments\\pay_img\\register2.jpg");
        l = new JLabel(i);
        l.setBounds(100,0,800,400);
        add(l);
        f = new Font("Comic Sans" , Font.BOLD,25);
        tf2 = new JButton();
        tf2.setIcon(i2);
        tf2.setSize(256,140);
        tf2.setFont(f);
        tf2.setBounds(100,450,256,130);
        add(tf2);

        tf1 = new JButton();
        tf1.setIcon(i3);
        tf1.setBounds(380,450,445,130);
        tf1.setFont(f);
        add(tf1);

        tf1.addActionListener((ActionEvent e)->{
                java.awt.EventQueue.invokeLater(()-> {
                    registerPagePay p = new registerPagePay();
                });
                    dispose();
                }
        );
        tf2.addActionListener((ActionEvent e)->{
            java.awt.EventQueue.invokeLater(()-> {
                loginPagePay p = new loginPagePay();
            });
                    dispose();
                }
        );
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()-> {
            mainPage p = new mainPage();
        });
    }
}
