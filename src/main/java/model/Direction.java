package model;

public enum Direction {
    N,
    S,
    E,
    W;

    private Direction leftDirection;
    private Direction rightDirection;

    static {
        N.rightDirection = E;
        N.leftDirection = W;
        S.rightDirection = W;
        S.leftDirection = E;
        E.rightDirection = S;
        E.leftDirection = N;
        W.rightDirection = N;
        W.leftDirection = S;
    }
    public Direction getLeftDirection() {
        return leftDirection;
    }

    public Direction getRightDirection() {
        return rightDirection;
    }
    }
