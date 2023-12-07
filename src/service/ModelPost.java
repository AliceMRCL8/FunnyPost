package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entite.Posts;

public class ModelPost {
	
	Connection conn;
	
	public ModelPost() {		
		try {//connection a la bdd
			conn = Connexion.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Posts> GetPosts() throws Exception {
		
	
		try {//utilisation de la bdd
			Statement st = conn.createStatement();
			//prendre les données de la base
			ResultSet rs = st.executeQuery("SELECT * from post");
			//creer la list
			ArrayList<Posts>  posts = new ArrayList();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				
				Posts post = new Posts(id,title,body);
				posts.add(post);
			}
			//boucle While iterator
			Iterator<Posts> iterator = posts.iterator();
			while (iterator.hasNext()) {
			    Posts post = iterator.next();
			    // Faites quelque chose avec l'objet post
			    System.out.println("ID : " + post.getId());
			    System.out.println("Titre : " + post.getTitle());
			    System.out.println("Corps : " + post.getBody());
			}
			
			//retourne les informations de la base list
			return posts;
		}catch (SQLException e) 
			{e.printStackTrace();}		
		return null;
	}
}
