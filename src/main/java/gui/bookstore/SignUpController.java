package gui.bookstore;

import Backend.Models.BookStore;
import Backend.Models.UserBasicInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    public String verifiedEmail;


    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField rePassword;
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    @FXML
    private Label passError;
    @FXML
    private Label errorMessage;
    public BookStore store=new BookStore();

    public void signUp(ActionEvent event) throws SQLException {
  // -1 username is take -2 email taken -3 0 ok
        // true tmam false
        Validation verify = new Validation();
        boolean check = verify.checkEmptyFields(firstname.getText(), lastname.getText(), username.getText(), email.getText(), password.getText(), rePassword.getText(), phone.getText(), address.getText());
        if (check) {
            //create user
            if (verify.verifyPass(password.getText(), rePassword.getText())) {
                //successful sign up process

                UserBasicInfo user=new UserBasicInfo();
                user.setEmail(email.getText());
                user.setFirstName(firstname.getText());
                user.setLastName(lastname.getText());
                user.setUsername(username.getText());
                user.setShippingAddress(address.getText());
                user.setPhoneNumber(phone.getText());
                user.setPassword(password.getText());
                int out=store.getCurrentUser().signUp(user);
                if(out==0) {
                    HomePage(event);
                }
                else if(out==-1){
                    passError.setText("");
                    errorMessage.setText("");
                    errorMessage.setText("UserName is already taken");
                }
                else if(out==-2){
                    passError.setText("");
                    errorMessage.setText("");
                    errorMessage.setText("Email is already taken");
                }
            } else {
                errorMessage.setText("");
                passError.setText("");
                passError.setText("Verify the password");

            }
        } else {
            passError.setText("");
            errorMessage.setText("");
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

    public void backMain(ActionEvent event) {
        ScenesHandler test = new ScenesHandler();
        test.backMain(event);
    }


}