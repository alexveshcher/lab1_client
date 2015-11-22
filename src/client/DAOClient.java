package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import ui.LibrarianFrame;
import ui.StudentFrame;
import daointerface.DAO;

public class DAOClient {

	public static void main(String[] args) throws RemoteException, NotBoundException, SQLException {
		

			
			Registry reg = LocateRegistry.getRegistry("localhost",222);
			DAO dao = (DAO) reg.lookup("mydao"); 
			new LibrarianFrame(dao);
			//new StudentFrame(dao);
	}

}
