package scenePack;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;

public class MainGUI extends Application{
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("Online Banking Application");

        //Initial Scene is set to MainMenu
        window.setScene(MainMenu.mainMenuScene(window));

        //not able to resize the window
        window.setResizable(false);

        //display the GUI
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}