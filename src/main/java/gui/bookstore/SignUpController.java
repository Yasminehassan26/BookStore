package gui.bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {

    public String verifiedEmail;
    ObservableList<String> list = FXCollections.observableArrayList("Female", "Male");

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txPassword;
    @FXML
    private TextField txtUserName1;
    @FXML
    private TextField txPassword1;

    @FXML
    private TextField txRePass;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private Label userlabel;
    @FXML
    private Label firstName;
    @FXML
    private Label Lastname;
    @FXML
    private Label email;
    @FXML
    private Label datelabel;
    @FXML
    private Label genderlabel;

//    public void signUp(ActionEvent event) {
//
//        ObjectReaderWriter object = new ObjectReaderWriter();
//        App app = new App();
//        doublyLinkedLists users = object.readFile(app.index);
//        verify Verify = new verify();
//
//        if ((!(gender.getValue() == null)) && (!(date.getValue() == null)) && Verify.verifyContact(txtfirstName.getText(), txtlastName.getText(), txtUserName1.getText(), txtmail.getText(), txPassword1.getText())) {
//            Contact contact = new Contact(txtfirstName.getText(), txtlastName.getText(), txtUserName1.getText(), txtmail.getText() + "@mfy.com", txPassword1.getText(), date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear(), gender.getValue());
//
//            if (Verify.verifyPass(txPassword1.getText(), txRePass.getText())) {
//                if (app.signup(contact)) {
//                    verifiedEmail = txtmail.getText() + "@mfy.com";
//                    app.accountUser = txtmail.getText() + "@mfy.com";
//                    controllerInbox.receive(app);
//                    inbox(event);
//                } else {
//                    if (!Verify.verifyEmailAddress(txtmail.getText() + "@mfy.com")) {
//                        lblstatus1.setText("Wrong Email Format");
//                        lblstatus2.setText("enter another one");
//                    } else if (!Verify.checkEmail(txtmail.getText() + "@mfy.com", users)) {
//                        lblstatus1.setText("Email already exist,");
//                        lblstatus2.setText("enter another one");
//                    } else if (!Verify.checkUserName(txtUserName1.getText(), users)) {
//                        lblstatus1.setText("Username already exist,");
//                        lblstatus2.setText("enter another one");
//                    }
//
//                }
//            } else {
//                lblstatus2.setText("");
//                lblstatus1.setText("Verify the password");
//
//            }
//        } else {
//            lblstatus2.setText("");
//            lblstatus1.setText("Please fill all the boxes");
//
//        }
//
//    }
//
//
//    public void backMain(ActionEvent event) {
//        scenes test = new scenes();
//        test.backMain(event);
//    }


}