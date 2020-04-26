package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;



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
        
        conn.disconnect();

      } catch (MalformedURLException e) {

        e.printStackTrace();

      } catch (IOException e) {

        e.printStackTrace();

      }
		
		
		
		return inline;
	}

	@Override
	public int getFloorCount() throws RemoteException {
		// TODO Auto-generated method stub
		String cnt = "";
		int fcnt = 0;
		try {
			URL url = new URL("http://localhost:5000/getFloorCount");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn.getResponseCode());
	        }

	        Scanner sc = new Scanner(url.openStream());
	        while(sc.hasNext()) {
	            cnt += sc.nextLine();
	        }
	        
	        
	        JSONObject jb = new JSONObject(cnt); 
              fcnt = jb.getInt("count");
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	      } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return fcnt;
	}

	@Override
	public int getRoomCount(String i) throws RemoteException {
		// TODO Auto-generated method stub
		String cnt = "";
		int rcnt = 0;
		try {
			URL url = new URL("http://localhost:5000/getRoomsCount/" + i);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn.getResponseCode());
	        }

	        Scanner sc = new Scanner(url.openStream());
	        while(sc.hasNext()) {
	            cnt += sc.nextLine();
	        }
	        
	        
	        JSONObject jb = new JSONObject(cnt); 
              rcnt = jb.getInt("count");
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	      } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rcnt;
	}

	@Override
	public void AddRoom(String fnum) throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
		
			 HttpClient client = HttpClient.newHttpClient();
		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("http://localhost:5000/addRoom/" + fnum ))
		                .POST(HttpRequest.BodyPublishers.ofString(""))
		                .build();

		        HttpResponse<String> response = client.send(request,
		                HttpResponse.BodyHandlers.ofString());

		        System.out.println(response.body());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	@Override
	public void AddFloor() throws RemoteException{
		// TODO Auto-generated method stub
		try {
		
			 HttpClient client = HttpClient.newHttpClient();
		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("http://localhost:5000/addFloor"))
		                .POST(HttpRequest.BodyPublishers.ofString(""))
		                .build();

		        HttpResponse<String> response = client.send(request,
		                HttpResponse.BodyHandlers.ofString());

		        System.out.println(response.body());
			
	       
	        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getMaxRoomnum() throws RemoteException {
		String cnt = "";
		int fcnt = 0;
		try {
			URL url = new URL("http://localhost:5000/MaxRoomCount");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn.getResponseCode());
	        }

	        Scanner sc = new Scanner(url.openStream());
	        while(sc.hasNext()) {
	            cnt += sc.nextLine();
	        }
	        
	        
	        JSONObject jb = new JSONObject(cnt); 
              fcnt = jb.getInt("maximumRoom");
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	      } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return fcnt;
	}

	
	

}
