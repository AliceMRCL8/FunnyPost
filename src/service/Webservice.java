package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Webservice {
	
	
	public void  getMethod() throws IOException {
		try {
			Connection conn = Connexion.getConnection();
			
			URL url = new URL ("https://jsonplaceholder.typicode.com/posts");
			HttpsURLConnection con= (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder data = new StringBuilder();
			while((inputLine = in.readLine())!= null) 
			{
				data.append(inputLine).append("\n");
			}
			in.close();
			
			JSONParser parser = new JSONParser();

	        try {
	            JSONArray jsonArray = (JSONArray) parser.parse(data.toString());
	            Statement st = conn.createStatement();

	            for (Object obj : jsonArray) {
	                JSONObject jsonObject = (JSONObject) obj;

	                int id = Integer.parseInt(jsonObject.get("id").toString());
	                String title = (String) jsonObject.get("title");
	                String body = (String) jsonObject.get("body");
	                
	                
	                ResultSet c = st.executeQuery("SELECT count(*) FROM post WHERE id ="+ id);
	                
	                if (c.next()&& c.getInt(1)== 0) {
		                PreparedStatement insertQuery = conn.prepareStatement("INSERT INTO post VALUES (?,?,?) ");

	                	insertQuery.setInt(1, id);
	                	insertQuery.setString(2, title);
	                	insertQuery.setString(3, body);
	                	insertQuery.executeUpdate();}
	                
	                
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
