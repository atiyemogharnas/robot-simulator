package org.example.robotsimulator.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Board {
    private final int width = 5;
    private final int height = 5;
}
