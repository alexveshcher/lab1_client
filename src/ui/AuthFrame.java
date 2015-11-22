package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;



public class AuthFrame extends JFrame {
    JPanel panel1;
    ButtonGroup group ;
    JRadioButton librarianButton;
    JRadioButton studentButton;
    JTextField loginField;
    JButton loginButton;


    //int selectedBookRow = -1;

    public AuthFrame() {
        this.setLocation(100, 100);
        this.setSize(300, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel();
        group= new ButtonGroup();
        librarianButton = new JRadioButton("librarian");
        group.add(librarianButton);
        panel1.add(librarianButton);
        
        studentButton = new JRadioButton("student");
        group.add(studentButton);
        panel1.add(studentButton);

        loginField = new JTextField(20);
        loginField.setText("Login");
        panel1.add(loginField);

        loginButton = new JButton("login");
        panel1.add(BorderLayout.NORTH, loginButton);




        loginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginField.setText("");
            }
        });
        this.add(panel1);
        this.setVisible(true);
    }

 
    
    public static void main(String[] args){
    	new AuthFrame();
    }

}
