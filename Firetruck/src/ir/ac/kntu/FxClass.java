package ir.ac.kntu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FxClass extends Application {

    private final ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    private final Menu menu1 = new Menu("Solutions");
    private final MenuBar menuBar = new MenuBar();
    private final TextArea ta = new TextArea();
    private final Map map = new Map();

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        menuBar.getMenus().add(menu1);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);

        ta.setPromptText("Type here");
        ta.setStyle("-fx-padding: 5px;");
        vBox.setStyle("-fx-background-color: pink;" + "-fx-border-color: black;");
        Button input = new Button("Show result");
        Button clear = new Button("Clear");
        Button exit = new Button("Exit");

        Label label = new Label("Enter input and See the solution paths in solutions menu.");
        label.setStyle("-fx-text-fill: gray");

        HBox hBox = new HBox(4);
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(input, clear, exit);

        hBox.setAlignment(Pos.CENTER_RIGHT);
        input.setOnAction(e -> {

            solutions.clear();
            menu1.getItems().clear();
            checkingInput();
            ta.setText("See the solution paths in solutions menu.");

        });
        clear.setOnAction(e -> ta.clear());
        exit.setOnAction(e -> primaryStage.close());
        vBox.getChildren().addAll(label, ta, hBox);

        pane.setStyle("-fx-background-color: #000000;");

        pane.setBottom(vBox);
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(menuBar);

        pane.setTop(vBox1);
        pane.setCenter(map);

        map.display();

        Scene scene = new Scene(pane, 600, 650);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fire Truck");
        //Displaying the contents of the stage
        primaryStage.show();
    }

    public void calcPath(int[][] ist, int pos, int fire, int[] mv, int mvNo) {
        ArrayList<Integer> temp = new ArrayList<>();
        int j;

        if (pos == fire) {
            for (int i = 0; i < mvNo; i++) {
                temp.add(mv[i]);
            }
            solutions.add(temp);
            return;
        }
        for (int i = 0; i < 22; i++) {
            if (ist[pos][i] == 1) {
                for (j = 0; j < mvNo; j++) {
                    if (mv[j] == i) {
                        break;
                    }
                }
                if (j == mvNo) {
                    mv[mvNo] = i;
                    calcPath(ist, i, fire, mv, mvNo + 1);
                }
            }
        }
    }

    //checkingInput input and call calcPath method
    public void checkingInput() {
        int countT;
        int count = 0;
        String t = ta.getText();
        String[] temp = t.split("\n");
        String[] t2;

        int fire = 0;
        int x, y;
        int case0 = 0;
        int[][] ist = new int[22][22];
        int[] mv = new int[22];

        if (temp[0].length() != 0) {

            fire = Integer.parseInt(temp[0]);
        }
        int i = 1;
        while (0 < fire && fire < 22) {

            for (x = 0; x < 22; x++) {
                for (y = 0; y < 22; y++) {
                    ist[x][y] = 0;
                }
            }
            t2 = temp[i].split(" ");
            x = Integer.parseInt(t2[0]);
            y = Integer.parseInt(t2[1]);

            while (x != 0 && y != 0) {
                ist[x][y] = 1;
                ist[y][x] = 1;
                t2 = temp[i].split(" ");
                x = Integer.parseInt(t2[0]);
                y = Integer.parseInt(t2[1]);
                i++;
            }
            mv[0] = 1;
            calcPath(ist, 1, fire, mv, 1);
            ++case0;
            Menu subMenu = new Menu("Case" + case0);

            for (int k = count; k < solutions.size(); k++) {

                Menu itemMenu = new Menu(String.valueOf(k - count + 1));
                subMenu.getItems().add(itemMenu);
                int finalK = k;
                itemMenu.setOnAction(e -> {
                    ta.clear();

                    map.displayPath(solutions.get(finalK));
                    System.out.println(solutions.get(finalK));
                });
            }
            menu1.getItems().add(subMenu);
            countT = count;
            count += solutions.size();
            count -= countT;

            if (i != temp.length) {

                fire = Integer.parseInt(temp[i]);

            } else {
                break;
            }
            i++;
        }
    }

    public static void showResult(String[] args) {
        launch(args);
    }
}