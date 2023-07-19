package com.example.marsMover.model;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarsRover implements Rover {
    private Position roverPosition;

    private Obstacle[] obstacles;

    public MarsRover() {
        roverPosition = new Position(Direction.E, 0, 0);
    }

    public MarsRover(Direction direction, int xCord, int yCord) {
        roverPosition = new Position(direction, xCord, yCord);
    }

    public MarsRover(Direction direction, int xCord, int yCord,Obstacle[] obstacles) {
        roverPosition = new Position(direction, xCord, yCord);
        this.obstacles=obstacles;
    }

    @Override
    public Position getRoverPosition() {
        return roverPosition;
    }

    @Override
    public MovementResponse moveRoverForwardOrBackward(char c) {
        MovementResponse response = new MovementResponse();
        response.setCurrentPosition(roverPosition);

        if (c == 'f' || c == 'b') {
            switch (roverPosition.getDirection()) {
                case E:
                    if (c == 'f') {
                        xCordAddition(response);
                    } else {
                        xCordSubtraction(response);
                    }
                    break;
                case N:
                    if (c == 'f') {
                        yCordAddition(response);
                    } else {
                        yCordSubtraction(response);
                    }
                    break;
                case W:
                    if (c == 'b') {
                        xCordAddition(response);
                    } else {
                        xCordSubtraction(response);
                    }
                    break;
                case S:
                    if (c == 'b') {
                        yCordAddition(response);
                    } else {
                        yCordSubtraction(response);
                    }
                    break;
            }
            if(response.getSuccess()==null){
                response.setSuccess("Success");
                response.setMessage("Moved");
            }

        }
        return response;
    }



    @Override
    public MovementResponse turnRoverLeftAndRight(char c) {
        MovementResponse response = new MovementResponse();
        response.setCurrentPosition(roverPosition);

        if (c == 'l' || c == 'r') {
            switch (roverPosition.getDirection()) {
                case E:
                    if (c == 'l') {
                        roverPosition.setDirection(Direction.N);
                    } else {
                        roverPosition.setDirection(Direction.S);
                    }
                    break;
                case N:
                    if (c == 'l') {
                        roverPosition.setDirection(Direction.W);
                    } else {
                        roverPosition.setDirection(Direction.E);
                    }
                    break;
                case W:
                    if (c == 'r') {
                        roverPosition.setDirection(Direction.N);
                    } else {
                        roverPosition.setDirection(Direction.S);
                    }
                    break;
                case S:
                    if (c == 'r') {
                        roverPosition.setDirection(Direction.W);
                    } else {
                        roverPosition.setDirection(Direction.E);
                    }
                    break;
            }
            response.setSuccess("Success");
            response.setMessage("Moved");
        }

        return response;
    }

    @Override
    public MovementResponse moveRover(CharSequence charSequence) {
        MovementResponse response = new MovementResponse();
        response.setCurrentPosition(roverPosition);
        if (charSequence.length() == 0) {
            response.setSuccess("Success");
            response.setMessage("No movement");
        } else if (!isValidInput(charSequence)) {
            response.setSuccess("Failure");
            response.setMessage("Unacceptable Input");
        }
        for (int i = 0; i < charSequence.length(); i++) {
            char c = charSequence.charAt(i);
            if (c == 'l' || c == 'r') {
                response = turnRoverLeftAndRight(c);
            } else if (c == 'f' || c == 'b') {
                response = moveRoverForwardOrBackward(c);
            }
            if(Objects.equals(response.getSuccess(), "Failure")){
                break;
            }
        }

        return response;
    }

    public void setRoverPosition(Position roverPosition) {
        this.roverPosition = roverPosition;
    }



    public Obstacle[] getObstacles() {
        return obstacles;
    }

    public void setObstacles(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public boolean isValidInput(CharSequence inputSeq) {
        String pattern = "^[flrb]+$";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(inputSeq);
        return matcher.matches();
    }

    public boolean obstacleCheck(int xCord, int yCord){
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getXCoordinate() == xCord && obstacle.getYCoordinate() == yCord) {
                return true;
            }
        }
        return false;
    }

    private void xCordAddition(MovementResponse response) {
        if(this.obstacles==null || !obstacleCheck(roverPosition.getXCoordinate()+1,roverPosition.getYCoordinate()))
            roverPosition.setXCoordinate(roverPosition.getXCoordinate() + 1);
        else{
            response.setSuccess("Failure");
            response.setMessage("Encountered obstacle");
        }
    }

    private void xCordSubtraction(MovementResponse response) {
        if(this.obstacles==null || !obstacleCheck(roverPosition.getXCoordinate()-1,roverPosition.getYCoordinate()))
            roverPosition.setXCoordinate(roverPosition.getXCoordinate() - 1);
        else{
            response.setSuccess("Failure");
            response.setMessage("Encountered obstacle");
        }
    }

    private void yCordAddition(MovementResponse response) {
        if(this.obstacles==null || !obstacleCheck(roverPosition.getXCoordinate(),roverPosition.getYCoordinate()+1))
            roverPosition.setYCoordinate(roverPosition.getYCoordinate() + 1);
        else{
            response.setSuccess("Failure");
            response.setMessage("Encountered obstacle");
        }
    }

    private void yCordSubtraction(MovementResponse response) {
        if(this.obstacles==null || !obstacleCheck(roverPosition.getXCoordinate(),roverPosition.getYCoordinate()-1))
            roverPosition.setYCoordinate(roverPosition.getYCoordinate() - 1);
        else{
            response.setSuccess("Failure");
            response.setMessage("Encountered obstacle");
        }
    }
}
