package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service  extends Remote{
	
	public String Getdata() throws RemoteException;
	public int getFloorCount() throws RemoteException;
	public int getRoomCount(String i) throws RemoteException;
	public void AddRoom(String fnum) throws RemoteException;
	public void AddFloor() throws RemoteException;
	public int getMaxRoomnum() throws RemoteException;
}
