package Model;


import java.util.HashSet;
import java.util.Set;

public class GameManager {
    private static GameManager instance;
    private Set<GameState> savedGames;

    private GameManager() {
        savedGames = new HashSet<>();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Set<GameState> getSavedGames() {
        return savedGames;
    }

    public void addSavedGame(GameState gameState) {
        savedGames.add(gameState);
    }
}
