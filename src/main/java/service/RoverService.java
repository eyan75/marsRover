package service;

import exception.IllegalCommandException;
import exception.IllegalPositionException;
import model.Instruction;
import model.Plateau;
import model.Rover;

public class RoverService {
    private Plateau plateau;

    public RoverService(Plateau plateau) {
        this.plateau = plateau;
    }

    /*
     * Process the instructions given to a specific rover.
     * Throws an IllegalCommandException if we encounter an invalid instruction
     */
    public void processInstructions(Rover rover, String instructions) {
        if(rover.getXCoordinate() > plateau.getWidth() || rover.getYCoordinate() > plateau.getHeight()) {
            throw new IllegalPositionException(rover.getXCoordinate(), rover.getYCoordinate());
        }

        for (char c : instructions.toCharArray()) {
            String command = String.valueOf(c);
            try {
                switch (Instruction.valueOf(command)) {
                    case L:
                        rover.setHeading(rover.getHeading().getLeftDirection());
                        break;
                    case R:
                        rover.setHeading(rover.getHeading().getRightDirection());
                        break;
                    case M:
                        moveRover(rover);
                        break;
                }
            } catch(IllegalArgumentException e) {
                throw new IllegalCommandException(instructions);
            }
        }
    }

    /*
     * Move the rover in the direction it is facing. If the movement would cause the rover to fall off the plateau,
     * ignore the instruction
     */
    private void moveRover(Rover rover) {
        switch (rover.getHeading()) {
            case N:
                if(rover.getYCoordinate() != plateau.getHeight()) {
                    rover.setYCoordinate(rover.getYCoordinate() + 1);
                }
                break;
            case S:
                if(rover.getYCoordinate() != 0) {
                    rover.setYCoordinate(rover.getYCoordinate() - 1);
                }
                break;
            case E:
                if(rover.getXCoordinate() != plateau.getWidth()) {
                    rover.setXCoordinate(rover.getXCoordinate() + 1);
                }
                break;
            case W:
                if(rover.getXCoordinate() != 0) {
                    rover.setXCoordinate(rover.getXCoordinate() - 1);
                }
                break;
        }
    }
}
