package model;

public class Plateau {
    // length in x direction
    private final int width;
    // length in y direction
    private final int height;

    public Plateau(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
