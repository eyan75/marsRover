package exception;

public class IllegalCommandException extends IllegalArgumentException {
    public IllegalCommandException(String command) {
        super(String.format("Invalid command: %s", command));
    }
}
