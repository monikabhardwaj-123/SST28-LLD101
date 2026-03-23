import models.Board;
import models.Dice;
import models.SixSidedDice;
import models.Player;
import strategies.DifficultyStrategy;
import strategies.EasyDifficultyStrategy;
import strategies.HardDifficultyStrategy;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n (size of board n x n, e.g. 10): ");
        int n = scanner.nextInt();
        
        System.out.println("Enter x (number of players): ");
        int x = scanner.nextInt();
        
        System.out.println("Enter difficulty level (easy/hard): ");
        String difficulty = scanner.next();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            players.add(new Player(i, "Player " + i));
        }

        Board board = new Board(n);
        Dice dice = new SixSidedDice();

        DifficultyStrategy strategy;
        if (difficulty.equalsIgnoreCase("hard")) {
            strategy = new HardDifficultyStrategy();
        } else {
            strategy = new EasyDifficultyStrategy();
        }

        Game game = new Game(board, dice, players, strategy, n);
        game.play();
        
        scanner.close();
    }
}
