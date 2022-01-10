package gui.bookstore;

import Backend.Models.BookStore;
import Backend.Models.User;
import Backend.Models.UserBasicInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ProfileController {

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
    private ComboBox<String> gender;

    @FXML
    private Label errorMessage;


    public BookStore store;
    public void loadProfile(BookStore store,User user) {
        email.setDisable(true);
        this.store=store;
        username.setDisable(true);
        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        address.setText(user.getShippingAddress());
        phone.setText(user.getPhoneNumber());
        email.setText(user.getEmail());

    }

    public void save(ActionEvent event) throws SQLException {


        Validation verify = new Validation();
        boolean check = verify.checkEmptyFields(firstname.getText(), lastname.getText(), username.getText(), email.getText(), password.getText(),"not", phone.getText(), address.getText());
        if (check) {
            //save user
            UserBasicInfo user=new UserBasicInfo();
            user.setId(store.getCurrentUser().getId());
            user.setFirstName(firstname.getText());
            System.out.println(store.getCurrentUser().getPrivilege());
            user.setLastName(lastname.getText());
            user.setShippingAddress(address.getText());
            user.setPhoneNumber(phone.getText());
            user.setPassword(password.getText());
           store.getCurrentUser().editPersonalInfo(user);

            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        } else {
            errorMessage.setText("");
            errorMessage.setText("Please fill all the boxes");

        }


    }


}