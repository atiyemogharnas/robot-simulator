package org.example.robotsimulator.service;

import org.example.robotsimulator.model.Enum.Direction;
import org.example.robotsimulator.model.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotService implements IRobotService {

    private final Robot robot;
    private final IBoardService iBoardService;

    @Autowired
    public RobotService(Robot robot, IBoardService iBoardService) {
        this.robot = robot;
        this.iBoardService = iBoardService;
    }

    @Override
    public void place(int x, int y, Direction direction) {
        if (iBoardService.isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
            robot.setDirection(direction);
            robot.setPlaced(true);
        } else {
            throw new IllegalArgumentException("Invalid position: (" + x + ", " + y + ")");
        }
    }

    @Override
    public void move() {
        ensureRobotIsPlaced();
        int x = robot.getX();
        int y = robot.getY();

        switch (robot.getDirection()) {
            case NORTH:
                y += 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTH:
                y -= 1;
                break;
            case WEST:
                x -= 1;
        }

        moveTo(x, y);
    }

    @Override
    public void turnLeft() {
        ensureRobotIsPlaced();
        robot.setDirection(turnLeftDirection(robot.getDirection()));
    }

    @Override
    public void turnRight() {
        ensureRobotIsPlaced();
        robot.setDirection(turnRightDirection(robot.getDirection()));
    }

    @Override
    public String report() {
        ensureRobotIsPlaced();
        return robot.getX() + "," + robot.getY() + "," + robot.getDirection().name();
    }

    private Direction turnLeftDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.WEST;
            case WEST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private Direction turnRightDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private void ensureRobotIsPlaced() {
        if (!robot.isPlaced()) {
            throw new IllegalArgumentException("Robot is not placed on the board.");
        }
    }

    private void moveTo(int x, int y) {
        if (iBoardService.isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
        } else {
            throw new IllegalArgumentException("Move failed. Robot might be at the edge of the board.");
        }
    }
}
