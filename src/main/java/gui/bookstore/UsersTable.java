package gui.bookstore;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class UsersTable {
    private final SimpleStringProperty ID;
    private final SimpleStringProperty UserName;
    private CheckBox Select;

    public UsersTable(String ID, String username) {
        this.ID = new SimpleStringProperty(ID);
        this.UserName = new SimpleStringProperty(username);
        this.Select = new CheckBox();
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public String getUserName() {
        return UserName.get();
    }

    public void setUserName(String userName) {
        this.UserName.set(userName);
    }

    public SimpleStringProperty userNameProperty() {
        return UserName;
    }

    public CheckBox getSelect() {
        return Select;
    }

    public void setSelect(CheckBox select) {
        Select = select;
    }


}
