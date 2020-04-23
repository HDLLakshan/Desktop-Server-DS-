package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;



public class Server extends UnicastRemoteObject implements Service {

	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("java.security.policy", "file:allowall.policy");
		
		try {
			Server svr = new Server();
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("LevelService", svr);
			System.out.println("Service Strated........");
		}catch(RemoteException re){
            System.err.println(re.getMessage());
        }
        catch(AlreadyBoundException abe){
            System.err.println(abe.getMessage());
        }

	}

	@Override
	public String Getdata() throws RemoteException {
		// TODO Auto-generated method stub
		String inline = "";
		try {
		URL url = new URL("http://localhost:5000/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()) {
            inline += sc.nextLine();
        }
        System.out.println("JSon");
        System.out.println(inline);
        
        

        conn.disconnect();

      } catch (MalformedURLException e) {

        e.printStackTrace();

      } catch (IOException e) {

        e.printStackTrace();

      }
		
		
		
		return inline;
	}

}
