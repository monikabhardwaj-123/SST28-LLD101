package models;

import java.util.HashMap;
import java.util.Map;
import entities.BoardEntity;

public class Board {
    private int size;
    private int totalCells;
    private Map<Integer, BoardEntity> entities;

    public Board(int size) {
        this.size = size;
        this.totalCells = size * size;
        this.entities = new HashMap<>();
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void addEntity(BoardEntity entity) {
        entities.put(entity.getStart(), entity);
    }

    public boolean hasEntity(int position) {
        return entities.containsKey(position);
    }

    public BoardEntity getEntity(int position) {
        return entities.get(position);
    }
}
