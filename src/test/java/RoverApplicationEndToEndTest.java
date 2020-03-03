import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RoverApplicationEndToEndTest {
    private ClassLoader classLoader = getClass().getClassLoader();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void endToEndTest() {
        String fileInput = classLoader.getResource("EndToEnd.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(2, output.length);
        assertEquals("1 3 N", output[0]);
        assertEquals("5 1 E", output[1]);
    }


    @Test
    public void invalidRover() {
        String fileInput = classLoader.getResource("InvalidRover.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid rover: 2 2 N 3", output[0]);
    }

    @Test
    public void invalidRoverPosition() {
        String fileInput = classLoader.getResource("InvalidRoverPosition.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid rover position x:4 y:2", output[0]);
    }

    @Test
    public void invalidRoverDirection() {
        String fileInput = classLoader.getResource("InvalidRoverDirection.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid rover direction: G", output[0]);
    }

    @Test
    public void invalidLines() {
        String fileInput = classLoader.getResource("InvalidLines.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid input: wrong number of lines", output[0]);
    }

    @Test
    public void invalidPlateau() {
        String fileInput = classLoader.getResource("InvalidPlateau.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid plateau: 3 3 2", output[0]);
    }

    @Test
    public void invalidPlateauDimensions() {
        String fileInput = classLoader.getResource("InvalidPlateauDimensions.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid plateau dimensions: 3 0", output[0]);
    }

    @Test
    public void invalidCommand() {
        String fileInput = classLoader.getResource("InvalidCommand.txt").getFile();

        RoverApplication.main(new String[]{fileInput});

        String[] output = outContent.toString().split("\\r?\\n");
        assertEquals(1, output.length);
        assertEquals("Invalid command: MMMS", output[0]);
    }
}
