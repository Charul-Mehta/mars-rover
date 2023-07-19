package com.example.marsMover.model;

public class Position {
    private Direction direction;
    private Integer xCoordinate;
    private Integer yCoordinate;
    Position(Direction d, int xCord, int yCord){
        this.direction=d;
        this.yCoordinate=yCord;
        this.xCoordinate=xCord;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
