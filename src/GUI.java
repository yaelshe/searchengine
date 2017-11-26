import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GUI extends Application {
        //implements EventHandler<ActionEvent> {

        Button rstButton;
    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
    //stage is the window and scene is the content
        primaryStage.setTitle("Our Search Engine!");
        StackPane layout = new StackPane();

        rstButton= new Button();
        rstButton.setText("RESET");
        layout.getChildren().add(rstButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        rstButton.setOnAction(e -> System.out.println("Lambda expressions are awesome!"));
    }

    /**@Override
    public void handle(ActionEvent event) {
    if(event.getSource()==rstButton)//which object was clicked
    {

    }
    else
    }
    */
}
