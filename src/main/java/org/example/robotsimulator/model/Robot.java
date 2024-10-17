package org.example.robotsimulator.model;

import lombok.Data;
import org.example.robotsimulator.model.Enum.Direction;
import org.springframework.stereotype.Component;

@Data
@Component
public class Robot {
    private int x = -1;
    private int y = -1;
    private Direction direction;
    private boolean placed = false;
}

