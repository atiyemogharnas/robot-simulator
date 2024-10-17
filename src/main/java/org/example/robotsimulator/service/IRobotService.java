package org.example.robotsimulator.service;

import org.example.robotsimulator.model.Enum.Direction;

public interface IRobotService {
    void place(int x, int y, Direction direction);

    void move();

    void turnLeft();

    void turnRight();

    String report();
}
