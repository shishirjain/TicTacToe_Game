import Controllers.GameController;
import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        GameController gameController=new GameController();
        System.out.println("What should be the dimesion of the game?");
        int dimension=sc.nextInt();

        System.out.println("Will there be bot? Y/N");
        String isBotString=sc.next();
        List<Player> players=new ArrayList<>();
        int toIterate=dimension-1;
        if(isBotString.equals("Y")){
            toIterate=dimension-2;
        }
        for( int i=0;i<toIterate;i++){
            System.out.println("what is the name of player"+i);
            String playerName=sc.next();

            System.out.println("what is the symbol of player"+i);
            String playerSymbol=sc.next();

            players.add(new Bot(playerName,playerSymbol.charAt(0),BotDifficultyLevel.EASY));
        }
        Game game=gameController.createGame(dimension,players);
        while(gameController.getGameStatus(game).equals((GameStatus.INPROGRESS))){
            System.out.println("this is the current board");

            gameController.displayBoard(game);
            System.out.println("does anyone want to undo?Y/N");
            String input=sc.next();

            if(input.equals("Y")){
                gameController.undo(game);
            }else{
                gameController.executeNextMove(game);
            }
        }
        System.out.println("game has ended. Result is:");
        if(!game.getGameStatus().equals(GameStatus.DRAW)){
            System.out.println("Winner is:"+gameController.getWinner(game).getName());
        }
    }
}
