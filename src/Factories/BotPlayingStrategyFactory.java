package Factories;

import Models.BotDifficultyLevel;
import Strategies.botPlayingStrategy.BotPlayingStrategy;
import Strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        return new RandomBotPlayingStrategy();
    }
}
