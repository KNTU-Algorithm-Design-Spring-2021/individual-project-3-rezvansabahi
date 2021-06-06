import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FxClass extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Set<String> dict = new HashSet<>();

        BufferedReader br;
        FileReader fr = new FileReader("src/main/word_alpha.txt");
        br = new BufferedReader(fr);

        String line;

        while ((line = br.readLine()) != null) {
            dict.add(line);

        }

        fr.close();

        TextArea textArea = new TextArea();

        textArea.setEditable(false);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);

        Label label = new Label("Read message:");

        BorderPane pane = new BorderPane();

        TextField tf = new TextField();
        tf.setPrefColumnCount(3);
        tf.setPrefWidth(300);
        tf.setPromptText("Type here");
        tf.setStyle("-fx-padding: 5px;");
        Button message = new Button("Enter message");
        Button clear = new Button("Clear");
        clear.setOnAction(event -> {
            tf.clear();
            textArea.clear();
        });
        message.setOnAction(e -> {

            String input;

            input = tf.getText();
            input = input.toLowerCase();

            WordBreak wordBreak = new WordBreak(textArea, dict);
            wordBreak.wordBreak(input.length(), input);

        });

        vBox.getChildren().addAll(label, textArea);

        HBox hBox = new HBox(4);
        hBox.setPadding(new Insets(10));
        HBox.setHgrow(tf, Priority.ALWAYS);
        hBox.getChildren().addAll(tf, message, clear);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        pane.setStyle("-fx-background-color: pink;" + "-fx-border-color: gray;");

        pane.setCenter(vBox);

        hBox.setStyle("-fx-background-color: gray;");

        Scene scene = new Scene(pane, 550, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Word Break Backtracking");
        //Displaying the contents of the stage
        primaryStage.show();

    }

    public static void showResult(String[] args) {
        launch(args);
    }
}