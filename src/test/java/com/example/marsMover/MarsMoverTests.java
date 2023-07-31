package com.example.marsMover;

import com.example.marsMover.model.Direction;
import com.example.marsMover.model.MarsRover;
import com.example.marsMover.model.MovementResponse;
import com.example.marsMover.model.Obstacle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MarsMoverTests {

    @Test
    void testMarsRoversPositionAtStart() {
        MarsRover rover=new MarsRover();

        Assertions.assertEquals(rover.getRoverPosition().getXCoordinate(),0);
        Assertions.assertEquals(rover.getRoverPosition().getYCoordinate(),0);
        Assertions.assertEquals(rover.getRoverPosition().getDirection(), Direction.E);
    }

    @Test
    void testMarsRoversPositionAtStartWhenProvidedByUser() {
        MarsRover rover=new MarsRover(Direction.W,2,3);

        Assertions.assertEquals(2,rover.getRoverPosition().getXCoordinate());
        Assertions.assertEquals(3,rover.getRoverPosition().getYCoordinate());
        Assertions.assertEquals(Direction.W,rover.getRoverPosition().getDirection());
    }

    @Test
    void testMarsRoversPositionValidFBMovement() {
        MarsRover rover=new MarsRover(Direction.E,2,3);
        MovementResponse response= rover.moveRoverForwardOrBackward('f');

        Assertions.assertEquals("Success",response.getSuccess());
        Assertions.assertEquals("Moved",response.getMessage());
        Assertions.assertEquals(3,response.getCurrentPosition().getXCoordinate());
        Assertions.assertEquals(3,response.getCurrentPosition().getYCoordinate());
        Assertions.assertEquals(Direction.E,response.getCurrentPosition().getDirection());
    }

    @Test
    void testMarsRoversPositionValidLRMovement() {
        MarsRover rover=new MarsRover(Direction.E,2,3);
        MovementResponse response= rover.turnRoverLeftAndRight('l');

        Assertions.assertEquals("Success",response.getSuccess());
        Assertions.assertEquals("Moved",response.getMessage());
        Assertions.assertEquals(2,response.getCurrentPosition().getXCoordinate());
        Assertions.assertEquals(3,response.getCurrentPosition().getYCoordinate());
        Assertions.assertEquals(Direction.N,response.getCurrentPosition().getDirection());

    }

    @Test
    void testMarsRoversPositionAfterMoveRover() {
        MarsRover rover=new MarsRover(Direction.W,2,3);
        MovementResponse response= rover.moveRover("");

        Assertions.assertEquals("Success", response.getSuccess());
        Assertions.assertEquals("No movement", response.getMessage());
        Assertions.assertEquals(2,response.getCurrentPosition().getXCoordinate());
        Assertions.assertEquals(3,response.getCurrentPosition().getYCoordinate());
        Assertions.assertEquals(Direction.W,response.getCurrentPosition().getDirection());
    }

    @Test
    void testMarsRoversPositionAfterBigMoveRover() {
        MarsRover rover=new MarsRover(Direction.E,2,3);
        MovementResponse response= rover.moveRover("ffbbff");

        Assertions.assertEquals("Success", response.getSuccess());
        Assertions.assertEquals("Moved", response.getMessage());
        Assertions.assertEquals(4,response.getCurrentPosition().getXCoordinate());
        Assertions.assertEquals(3,response.getCurrentPosition().getYCoordinate());
        Assertions.assertEquals(Direction.E,response.getCurrentPosition().getDirection());

        response= rover.moveRover("lrlrl");
        Assertions.assertEquals(Direction.N,response.getCurrentPosition().getDirection());

        response= rover.moveRover("llff");
        Assertions.assertEquals(4,response.getCurrentPosition().getXCoordinate());
        Assertions.assertEquals(1,response.getCurrentPosition().getYCoordinate());
        Assertions.assertEquals(Direction.S,response.getCurrentPosition().getDirection());
    }

    @Test
    void testMarsRoversPositionAfterBigMoveRoverWithObstacle() {
        Obstacle[] obstacles={new Obstacle(3,3)};
        MarsRover rover=new MarsRover(Direction.E,2,3,obstacles);
        MovementResponse response= rover.moveRover("f");
        Assertions.assertEquals("Failure", response.getSuccess());
        Assertions.assertEquals("Encountered obstacle", response.getMessage());
    }

    @Test
    void testMarsRoversPositionAfterBackwardWithObstacle() {
        Obstacle[] obstacles={new Obstacle(1,3)};
        MarsRover rover=new MarsRover(Direction.E,2,3,obstacles);
        MovementResponse response= rover.moveRover("b");
        Assertions.assertEquals("Failure", response.getSuccess());
        Assertions.assertEquals("Encountered obstacle", response.getMessage());
    }

    @Test
    void testMarsRoversForObstacle() {
        MarsRover rover=new MarsRover();
        boolean result =rover.obstacleCheck(0,0);
        Assertions.assertFalse(result);
    }
}
