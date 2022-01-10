package gui.bookstore;

import Backend.Models.BookStore;
import Backend.Models.Manager;
import Backend.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {
    public BookStore store = new BookStore();
    public String verifiedEmail;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField email;
    @FXML
    private TextField password;


    // login validation
    public void login(ActionEvent event) throws SQLException {

        Validation verify = new Validation();
        int userId = 0;
        if (verify.checkEmptyFields(email.getText(), password.getText())) {
            // valid login
            // it checks the user at the backend if he exists it return true and start
            // processing

            boolean check = store.getCurrentUser().signIn(email.getText(), password.getText());
            if (check) {


                User user = new User();
                ResultSet result = BookStore.databaseManager.executeQuery("CALL getUser('" + email.getText() + "')");
                result.next();
                user.setUserInformation(result);
                store.setCurrentUser(user);
                int role = store.getCurrentUser().getPrivilege();// store.getUsers().

                if (role == 0) {
                    HomePage(event);
                } else {
                    store.setCurrentManager(new Manager());
                    ManagerHomePage(event);
                }
            } else {
                errorMessage.setText("Please enter valid information");
            }
        } else {
            errorMessage.setText("Please fill all the boxes");
        }

    }

    public void HomePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            root = loader.load();
            HomepageController c = loader.getController();
            c.receive(store);
            ScenesHandler test = new ScenesHandler();
            test.homePage(root, event);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ManagerHomePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ManagerHomePage.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerHomePage.fxml"));
            root = loader.load();
            ManagerHomePage c = loader.getController();
            c.receive(store);
            ScenesHandler test = new ScenesHandler();
            test.homePageManager(root,event);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void signUp(ActionEvent event) {
        ScenesHandler test = new ScenesHandler();
        test.SignUp(event);
    }


}