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

public class Main {

    public static void main(String args[]) {
        ClassLoader classLoader = Main.class.getClassLoader();
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
            System.exit(0);
        }
    }

    private static void processInput(List<String> inputLines) {
        if (inputLines.size() % 2 == 0) {
            System.out.println("Invalid input: wrong number of lines");
            System.exit(0);
        }

        RoverService service = new RoverService(getPlateau(inputLines.get(0)));
        for (int i = 1; i < inputLines.size(); i += 2) {
            Rover rover = getRover(inputLines.get(i));
            String command = inputLines.get(i + 1);
            try {
                service.processInstructions(rover, command);
            } catch(IllegalPositionException | IllegalCommandException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
            System.out.println(String.format("%s %s %s", rover.getXCoordinate(), rover.getYCoordinate(), rover.getHeading()));
        }
    }

    private static Plateau getPlateau(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid plateau");
            System.exit(0);
        }

        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        if (width <= 0 || height <= 0) {
            System.out.println(String.format("Invalid plateau dimenesions: %s %s", width, height));
            System.exit(0);
        }
        return new Plateau(width, height);
    }

    private static Rover getRover(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid rover");
            System.exit(0);
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
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rover direction: " + parts[2]);
            System.exit(0);
        }
        return new Rover(xCoordinate, yCoordinate, direction);
    }
}
