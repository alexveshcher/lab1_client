package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import daointerface.*;
import daoimplementation.*;
import vo.Book;
import vo.Order;

public class AuthFrame extends JFrame {
    JPanel panel1;
    JTextField textField;
    JButton searchButton;
    JButton orderButton;
    JTable bookTable;
    //JTable orderTable;

    int selectedBookRow = -1;

    DAO dao;
    List<Book> bookList;
    List<Order> orderList;

    public AuthFrame() {
    	this.dao = dao;
        this.setLocation(100, 100);
        this.setSize(300, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel();

        textField = new JTextField(20);
        textField.setText("Ð²Ð²ÐµÐ´Ñ–Ñ‚ÑŒ Ð±ÑƒÐ´ÑŒ-Ñ�ÐºÑ– Ð´Ð°Ð½Ñ– Ð¿Ñ€Ð¾ ÐºÐ½Ð¸Ð³Ñƒ");
        panel1.add(textField);

        searchButton = new JButton("searchBook");
        panel1.add(BorderLayout.NORTH, searchButton);

        orderButton = new JButton("order");
        panel1.add(BorderLayout.SOUTH, orderButton);


        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                initializeTable();
            }
        });

        orderButton.addActionListener(new ActionListener() {

            //action on clicking ORDER button
            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectedBookRow = bookTable.getSelectedRow();
                makeOrder();
            }
        });

        this.add(panel1);
        this.setVisible(true);
    }

    //initializing table that returns BOOKS after pressing SEARCH button
    private void initializeTable() {
        if (bookTable != null)
            panel1.remove(bookTable);
        if (bookList != null) {
            bookList = null;
        }
        String[] columnNames = {"Authors", "Title", "Year"};
        try {
            bookList = dao.searchBook(textField.getText());
        } catch (SQLException | RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String data[][] = new String[bookList.size()][columnNames.length];

        for (int i = 0; i < bookList.size(); i++) {
            data[i][0] = bookList.get(i).getAuthors();
            data[i][1] = bookList.get(i).getTitle();
            data[i][2] = Integer.toString(bookList.get(i).getYear());

        }
        bookTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        bookTable.setFillsViewportHeight(true);
        panel1.add(scrollPane);
        this.revalidate();
    }

    private void makeOrder() {

        Order ord = new Order();
        ord.setBook(bookList.get(selectedBookRow));
        try {
            dao.makeOrder(ord);
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
    	new AuthFrame();
    }

}
