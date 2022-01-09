package gui.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import  gui.bookstore.Main;
public class ScenesHandler {
	
	public void backMain(ActionEvent event ) {
		try {
		  Parent  root=FXMLLoader.load(getClass().getResource("/application/main.fxml"));
	       Main test= new Main() ;
	       test.setScene(event, root,"My Mail");
	}
	    catch(Exception e) {
	         e.printStackTrace();	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
	public void inbox (ActionEvent event ) {
	try {
		  Parent  root=FXMLLoader.load(getClass().getResource("/application/inbox.fxml"));
	Main test= new Main() ;
	test.setScene(event, root,"Inbox");
	}catch(Exception e) {
	e.printStackTrace();	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void SignUp (ActionEvent event ) {
		try {
		 Parent  root=FXMLLoader.load(getClass().getResource("/application/sign up.fxml"));
	     Main test= new Main() ;
	     test.setScene(event, root,"Sign Up");
		}catch(Exception e) {
	        e.printStackTrace();	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void Draft (ActionEvent event ) {
		try {
		Parent  root=FXMLLoader.load(getClass().getResource("/application/draft.fxml"));
	     Main test= new Main() ;
	     test.setScene(event, root,"Draft");
		}catch(Exception e) {
	        e.printStackTrace();	}
	}
	///////////////////////////////////////////////////////////
		public void Trash (ActionEvent event ) {
			try {
		    Parent  root=FXMLLoader.load(getClass().getResource("/application/trash.fxml"));
		     Main test= new Main() ;
		     test.setScene(event, root,"Trash");
			}catch(Exception e) {
		        e.printStackTrace();	}
	}
	///////////////////////////////////////////////////////////////////////
   

public void folder(ActionEvent event,Parent root,String name) {
	try {
		Main test= new Main() ;
		test.setScene(event, root,name);
		}catch(Exception e) {
		e.printStackTrace();	}	
}
/////////////////////////////////////////////////////////////////////////////////////////////
public void sent (ActionEvent event ) {
	try {
	 Parent  root=FXMLLoader.load(getClass().getResource("/application/sent.fxml"));
     Main test= new Main() ;
     test.setScene(event, root,"Sent");
	}catch(Exception e) {
        e.printStackTrace();	}
}



public void sendto (ActionEvent event,Parent root ) {
	try {
     Main test= new Main() ;
     test.setScene(event, root,"Sent");
	}catch(Exception e) {
        e.printStackTrace();	}
}

public void draftto (ActionEvent event,Parent root ) {
	try {
     Main test= new Main() ;
     test.setScene(event, root,"Draft");
	}catch(Exception e) {
        e.printStackTrace();	}
}

public void contacts(ActionEvent event ) {
	try {
	  Parent  root=FXMLLoader.load(getClass().getResource("/application/contacts.fxml"));
       Main test= new Main() ;
       test.setScene(event, root,"Contacts");
}
    catch(Exception e) {
         e.printStackTrace();	}
}



}