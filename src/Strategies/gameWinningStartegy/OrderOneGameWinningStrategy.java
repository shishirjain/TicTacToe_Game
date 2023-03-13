package Strategies.gameWinningStartegy;

import Models.Board;
import Models.Cell;
import Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowSymbolCounts=new ArrayList<>();
    private List<HashMap<Character,Integer>>  colSymbolCounts=new ArrayList<>();
    private HashMap<Character,Integer> leftDiagSymbolCounts=new HashMap<>();
    private HashMap<Character,Integer> rightDiagSymbolCounts=new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension){
        for( int i=0;i<dimension;i++){
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap());
        }
    }
    public boolean isCellLeftDiag(int row,int col){
        return row==col;
    }
    public boolean isCellRightDiag(int row,int col,int dimension){
        return row+col==dimension-1;
    }
    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        char symbol=moveCell.getPlayer().getSymbol();
        int row =moveCell.getRow();
        int col=moveCell.getCol();
        int dimension=board.getBoard().size();
        if(!rowSymbolCounts.get(row).containsKey(symbol)){
            rowSymbolCounts.get(row).put(symbol,0);
        }
        rowSymbolCounts.get(row).put(symbol,rowSymbolCounts.get(row).get(symbol)+1);
        if(!colSymbolCounts.get(row).containsKey(symbol)){
            colSymbolCounts.get(row).put(symbol,0);
        }
        colSymbolCounts.get(row).put(symbol,colSymbolCounts.get(row).get(symbol)+1);

        if( isCellLeftDiag(row,col)){
            if(!leftDiagSymbolCounts.containsKey(symbol)){
                leftDiagSymbolCounts.put(symbol,0);
            }
            leftDiagSymbolCounts.put(symbol,leftDiagSymbolCounts.get(symbol)+1);

        }

        if( isCellRightDiag(row,col,dimension)){
            if(!rightDiagSymbolCounts.containsKey(symbol)){
                rightDiagSymbolCounts.put(symbol,0);
            }
            rightDiagSymbolCounts.put(symbol,rightDiagSymbolCounts.get(symbol)+1);

        }

        if(rowSymbolCounts.get(row).get(symbol)==dimension||
            colSymbolCounts.get(row).get(symbol)==dimension){
            return true;
        }
        if(isCellRightDiag(row,col,dimension)&& rightDiagSymbolCounts.get(symbol)==dimension){
            return true;
        }
        if(isCellLeftDiag(row,col)&& leftDiagSymbolCounts.get(symbol)==dimension){
            return true;
        }
        return false;
    }
}
