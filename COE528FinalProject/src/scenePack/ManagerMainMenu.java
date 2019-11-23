package scenePack;

import systemPack.Manager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerMainMenu {



    public static Scene managerMainMenuScene(Stage window) throws FileNotFoundException{

        //making the Scene non resizable
        BorderPane bp = new BorderPane();

        //Bottom Pane

        //Create the Horizontal Box
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(20,50,20,20));

        //Log Out button
        Button logOutButton = new Button("Log Out");
        logOutButton.setPrefSize(100, 20);

        //Log Out Button Action
        logOutButton.setOnAction(e -> {
            try {
                //Confirmation to log out
                if(ConfirmBox.display("Confirmation", "Are You Sure You"
                        + " Want To Log Out")){

                    //Perhaps a save function for the manager goes here

                    //return to main menu
                    window.setScene(MainMenu.mainMenuScene(window));
                }
            } catch (Exception ex) {
                Logger.getLogger(UserMainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        bottomBox.getChildren().addAll(logOutButton);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        bottomBox.setStyle("-fx-background-color: #3F669A;");
        bp.setBottom(bottomBox);

        //Center Pane

        VBox centerBox = new VBox();
        centerBox.setPadding(new Insets(10, 10, 0, 10));
        centerBox.setSpacing(10);

        //Set the background style
        centerBox.setStyle("-fx-background-color: #7799C8;");
        bp.setCenter(centerBox);

        //Add the Add Account Button
        Button addAccountButton = new Button("Add Account");
        addAccountButton.setPrefSize(300, 60);

        //Add the Delete Account button
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setPrefSize(300, 60);

        //Function of the Add Account Button

        addAccountButton.setOnAction(e -> {
            String username = TextInputBox.display("New Account", "Input Username");
            String password = TextInputBox.display("New Account", "Input Password");
            String firstName = TextInputBox.display("New Account", "Input User's First Name");
            String lastName = TextInputBox.display("New Account", "Input User's Last Name");

            if(username != null || password != null || firstName != null || lastName != null ||
                    username.trim().equals("") || password.trim().equals("") ||
                    firstName.trim().equals("") || lastName.trim().equals("")){
                try {
                    Manager.createUser(username, password, firstName, lastName);
                }catch (IOException ex) {
                    AlertBox.display("Error", "File Not Found");
                }
            }
            else{
                AlertBox.display("Error", "One or More Entries Are Blank");
            }
        });

        deleteAccountButton.setOnAction(e -> {
            String username = TextInputBox.display(
                    "Delete Account", "Input Username of Account: ");

            try {
                Manager.deleteUser(username);
            } catch (IOException ex) {
                AlertBox.display("Error", "Usernane: "+username+
                        "Doesn't exist");
            }
        });

        centerBox.getChildren().addAll(addAccountButton, deleteAccountButton);

        Scene scene = new Scene(bp, 1050, 500);

        return scene;
    }
}