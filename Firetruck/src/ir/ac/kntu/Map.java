package ir.ac.kntu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.ArrayList;

public class Map extends Group {

    private double[] xCor;
    private double[] yCor;

    private Circle createCircle(double x, double y, Color color, Text text) {

        Circle circle = new Circle(x, y, 5, color);
        final double PADDING = 10;
        circle.setRadius(getWidth(text) / 2 + PADDING);
        return circle;

    }

    private double getWidth(Text text) {
        new Scene(new Group(text));
        text.applyCss();
        return text.getLayoutBounds().getWidth();
    }

    public void creatMap(ArrayList<Integer> integers, double centerX, double centerY, double radius) {

        int num = 1;
        int count = 0;

        Label label = new Label("Fire Station");
        Label fire = new Label("Fire");
        Label path = new Label();

        StringBuilder sb = new StringBuilder();

        sb.append("Path : ");
        int n = integers.size();
        for (int i = 0; i < n; i++) {
            sb.append(integers.get(i));
            count++;
            if (count == 6) {
                sb.append("\n");
            }
            if (i != n - 1) {
                sb.append(" -> ");
            } else {
                fire.setTranslateX(xCor[integers.get(i) - 1] - 10);
                fire.setTranslateY(yCor[integers.get(i) - 1] - 25);
                fire.setStyle("-fx-text-fill:  #FFFFFF");
                this.getChildren().add(fire);
            }
        }

        path.setTranslateX(-50);
        path.setTranslateY(360);
        path.setStyle("-fx-text-fill:  #FFFFFF");
        path.setText(sb.toString());
        path.setTranslateX(-50);
        path.setTranslateY(300);
        path.setStyle("-fx-text-fill:  #FFFFFF");
        if (n > 0) {
            this.getChildren().add(path);
        }

        Text text = new Text(String.valueOf(num));
        text.setBoundsType(TextBoundsType.VISUAL);

        Circle[] circles = new Circle[21];
        xCor = new double[21];
        yCor = new double[21];
        final double angleStep = Math.PI * 2 / 21;
        double angle = 0; // assumes one point is located directly beneath the center point

        circles[0] = createCircle((Math.sin(angle) * radius + centerX), (Math.cos(angle) * radius + centerY) + 100, Color.color(Math.random(), Math.random(), Math.random()), text);
        xCor[0] = circles[0].getCenterX();
        yCor[0] = circles[0].getCenterY();

        label.setTranslateX(xCor[0] - 30);
        label.setTranslateY(yCor[0] + 10);
        label.setStyle("-fx-text-fill:  #FFFFFF");
        this.getChildren().add(label);
        angle += angleStep;
        this.getChildren().addAll(circles[0], new Text(xCor[0] - 4, yCor[0] + 6, String.valueOf(num)));
        double xCurrent, yCurrent;

        for (int i = 1; i < 21; i++, angle += angleStep) {


            circles[i] = circles[i] = createCircle((Math.sin(angle) * radius + centerX), (Math.cos(angle) * radius + centerY) + 100, Color.color(Math.random(), Math.random(), Math.random()), text);
            xCor[i] = circles[i].getCenterX();
            yCor[i] = circles[i].getCenterY();

            this.getChildren().addAll(circles[i], new Text(xCor[i] - 5, yCor[i] + 6, String.valueOf(++num)));
        }

        xCurrent = circles[0].getCenterX();
        yCurrent = circles[0].getCenterY();
        double y2;
        double x2;

        for (int i = 1; i < integers.size(); i++) {
            x2 = circles[integers.get(i) - 1].getCenterX();
            y2 = circles[integers.get(i) - 1].getCenterY();
            Line line = new Line(xCurrent, yCurrent, x2, y2);
            line.setStroke(Color.RED);
            xCurrent = x2;
            yCurrent = y2;
            this.getChildren().add(line);
            line.toBack();
        }

    }

    public void displayPath(ArrayList<Integer> integers) {

        this.getChildren().clear();
        creatMap(integers, 300, 150, 150);

    }

    public void display() {

        this.getChildren().clear();
        ArrayList<Integer> nullV = new ArrayList<>();
        creatMap(nullV, 300, 150, 150);

    }
}
