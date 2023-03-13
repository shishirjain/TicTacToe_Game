package Models;

import Strategies.botPlayingStrategy.BotPlayingStrategy;
import Factories.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot( String name,char symbol, BotDifficultyLevel difficultyLevel) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficultyLevel=difficultyLevel;
        this.botPlayingStrategy=BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public Move decideMove(Board board){
        return botPlayingStrategy.decideMove(this, board);
    }
}
