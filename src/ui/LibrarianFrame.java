package ui;

import daointerface.*;
import daoimplementation.*;
import vo.Order;

import javax.swing.*;

import client.DAOClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LibrarianFrame extends JFrame {
    JPanel panel1;
    //JPanel panel2;
    //JTextField textField;
    JButton showButton;
    JButton completeOrderButton;
    JTable orderTable;

    int selectedOrderRow = -1;

    DAO dao;
    List<Order> orderList;

    public LibrarianFrame(DAO dao) {
    	this.dao = dao;

        this.setLocation(100, 100);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel();



        showButton = new JButton("show orders");
        panel1.add(BorderLayout.NORTH, showButton);
        
        completeOrderButton = new JButton("completeOrder");
        panel1.add(BorderLayout.NORTH, completeOrderButton);
        

        completeOrderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                completeOrder();

            }
        });

        showButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	revalidate();
                orderTable = null; 
                initializeTable();

            }
        });


        this.add(panel1);
        this.setVisible(true);
    }

    //initializing table that returns Orders after pressing SEARCH button
    private void initializeTable() {
        if (orderTable != null)
            panel1.remove(orderTable);
        if (orderList != null) {
            orderList = null;
        }
        String[] columnNames = {"Order id", "Book id", "Student id"};

        try {
            orderList = dao.getUncompleted();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        String data[][] = new String[orderList.size()][columnNames.length];

        for (int i = 0; i < orderList.size(); i++) {
            data[i][0] = Integer.toString(orderList.get(i).getId());
            data[i][1] = Integer.toString(orderList.get(i).getBook().getId());
            data[i][2] = Integer.toString(orderList.get(i).getStudent());

        }
        orderTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderTable.setFillsViewportHeight(true);
        panel1.add(scrollPane);
        //panel1.add(orderTable);

        this.revalidate();
    }
    
    private void completeOrder() {
    	selectedOrderRow = orderTable.getSelectedRow();
        
        try {
            dao.completeOrder(orderList.get(selectedOrderRow));
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
