package Chat.Sources.Client.Src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class weather {

	public String getWeatherInfo(String city_name) throws IOException, ParseException  {
		//Url : api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}&lang=kr
		//String wheater(String baseDate, String baseTime, String nx, String ny) throws IOException, ParseException {
	        StringBuilder urlBuilder = new StringBuilder("http://api.openweathermap.org/data/2.5/weather"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("q","UTF-8") + "=" + URLEncoder.encode(city_name, "UTF-8")); /*����id*/
	        urlBuilder.append("&" + URLEncoder.encode("appid","UTF-8") + "=8db046569de7051d97780c0106505b69"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("lang","UTF-8") + "=en"); /*Service Key*/

	        URL url = new URL(urlBuilder.toString());
	       // System.out.println(url);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	      //  System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        String result=sb.toString();
	       // System.out.println(result);
		
	       StringBuilder rs = new StringBuilder();
	      JSONParser parser = new JSONParser(); 
	      

	      JSONObject obj = (JSONObject) parser.parse(result); 
	      JSONArray parse_weather = (JSONArray) obj.get("weather"); 
	      JSONObject weather_obj = (JSONObject) parse_weather.get(0); 
	      Object description = weather_obj.get("description");
	      JSONObject parse_coor = (JSONObject) obj.get("main"); 
	      //JSONObject parse_city = (JSONObject) obj.get("name"); 

	      int temp= (int)Math.round(Double.parseDouble(parse_coor.get("temp").toString())-273.15);
	      int temp_min =   (int)Math.round(Double.parseDouble(parse_coor.get("temp_min").toString())-273.15);
	      int temp_max =   (int)Math.round(Double.parseDouble(parse_coor.get("temp_max").toString())-273.15);
	      Object humidity = parse_coor.get("humidity");
	      String city = (String)(obj.get("name"));

	      
	      rs.append("["+city+"] "+description+", (Temperature) Current: "+ Integer.toString(temp) + ", Min/Max: " +  Integer.toString(temp_min) 
	      				+"/"+  Integer.toString(temp_max) + ", Humidity : "+ humidity+"%");
	      return rs.toString();
	    	
	}
}

