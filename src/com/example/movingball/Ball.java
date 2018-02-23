package com.example.movingball;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private double x;
    private double y;
    private Color color;
    private double dx;
    private double dy;
    private double radius;

    private Circle circle;
    private long time;
    private boolean checkMoving = true;

    public Ball() {
        this.circle = new Circle();
    }

    public void addToCanvas(Pane canvas) {
        canvas.getChildren().add(this.circle);
    }

    public void checkInBounds(Bounds bounds) {
        if (this.x <= (bounds.getMinX() + this.circle.getRadius()) ||
                this.x >= (bounds.getMaxX() - this.circle.getRadius())) {
            this.dx = - this.dx;
        }
        if (this.y >= (bounds.getMaxY() - this.circle.getRadius()) ||
                this.y <= (bounds.getMinY() + this.circle.getRadius())) {
            this.dy = - this.dy;
        }
    }

    public boolean checkCollision(Ball anotherBall) {
        if (
                ((this.getX() < (anotherBall.getCircle().getLayoutX() + anotherBall.getCircle().getRadius())) &&
                        (this.getX() > (anotherBall.getCircle().getLayoutX() - anotherBall.getCircle().getRadius()))) &&
                        ((this.getY() < (anotherBall.getCircle().getLayoutY() + anotherBall.getCircle().getRadius())) &&
                                (this.getY() > (anotherBall.getCircle().getLayoutY() - anotherBall.getCircle().getRadius()))
                        )
                ) {
            this.dx = - this.dx;
            this.dy = - this.dy;
            return true;
        }
        return false;
    }

    public void checkTimeoutForCollision(Ball anotherBall) {
        if (this.isCheckMoving()) {
            if (anotherBall.checkCollision(this)) {
                this.setCheckMoving(false);
                this.setTime(System.currentTimeMillis());
            }
        }

        if (System.currentTimeMillis() - this.getTime() > 50 && !this.isCheckMoving()) {
            this.setCheckMoving(true);
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
        this.circle.setLayoutX(x);
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
        this.circle.setLayoutY(y);
    }

    public void setColor(Color color) {
        this.color = color;
        this.circle.setFill(color);
    }

    public double getDx() {
        return this.dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return this.dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.circle.setRadius(this.radius);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isCheckMoving() {
        return checkMoving;
    }

    public void setCheckMoving(boolean checkMoving) {
        this.checkMoving = checkMoving;
    }
}

