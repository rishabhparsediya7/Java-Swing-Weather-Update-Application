import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONDataFormat {
private String APIKey="YOUR_API_KEY";

	
	private String inline;
	private String arr[]=new String[7];
	
	public String[] getDataFormupdates(String city) throws Exception
	{
		String stringurl="http://api.openweathermap.org/data/2.5/weather?q=" + city + "&mode=json&appid=" + APIKey;
	
		URL url=new URL(stringurl);
	
		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		int responsecode=conn.getResponseCode();
		if(responsecode!=200)
			throw new RuntimeException("HttpResponseCode"+responsecode);
		else 
		{
			Scanner sc=new Scanner(url.openStream());
			while(sc.hasNext()) {
			
				inline = sc.nextLine();			
			}
			//System.out.println(inline);
			sc.close();
		}
		//defining json objects to collect all other attributes
				//which are in json format.
				JSONObject json = new JSONObject(inline);
				JSONObject cord = json.getJSONObject("coord");
				JSONObject temperature = json.getJSONObject("main");
				
				//getting latitude and longitude
				Double longitude = (Double) cord.get("lon"); 
				Double latitude = (Double) cord.get("lat"); 
				
				//getting temperature in celsius and pressure and humidity.
				Double temper=(Double) temperature.get("temp");
				int pressure=(Integer) temperature.get("pressure");
				int humidity=(Integer) temperature.get("humidity");
				float tempInCelsius=(float) (temper - 273.15);
				
				//getting cityname
				String requestedCity=(String) json.get("name");	
				JSONArray arrjson = json.getJSONArray("weather");
				
				String postid ="";
				for (int i = 0; i < arrjson.length(); i++)
				{
				    postid = arrjson.getJSONObject(i).getString("main");
				
				}
				arr[1]=requestedCity+"'s longitude is:"+longitude.toString();
				arr[2]=requestedCity+"'s latitude is:"+latitude.toString();
				arr[3]="Temperature:"+String.valueOf(tempInCelsius)+" C.";
				arr[4]="Pressure = "+String.valueOf(pressure)+" atm";
				arr[5]="Humidity = "+String.valueOf(humidity)+" %";
				arr[0]=requestedCity;
				arr[6]=requestedCity+"'s Weather:"+postid;
				
				/*System.out.println(arr[0]);
				System.out.println(arr[1]);
				System.out.println(arr[2]);
				System.out.println(arr[3]);
				System.out.println(arr[4]);
				System.out.println(arr[5]);
				System.out.println(arr[6]);*/
				
				return arr;
	}

}
