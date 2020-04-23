package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONException;

public class ClientView extends JFrame {

	private JPanel contentPane;
	static int delay = 0; // delay for 0 sec. 
	static int period = 4000; // repeat every 10 sec. 
	static Timer timer = new Timer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView();
					frame.setVisible(true);
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientView() {
		
		
		
		timer.scheduleAtFixedRate(new TimerTask() 
		{ 
		    public void run() 
		    { 
		        //Call function
		    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(200, 200, 925, 532);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
		   
		int p = 185;
		for(int v=0; v<6; v++) {
		JLabel lblNewLabel_1 = new JLabel("Room " + (v+1));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(p, 22, 81, 20);
		contentPane.add(lblNewLabel_1);
		p= p + 112;
		}
		
		
		System.setProperty("java.security.policy", "file:allowall.policy");
	        
	        Service service = null;
		
	        try {
	        	
	            service = (Service) Naming.lookup("//localhost/LevelService");
	            
	            String s = service.Getdata();

	           System.out.println(s);
	           
	           JSONArray jsar = new JSONArray(s);
	          
	           int y = 52;
	           
	           for(int j=0; j<jsar.length(); j++) {
	   		
	        	   JLabel jmain = new JLabel("New label");
	        	   jmain.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        	   jmain.setBorder(new LineBorder(new Color(0, 0, 0)));
	        	   jmain.setBounds(25, y, 132, 72);
	        	   contentPane.add(jmain);
	       		   jmain.setText("FLOOR    " +  String.valueOf(jsar.getJSONObject(j).getInt("FloorNo")));
	       		   int x = 167;
	        	   
	       		   for(int i=0; i<jsar.getJSONObject(j).getJSONArray("Rooms").length(); i++) {
	   			       
	        		   JLabel jlbx = new JLabel("");
	        		   jlbx.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        		   jlbx.setBorder(new LineBorder(new Color(0, 0, 0)));
	        	       jlbx.setBounds(x, y, 102, 32);
	        	       contentPane.add(jlbx);
	   			
	        		    JLabel jlby = new JLabel("");
	        		    jlby.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        		    jlby.setBorder(new LineBorder(new Color(0, 0, 0)));
	        		    jlby.setBounds(x, y+42, 102, 32);
	        		    contentPane.add(jlby);
	        		     			
	   			        int sl = jsar.getJSONObject(j).getJSONArray("Rooms").getJSONObject(i).getInt("SmokeLevel");
	   			        int cl = jsar.getJSONObject(j).getJSONArray("Rooms").getJSONObject(i).getInt("CO2Level");
	   			
	   			     jlbx.setText("Smoke Level:" + String.valueOf(sl));
	   			     jlby.setText("Co2 Level  : " +String.valueOf(cl));
	   			     
	   			     boolean active = jsar.getJSONObject(j).getJSONArray("Rooms").getJSONObject(i).getBoolean("Active");
	   			     
	   			     if(!active) {
	   			    	jlbx.setText(null);
	   			    	jlby.setText(null);
	   			     }
	   			     
	   			  if(sl >= 5) jlbx.setForeground(Color.RED); else jlbx.setForeground(Color.GREEN);
	   			if(cl >= 5) jlby.setForeground(Color.RED); else jlby.setForeground(Color.GREEN);
	   			  
	   			     
	   			     
	   			  x = x +112;
	   			     
	   		}
	       		   
	       		   
	           y= y+82;
	           }
	         } catch (NotBoundException ex) {
	            System.err.println(ex.getMessage());
	        } catch (MalformedURLException ex) {
	            System.err.println(ex.getMessage());
	        } catch (RemoteException ex) {
	            System.err.println(ex.getMessage());
	        } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}}, delay, period);
		
	}

}