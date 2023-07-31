package com.example.marsMover.model;

public interface Rover {

    Position getRoverPosition();

    MovementResponse moveRoverForwardOrBackward(char charSequence);

    MovementResponse turnRoverLeftAndRight(char charSequence);

    MovementResponse moveRover(CharSequence charSequence);
}
