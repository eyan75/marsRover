package exception;

public class IllegalPositionException extends IllegalArgumentException{
    public IllegalPositionException(int x, int y) {
        super(String.format("Illegal position x:%s y:%s", x, y));
    }
}
