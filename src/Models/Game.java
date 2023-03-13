package Models;

import Exceptions.InvalidGameConstructionParametersException;
import Strategies.gameWinningStartegy.GameWinningStrategy;
import Strategies.gameWinningStartegy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;
    private GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    private Game(){

    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public void undo(){}
    public void makeNextMove(){
        Player toMovePlayer=players.get(nextPlayerIndex);
        System.out.println("It is"+players.get(nextPlayerIndex).getName()+"'s turn.");

        Move move=toMovePlayer.decideMove(this.board);
        int row =move.getCell().getRow();
        int col=move.getCell().getCol();
        System.out.println("MOve happened at:"+row+","+col+".");
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));
        Move finalMove=new Move(players.get(nextPlayerIndex),board.getBoard().get(row).get(col));
        this.moves.add(finalMove);
         if(gameWinningStrategy.checkWinner(
                 board,players.get(nextPlayerIndex),finalMove.getCell())){
             gameStatus=GameStatus.ENDED;
             winner=players.get(nextPlayerIndex);
         }
         nextPlayerIndex+=1;
         nextPlayerIndex %=players.size();

    }
    public void displayBoard(){
        this.board.display();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

     private boolean valid() throws InvalidGameConstructionParametersException {
         if (this.dimension < 3) {
             throw new InvalidGameConstructionParametersException("Dimension of game can't be less than 3");
         }
         if (this.players.size() != this.dimension - 1) {
             throw new InvalidGameConstructionParametersException("Number of players should be dimension-1.");
         }
         return true;
     }
     public Game build() throws InvalidGameConstructionParametersException{
        try {
            valid();
        }catch ( Exception e){
            throw new InvalidGameConstructionParametersException(e.getMessage());
        }
        Game game=new Game();
        game.setGameStatus(GameStatus.INPROGRESS);
        game.setPlayers(players);
        game.setMoves(new ArrayList<>());
        game.setBoard(new Board(dimension));
        game.setNextPlayerIndex(0);
        game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

        return game;
         }
    }
}
