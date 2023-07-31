package com.example.marsMover.model;

public class Obstacle {
    private int xCoordinate;
    private int yCoordinate;

    public Obstacle(int x,int y){
        xCoordinate=x;
        yCoordinate=y;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
