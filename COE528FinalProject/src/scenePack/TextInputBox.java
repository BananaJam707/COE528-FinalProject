package scenePack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextInputBox {

    private static String userInput;

    public static String display(String title, String message){

        //Creating a new window
        Stage window = new Stage();

        //Blocks user action until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(450);
        window.setHeight(200);

        //setting the label message
        Label label = new Label();
        label.setText(message);

        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        TextField textFieldInput = new TextField();

        confirmButton.setOnAction(e -> {
            if(!textFieldInput.getText().trim().isEmpty()){
                userInput = textFieldInput.getText();
                window.close();
            }
        });

        cancelButton.setOnAction(e -> {
            userInput = null;
            window.close();
        });

        //Setting the GridPane layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));

        //Layer for label
        HBox labelLayer = new HBox();
        labelLayer.setAlignment(Pos.CENTER);
        labelLayer.getChildren().add(label);

        HBox inputLayer = new HBox();
        inputLayer.setAlignment(Pos.CENTER);
        inputLayer.getChildren().add(textFieldInput);

        //Layer for the buttons
        HBox buttonLayer = new HBox();
        buttonLayer.setAlignment(Pos.CENTER);
        buttonLayer.setSpacing(20);
        buttonLayer.getChildren().addAll(confirmButton, cancelButton);

        //Adding and Formatting the elements

        //adding all the layers into the layout
        layout.getChildren().addAll(labelLayer, inputLayer, buttonLayer);

        //set the VBox spacing
        layout.setSpacing(15);

        //Adding the gridpane to the window
        Scene scene = new Scene(layout);
        window.setScene(scene);

        //block the caller until the modal stage is hidden (closed)
        window.showAndWait();

        return userInput;
    }
}