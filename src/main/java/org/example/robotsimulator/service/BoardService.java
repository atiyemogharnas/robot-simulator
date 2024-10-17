package org.example.robotsimulator.service;

import org.example.robotsimulator.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService implements IBoardService {

    private final Board board;

    @Autowired
    public BoardService(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight();
    }

}
