package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service  extends Remote{
	
	public String Getdata() throws RemoteException;

}
