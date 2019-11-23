package scenePack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private static boolean answer;

    public static boolean display(String title, String message){

        //Creating a new window
        Stage window = new Stage();

        //Blocks user action until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(150);

        //setting the label message
        Label label = new Label();
        label.setText(message);

        //Creating the option buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setPrefSize(50, 20);
        noButton.setPrefSize(50, 20);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //Setting the GridPane layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));

        //Layer for label
        HBox labelLayer = new HBox();
        labelLayer.setAlignment(Pos.CENTER);
        labelLayer.getChildren().add(label);

        //Layer for the buttons
        HBox buttonLayer = new HBox();
        buttonLayer.setAlignment(Pos.CENTER);
        buttonLayer.setSpacing(20);
        buttonLayer.getChildren().addAll(yesButton, noButton);

        //Adding and Formatting the elements

        //adding the label on one line
        layout.getChildren().add(labelLayer);

        //adding the buttons on another line
        layout.getChildren().add(buttonLayer);

        //set the VBox spacing
        layout.setSpacing(20);

        //Adding the gridpane to the window
        Scene scene = new Scene(layout);
        window.setScene(scene);

        //block the caller until the modal stage is hidden (closed)
        window.showAndWait();

        return answer;
    }
}