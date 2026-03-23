package strategies;

import models.Board;
import entities.Snake;
import entities.Ladder;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class HardDifficultyStrategy implements DifficultyStrategy {
    private Random random = new Random();

    @Override
    public void placeEntities(Board board, int n) {
        int totalCells = board.getTotalCells();
        Set<Integer> usedPositions = new HashSet<>();
        usedPositions.add(totalCells);

        int snakesPlaced = 0;
        int maxAttempts = 1000;
        int attempts = 0;
        while (snakesPlaced < n && attempts < maxAttempts) {
            attempts++;
            int start = random.nextInt(totalCells - 2) + 2;
            int end = random.nextInt(start - 1) + 1;
            
            if (start - end < 15 && start > 20) {
                end = Math.max(1, start - 15 - random.nextInt(10));
            }

            if (!usedPositions.contains(start) && !usedPositions.contains(end)) {
                board.addEntity(new Snake(start, end));
                usedPositions.add(start);
                usedPositions.add(end);
                snakesPlaced++;
            }
        }

        int laddersPlaced = 0;
        attempts = 0;
        while (laddersPlaced < n && attempts < maxAttempts) {
            attempts++;
            int start = random.nextInt(totalCells - 2) + 2; 
            int end = start + random.nextInt(totalCells - start) + 1;
            
            if (end - start > 15) {
                end = Math.min(totalCells, start + random.nextInt(10) + 1);
            }

            if (!usedPositions.contains(start) && !usedPositions.contains(end)) {
                board.addEntity(new Ladder(start, end));
                usedPositions.add(start);
                usedPositions.add(end);
                laddersPlaced++;
            }
        }
    }
}
