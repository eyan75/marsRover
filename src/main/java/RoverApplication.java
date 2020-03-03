import exception.IllegalCommandException;
import exception.IllegalPositionException;
import model.Direction;
import model.Plateau;
import model.Rover;
import service.RoverService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoverApplication {

    public static void main(String args[]) {
        ClassLoader classLoader = RoverApplication.class.getClassLoader();
        String inputFile;
        if (args == null || args.length == 0) {
            inputFile = classLoader.getResource("input.txt").getFile();
        } else {
            inputFile = args[0];
        }

        try {
            Scanner scanner = new Scanner(new File(inputFile));
            List<String> inputLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            processInput(inputLines);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }
    }

    private static void processInput(List<String> inputLines) {
        if (inputLines.size() % 2 == 0) {
            System.out.println("Invalid input: wrong number of lines");
            return;
        }

        Plateau plateau = getPlateau(inputLines.get(0));
        if(plateau == null) {
            return;
        }
        RoverService service = new RoverService(plateau);
        for (int i = 1; i < inputLines.size(); i += 2) {
            Rover rover = getRover(inputLines.get(i));
            if(rover == null) {
                return;
            }
            String command = inputLines.get(i + 1);
            try {
                service.processInstructions(rover, command);
            } catch(IllegalPositionException | IllegalCommandException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println(String.format("%s %s %s", rover.getXCoordinate(), rover.getYCoordinate(), rover.getHeading()));
        }
    }

    private static Plateau getPlateau(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid plateau: " + input);
            return null;
        }

        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        if (width <= 0 || height <= 0) {
            System.out.println(String.format("Invalid plateau dimensions: %s %s", width, height));
            return null;
        }
        return new Plateau(width, height);
    }

    private static Rover getRover(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid rover: " + input);
            return null;
        }

        int xCoordinate = 0;
        int yCoordinate = 0;
        Direction direction = null;
        try {
            xCoordinate = Integer.parseInt(parts[0]);
            yCoordinate = Integer.parseInt(parts[1]);
            direction = Direction.valueOf(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println(String.format("Invalid rover position: %s, %s", parts[0], parts[1]));
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rover direction: " + parts[2]);
            return null;
        }
        return new Rover(xCoordinate, yCoordinate, direction);
    }
}
