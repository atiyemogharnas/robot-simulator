package org.example.robotsimulator.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.robotsimulator.model.Dto.RobotRequestDto;
import org.example.robotsimulator.model.Enum.Direction;
import org.example.robotsimulator.service.IRobotService;
import org.example.robotsimulator.service.RobotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robot")
@Slf4j
public class RobotController {

    private final IRobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/place")
    public ResponseEntity<String> place(@RequestBody RobotRequestDto robotRequestDto) {
        try {
            robotService.place(
                    robotRequestDto.getX(),
                    robotRequestDto.getY(),
                    Direction.valueOf(robotRequestDto.getDirection().toUpperCase())
            );
            String message = "Robot placed at " + robotService.report();
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            log.error("Error placing robot: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/move")
    public ResponseEntity<String> move() {
        try {
            robotService.move();
            String message = "Robot moved at " + robotService.report();
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            log.error("Error moving robot: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/left")
    public ResponseEntity<String> turnLeft() {
        try {
            robotService.turnLeft();
            String message = "Robot turned left " + robotService.report();
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            log.error("Error turning robot left: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/right")
    public ResponseEntity<String> turnRight() {
        try {
            robotService.turnRight();
            String message = "Robot turned right " + robotService.report();
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            log.error("Error turning robot right: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping("/report")
    public ResponseEntity<String> report() {
        try {
            String report = robotService.report();
            return ResponseEntity.ok(report);
        } catch (IllegalArgumentException ex) {
            log.error("Error generating robot report: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
