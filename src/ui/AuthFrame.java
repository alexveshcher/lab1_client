package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




import javax.swing.*;

import daointerface.DAO;



public class AuthFrame extends JFrame {
    JPanel panel1;
    ButtonGroup group ;
    JRadioButton librarianButton;
    JRadioButton studentButton;
    JTextField loginField;
    JButton loginButton;
    
    DAO dao;
    
    public static int studentID=-1;


    //int selectedBookRow = -1;

    public AuthFrame(DAO dao) {
    	this.dao = dao;
        this.setLocation(100, 100);
        this.setSize(300, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel();
        group= new ButtonGroup();
        librarianButton = new JRadioButton("librarian");
        group.add(librarianButton);
        panel1.add(librarianButton);
        
        studentButton = new JRadioButton("student",true);
        group.add(studentButton);
        panel1.add(studentButton);
        

        loginField = new JTextField(20);
        loginField.setText("type your login");
        //loginField.setEnabled(false);
        panel1.add(loginField);

        loginButton = new JButton("login");
        panel1.add(BorderLayout.NORTH, loginButton);




        loginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginField.setText("");
            }
            public void mousePressed(MouseEvent e) {
                loginField.setText("");
           }
        });
        
        loginButton.addActionListener(new ActionListener() {

            //action on clicking LOGIN button
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	
                if(studentButton.isSelected()==true){
                	studentID = Integer.parseInt(loginField.getText());
                	new StudentFrame(dao);
                }
                else new LibrarianFrame(dao);
               dispose();
            }
        });
        
        this.add(panel1);
        this.setVisible(true);
    }

 
    

}
