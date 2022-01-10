package gui.bookstore;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    static Stage window;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("showTree.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScene(javafx.event.ActionEvent event, Parent root, String name) {
        Scene scene = new Scene(root);
        window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.setTitle(name);
        window.setResizable(false);
        window.show();
    }
}