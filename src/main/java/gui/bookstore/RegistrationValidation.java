package gui.bookstore;



import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationValidation {
//verify that the user enterd the right password
	public  int correctPass(String email, String password) {
		// for(int i = 0; i < usersAccount.size(); i++) {
		// 	Contact user = (Contact) usersAccount.get(i);
		// 	String userEmail = user.getEmail();
		// 	String userPass = user.getPass();
		// 	if(email.equals(user.getEmail())) {
		// 		if(password.equals(user.getPass())) {
		// 			return 0;
		// 		}
		// 		else {
		// 			return 2;
		// 		}
		// 	}
		// }
		return 1;
	}
		//check if the username exists in the backend

	public  Boolean checkUserName(String userName, LinkedList usersAccount) {
		for(int i = 0; i < usersAccount.size(); i++) {
			// Contact user = (Contact) usersAccount.get(i);
			// if(userName.equals(user.getUser())) {
            //   return false;
			// }
		}
		return true;
	}

	//check if the email exists in the backend
	public  Boolean checkEmail(String email) {
		if(!verifyEmailAddress(email)) {
			return false;
		}
		// for(int i = 0; i < usersAccount.size(); i++) {
		// 	check in the backend
		// 	Contact user = (Contact) usersAccount.get(i);
		// 	if(email.equals(user.getEmail())) {
        //       return false;
		// 	}
		// }
		return true;
	}
	
	public  Boolean verifyPass(String passOne, String passTwo) {
		if(passOne.equals(passTwo)) {
			return true;
		}
		return false;
	}
	
	public  Boolean verifyContact(String firstName, String lastName, String userName, String email, String pass) {
		if(firstName.isEmpty()) {
			return false;
		}
		if(lastName.isEmpty()) {
			return false;
		}
		if(userName.isEmpty()) {
			return false;
		}
		if(email.isEmpty()) {
			return false;
		}
		if(pass.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public  Boolean verifyEmailAddress(String email) {
		String regex = "^[A-Za-z0-9_.-]+@mfy.com$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public  Boolean checkEmptyFields(String mail, String pass) {
		if(mail.isEmpty()) {
			return false;
		}
		if(pass.isEmpty()) {
			return false;
		}
		return true;
	}
	public  Boolean checkUser(String mail, String pass) {
		if(mail.isEmpty()) {
			return false;
		}
		if(pass.isEmpty()) {
			return false;
		}
		return true;
	}
}