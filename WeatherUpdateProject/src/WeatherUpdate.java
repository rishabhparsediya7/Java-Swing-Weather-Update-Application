import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class WeatherUpdate implements ActionListener{
	private JTextField searchCity, temperature,pressure,clouds;
	private JTextField dateShow, humidity, cityNameField,lat,lon;
	private JTextArea showUpdate;
	private JFrame frame; 
	private JLabel searchLabel,Banner;
	private String cityname;
	public JButton getUpdate, reset;
	private String getdate;
	String arr[]=new String[7];
	WeatherUpdate(String res)
	{
		Color framecolor=new Color(255,69,0);
		Color textfields=new Color(85,107,47);
		Color banner=new Color(144,238,144);
		
		frame=new JFrame(res);
		frame.setBackground(framecolor);
		
		Banner=new JLabel("Weather Update Application", JLabel.CENTER);
		Banner.setBounds(0,0,1500, 70);
		Banner.setFont(new Font("Times New Roman", Font.BOLD, 32));
		Banner.setBackground(banner);
		Banner.setForeground(new Color(0,139,139));
		
		
		searchLabel=new JLabel("Search your city:", JLabel.CENTER);
		
		searchLabel.setBounds(500,150,200,30);
		searchLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		searchLabel.setForeground(new Color(0,139,139));
		searchLabel.setBackground(textfields);

		searchCity=new JTextField();
		searchCity.setBounds(700, 150, 200, 30);
		
		//Cityname field in Textarea.
		cityNameField=new JTextField();
		cityNameField.setBounds(150,270,400,100);
		cityNameField.setFont(new Font("Times New Roman", Font.ITALIC, 72));
		Border border = BorderFactory.createLineBorder(textfields, 2);
		cityNameField.setBorder(border);
		
		//latitude.
		lon=new JTextField();
		lon.setBounds(150,400,300,50);
		lon.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		Border border1 = BorderFactory.createLineBorder(textfields, 2);
		lon.setBorder(border1);
		
		//latitude
		lat=new JTextField();
		lat.setBounds(150,450,300,50);
		lat.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		Border border2 = BorderFactory.createLineBorder(textfields, 2);
		lat.setBorder(border2);
		
		//button to click get update
		getUpdate=new JButton("Get Updates");
		getUpdate.setBounds(500, 210, 150, 20);
		getUpdate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		//reset button which clear search field.
		reset=new JButton("Reset");
		reset.setBounds(800, 210, 150, 20);
		reset.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss aa");
		Calendar calobj = Calendar.getInstance();
		getdate = df.format(calobj.getTime()).toString();
	
		//date show field 
		dateShow= new JTextField(getdate);
		dateShow.setFont(new Font("Times New Roman", Font.ITALIC, 32));
		dateShow.setBounds(1000,270,330,50);
		Border border3 = BorderFactory.createLineBorder(textfields, 2);
		dateShow.setBorder(border3);
		
		//tempearture show field.
		temperature= new JTextField();
		temperature.setFont(new Font("Times New Roman", Font.ITALIC, 32));
		temperature.setBounds(1000,340,330,50);
		Border border4 = BorderFactory.createLineBorder(textfields, 2);
		temperature.setBorder(border4);
		
		//pressure show field
		pressure= new JTextField();
		pressure.setFont(new Font("Times New Roman", Font.ITALIC, 32));
		pressure.setBounds(1000,410,330,50);
		Border border5 = BorderFactory.createLineBorder(textfields, 2);
		pressure.setBorder(border5);
		
		//humidity show field.
		humidity= new JTextField();
		humidity.setFont(new Font("Times New Roman", Font.ITALIC, 32));
		humidity.setBounds(1000,480,330,50);
		Border border6 = BorderFactory.createLineBorder(textfields, 2);
		humidity.setBorder(border6);
		
		//clouds show field.
		clouds= new JTextField();
		clouds.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		clouds.setBounds(1000,550,330,50);
		Border border7 = BorderFactory.createLineBorder(textfields, 2);
		clouds.setBorder(border7);
	
		showUpdate=new JTextArea();
		showUpdate.setBounds(100,250,1350,400);
		showUpdate.setFont(new Font("Times New Roman", Font.ITALIC, 72));
		
		// set the border of this component
		Border border8 = BorderFactory.createLineBorder(framecolor, 5);
		showUpdate.setBorder(border8);
		
		//img=Toolkit.getDefaultToolkit().createImage("C:\\Users\\HP\\eclipse-workspace\\WeatherUpdateProject\\beach.jpg");
		
	
		frame.setSize(1800,900);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(Banner);
		frame.add(searchLabel);
		frame.add(searchCity);
		frame.add(getUpdate);
		frame.add(reset);
		frame.add(showUpdate);
		frame.add(dateShow);
		frame.add(temperature);
		getUpdate.addActionListener(this);
		reset.addActionListener(this);
		frame.add(getUpdate);
		frame.add(reset);
		frame.add(cityNameField);
		frame.add(pressure);
		frame.add(clouds);
		frame.add(humidity);
		frame.add(lat);
		frame.add(lon);
	}
	public void actionPerformed(ActionEvent e) {
		cityname=searchCity.getText();
		if(e.getSource()==getUpdate) 
		{
				JSONDataFormat call=new JSONDataFormat();
				try 
				{
					String arr[]=call.getDataFormupdates(cityname);
					cityNameField.setText(arr[0]);
					temperature.setText(arr[3]);
					pressure.setText(arr[4]);
					humidity.setText(arr[5]);
					clouds.setText(arr[6]);
					lon.setText(arr[1]);
					lat.setText(arr[2]);
					dateShow.setText(getdate);

				} catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		if(e.getSource()==reset)
		{
			searchCity.setText(" ");
			cityNameField.setText(" ");
			temperature.setText(" ");
			pressure.setText(" ");
			humidity.setText(" ");
			clouds.setText(" ");
			lat.setText(" ");
			lon.setText(" ");
		}
	}
	public static void main(String args[])
	{
		new WeatherUpdate("Weather Update App"); 
	}
}