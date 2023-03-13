package Strategies.gameWinningStartegy;

import Models.Board;
import Models.Cell;
import Models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell);
}
