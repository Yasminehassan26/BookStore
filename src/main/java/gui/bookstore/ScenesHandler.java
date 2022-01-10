package gui.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class ScenesHandler {

	public void backMain(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
			Main test = new Main();
			test.setScene(event, root, "My BookShop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void homePage(Parent root,ActionEvent event) {
		try {
			Main test = new Main();
			test.setScene(event, root, "User HomePage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cart(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("UserCart.fxml"));
			Main test = new Main();
			test.setScene(event, root, "Cart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SignUp(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
			Main test = new Main();
			test.setScene(event, root, "Sign Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void homePageManager(Parent root,ActionEvent event) {
		try {
			Main test = new Main();
			test.setScene(event, root, "Manager");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}