package gui.bookstore;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

public class RegistrationController {

    public String verifiedEmail;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField email;
    @FXML
    private TextField password;


    // login validation
    public void login(ActionEvent event) {

        RegistrationValidation verify = new RegistrationValidation();
        int userId=0;
        if (verify.checkEmptyFields(email.getText(), password.getText())) {
            // valid login
            // it check the user at the backend if he exists it return true and start
            // processing
            if (verify.checkUser(email.getText(), password.getText())) {
                // go to home page
            }

            else {

                int n = verify.correctPass(email.getText(), password.getText());
                // enetered worng password
                if (n == 2) {
                    errorMessage.setText("wrong password ! try again");
                }
                // email not found
                else if (n == 1) {
                    errorMessage.setText("Email not found ! try again");

                }
            }
        } else {
            errorMessage.setText("Please fill all the boxes");
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void signUp(ActionEvent event) {
        ScenesHandler test = new ScenesHandler();
        test.SignUp(event);
    }

    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void HomePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FxmlFiles/HomePage.fxml"));
            ScenesHandler test = new ScenesHandler();
            test.inbox(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profile() {
       //get user info then fill its data
        // firstName.setText(user.getFirstName());
        // Lastname.setText(user.getLastName());
        // userlabel.setText(user.getUser());
        // genderlabel.setText(user.getGender());
        // datelabel.setText("" + user.getDay() + "/" + contact.getMonth() + "/" + contact.getYear() + ".");
        // email.setText(contact.getEmail());
    }

    public void backMain(ActionEvent event) {
        ScenesHandler test = new ScenesHandler();
        test.backMain(event);
    }

}