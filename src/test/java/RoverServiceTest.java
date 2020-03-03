import exception.IllegalCommandException;
import exception.IllegalPositionException;
import model.Direction;
import model.Plateau;
import model.Rover;
import org.junit.Test;
import service.RoverService;

import static org.junit.Assert.assertEquals;

public class RoverServiceTest {
    @Test
    public void turnLeft() {
        Rover rover = new Rover(2, 2, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "L";
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.W, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.S, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.E, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.N, rover.getHeading());
        assertEquals(2, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
    }

    @Test
    public void turnRight() {
        Rover rover = new Rover(2, 2, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "R";
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.E, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.S, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.W, rover.getHeading());
        roverService.processInstructions(rover, instructions);
        assertEquals(Direction.N, rover.getHeading());
        assertEquals(2, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
    }

    @Test
    public void moveNorth() {
        Rover rover = new Rover(2, 2, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "M";
        roverService.processInstructions(rover, instructions);
        assertEquals(2, rover.getXCoordinate());
        assertEquals(3, rover.getYCoordinate());
        assertEquals(Direction.N, rover.getHeading());
    }

    @Test
    public void moveNorthStaysOnMap() {
        Rover rover = new Rover(2, 2, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMMMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(2, rover.getXCoordinate());
        assertEquals(5, rover.getYCoordinate());
        assertEquals(Direction.N, rover.getHeading());
    }

    @Test
    public void moveSouth() {
        Rover rover = new Rover(2, 2, Direction.S);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "M";
        roverService.processInstructions(rover, instructions);
        assertEquals(2, rover.getXCoordinate());
        assertEquals(1, rover.getYCoordinate());
        assertEquals(Direction.S, rover.getHeading());
    }

    @Test
    public void moveSouthStaysOnMap() {
        Rover rover = new Rover(2, 2, Direction.S);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMMMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(2, rover.getXCoordinate());
        assertEquals(0, rover.getYCoordinate());
        assertEquals(Direction.S, rover.getHeading());
    }

    @Test
    public void moveEast() {
        Rover rover = new Rover(2, 2, Direction.E);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "M";
        roverService.processInstructions(rover, instructions);
        assertEquals(3, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
        assertEquals(Direction.E, rover.getHeading());
    }

    @Test
    public void moveEastStaysOnMap() {
        Rover rover = new Rover(2, 2, Direction.E);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMMMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(5, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
        assertEquals(Direction.E, rover.getHeading());
    }

    @Test
    public void moveWest() {
        Rover rover = new Rover(2, 2, Direction.W);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "M";
        roverService.processInstructions(rover, instructions);
        assertEquals(1, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
        assertEquals(Direction.W, rover.getHeading());
    }

    @Test
    public void moveWestStaysOnMap() {
        Rover rover = new Rover(2, 2, Direction.W);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMMMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(0, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
        assertEquals(Direction.W, rover.getHeading());
    }

    @Test
    public void complexInstruction() {
        Rover rover = new Rover(0, 0, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "LMLMLMLMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(1, rover.getXCoordinate());
        assertEquals(2, rover.getYCoordinate());
        assertEquals(Direction.N, rover.getHeading());
    }

    @Test
    public void staysOnMapKeepsMoving() {
        Rover rover = new Rover(4, 4, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMLMM";
        roverService.processInstructions(rover, instructions);
        assertEquals(2, rover.getXCoordinate());
        assertEquals(5, rover.getYCoordinate());
        assertEquals(Direction.W, rover.getHeading());
    }

    @Test(expected = IllegalPositionException.class)
    public void badStartingPosition() {
        Rover rover = new Rover(7, 1, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "MMMMMM";
        roverService.processInstructions(rover, instructions);
    }

    @Test(expected = IllegalCommandException.class)
    public void badInstructions() {
        Rover rover = new Rover(0, 0, Direction.N);
        Plateau plateau = new Plateau(5, 5);
        RoverService roverService = new RoverService(plateau);

        String instructions = "S";
        roverService.processInstructions(rover, instructions);
    }
}
