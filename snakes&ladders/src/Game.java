import models.Board;
import models.Dice;
import models.Player;
import entities.BoardEntity;
import strategies.DifficultyStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private Map<Player, Integer> playerPositions;
    private List<Player> rank;

    public Game(Board board, Dice dice, List<Player> playerList, DifficultyStrategy strategy, int n) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.playerPositions = new HashMap<>();
        this.rank = new LinkedList<>();

        for (Player p : playerList) {
            playerPositions.put(p, 0);
        }

        strategy.placeEntities(board, n);
    }

    public void play() {
        while (players.size() > 1) {
            Player currentPlayer = players.poll();
            int currentPos = playerPositions.get(currentPlayer);
            int diceVal = dice.roll();
            
            int newPos = currentPos + diceVal;
            
            if (newPos > board.getTotalCells()) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceVal + " and cannot move beyond " + board.getTotalCells() + " (remaining at " + currentPos + ")");
                players.add(currentPlayer);
                continue;
            }

            System.out.println(currentPlayer.getName() + " rolled a " + diceVal + " and moved from " + currentPos + " to " + newPos);
            
            if (board.hasEntity(newPos)) {
                BoardEntity entity = board.getEntity(newPos);
                System.out.println("  " + currentPlayer.getName() + " " + entity.getEncounterMessage());
                newPos = entity.getEnd();
            }

            playerPositions.put(currentPlayer, newPos);

            if (newPos == board.getTotalCells()) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                rank.add(currentPlayer);
            } else {
                players.add(currentPlayer);
            }
        }
        
        if (players.size() == 1) {
            System.out.println(players.poll().getName() + " lost the game.");
        } else {
            System.out.println("Game Over.");
        }
    }
}
