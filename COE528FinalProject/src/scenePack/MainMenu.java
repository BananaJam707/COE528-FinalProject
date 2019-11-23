package scenePack;

import javafx.geometry.Insets;
import systemPack.Authentication;
import scenePack.MainGUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenu {

    public static Scene mainMenuScene(Stage window) throws FileNotFoundException{
        //making the Scene non resizable
        BorderPane bp = new BorderPane();
        //Center Pane
        GridPane centerBox = new GridPane();
        centerBox.setPadding(new Insets(10));

        //define labels and inputs
        Label lblName = new Label("Username: ");
        TextField tfName = new TextField();
        Label lblPwd = new Label("Password: ");
        PasswordField pfPwd = new PasswordField();

        //Defining the button
        Button submitButton = new Button("Submit");
        submitButton.setPrefSize(100, 20);

        //Defining the role choice box, to determine the role of the user
        ChoiceBox<String> roleChoiceBox = new ChoiceBox<>();

        //Inputting 2 roles
        //getItems returns the ObservableList object, which is the string of
        //the occupation
        roleChoiceBox.getItems().add("User");
        roleChoiceBox.getItems().add("Manager");

        //Set default option to user
        roleChoiceBox.setValue("User");

        //Defining the buttons actions
        submitButton.setOnAction(e -> {

            //Authenticates the manager and swaps scenes to manager scene
            if( Authentication.authenticateManager( tfName.getText(), pfPwd.getText())
                    && roleChoiceBox.getValue().equals("Manager") ){

                try {
                    window.setScene(ManagerMainMenu.managerMainMenuScene(window));
                } catch (FileNotFoundException ex) {
                    AlertBox.display("Error", "File Not Found");
                }
            }

            //Authenticates the user and switches scenes
            else if(Authentication.authenticateUser( tfName.getText(), pfPwd.getText())
                    && roleChoiceBox.getValue().equals("User") ){

                //changes scene to user account GUI
                try {
                    //Change scene to user account scene
                    window.setScene(UserMainMenu.userMainMenuScene(window, tfName.getText()));
                } catch (FileNotFoundException ex) {
                    AlertBox.display("Error", "File Not Found");
                }
            }

            else{
                AlertBox.display("Authentication Failed", "Username and "
                        + "Password Do Not Match");
            }
        });

        //set Vertical gap of elements to 5 pixels
        centerBox.setVgap(5);

        //alignment of the labels and textfield inputs
        centerBox.add(lblName, 0, 0);
        centerBox.add(tfName, 1, 0);
        centerBox.add(lblPwd, 0, 1);
        centerBox.add(pfPwd, 1, 1);
        centerBox.add(roleChoiceBox, 1, 2);
        centerBox.add(submitButton, 0, 5);

        //set the center screen with a background colour
        centerBox.setStyle("-fx-background-color: #7799C8;");

        bp.setCenter(centerBox);
        Scene scene = new Scene(bp, 1050, 500);

        return scene;
    }
}