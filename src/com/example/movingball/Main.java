package com.example.movingball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private final static int WIDTH = 1500;
    private final static int HEIGHT = 1500;

    @Override
    public void start(Stage primaryStage) {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, WIDTH, HEIGHT);
        Bounds bounds = canvas.getBoundsInLocal();

        Random random = new Random(System.currentTimeMillis());

        List<Ball> ballList = new ArrayList<>();
        for (int i=0; i<150; i++) {
            ballList.add(generateBall(random, canvas));
        }

        primaryStage.setTitle("Moving ball app");
        primaryStage.setScene(scene);
        primaryStage.show();


        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(60),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for (int i=0 ; i<ballList.size(); i++) {
                            Ball ball = ballList.get(i);
                            ball.setX(ball.getX() + ball.getDx());
                            ball.setY(ball.getY() + ball.getDy());
                            ball.checkInBounds(bounds);
                            List<Ball> restListA = ballList.subList(0, i);
                            List<Ball> restListB = ballList.subList(i + 1, ballList.size());
                            List<Ball> sumList = new ArrayList<>(restListA);
                            sumList.addAll(restListB);
                            for (int j=0 ; j<sumList.size() ; j++) {
                                Ball anotBall = sumList.get(j);
                                ball.checkTimeoutForCollision(anotBall);
                            }
                        }
                    }
                }
        ));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Ball generateBall(Random random, Pane canvas) {
        Color color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);
        double x = WIDTH*random.nextDouble();
        double y = HEIGHT*random.nextDouble();
        double dx = 10*random.nextDouble();
        double dy = 10*random.nextDouble();
        double r = 20*random.nextDouble();

        Ball ball = new Ball();
        ball.setX(x);
        ball.setY(y);
        ball.setColor(color);
        ball.setDx(dx);
        ball.setDy(dy);
        ball.setRadius(r);
        ball.addToCanvas(canvas);
        return ball;
    }
}
