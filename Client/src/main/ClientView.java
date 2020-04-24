package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ClientView extends JFrame {

	private JPanel contentPane;
	static int delay = 0; // delay for 0 sec. 
	static int period = 15000; // repeat every 15 sec. 
	static Timer timer = new Timer();
	 static ClientView frame ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ClientView();
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
				setBounds(20, 20, 1325, 732);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				
				
				JButton btnAddFloor = new JButton("Add Floor");
				btnAddFloor.setBounds(806, 10, 95, 27);
				getContentPane().add(btnAddFloor);
				
				btnAddFloor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FloorAdd fa = new FloorAdd();
						fa.setVisible(true);
						setVisible(false);
						
					}
				});
				
		   
		int p = 185;
		for(int v=0; v <8; v++) {
		JLabel lblNewLabel_1 = new JLabel("Room " + (v+1));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(p, 42, 81, 20);
		contentPane.add(lblNewLabel_1);
		p= p + 112;
		}
		
		
		System.setProperty("java.security.policy", "file:allowall.policy");
	        
	        Service service = null;
		
	        try {
	        	
	            service = (Service) Naming.lookup("//localhost/LevelService");
	            
	            String s = service.Getdata();
	           
	           JSONArray jsar = new JSONArray(s);
	          
	           int y = 72;
	           
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
