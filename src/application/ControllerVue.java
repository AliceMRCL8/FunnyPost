package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import entite.Posts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import service.ModelPost;
import service.Webservice;

public class ControllerVue implements Initializable{
	@FXML
	private ListView<String> Donnees;
	@FXML
	private Button init;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ModelPost mp = new ModelPost();
			
			ArrayList<Posts> posts = mp.GetPosts();			
			for(Posts post: posts) {
				Donnees.getItems().addAll(post.getTitle());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void envoieDesDonnes() {
		Webservice ws = new Webservice();
		try {
			ws.getMethod();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
