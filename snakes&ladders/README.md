# Snakes and Ladders

A console-based Snake and Ladder application built in Java, making use of Object-Oriented Design principles and Design Patterns such as the Strategy Pattern.

## Features
- Dynamic board size `n x n`.
- `x` number of players rolling a six-sided dice.
- Game operates turn-by-turn.
- `n` snakes and `n` ladders are placed randomly without cycles based on the selected difficulty.
- Contains two difficulty levels: `easy` and `hard`.
- Handles edge cases where a player over-rolls the required amount to reach the final position (the player stays in place).
- The game loop ensures the game stops once `x-1` players finish and reaches the last cell.

## Class Diagram

```mermaid
classDiagram
    class Player {
        -int id
        -String name
        +getId() int
        +getName() String
    }
    class Dice {
        <<interface>>
        +roll() int
    }
    class SixSidedDice {
        -Random random
        +roll() int
    }
    Dice <|.. SixSidedDice

    class BoardEntity {
        <<abstract>>
        -int start
        -int end
        +getStart() int
        +getEnd() int
        +getEncounterMessage() String
    }
    class Snake {
        +getEncounterMessage() String
    }
    class Ladder {
        +getEncounterMessage() String
    }
    BoardEntity <|-- Snake
    BoardEntity <|-- Ladder

    class Board {
        -int size
        -int totalCells
        -Map~Integer, BoardEntity~ entities
        +getTotalCells() int
        +addEntity(BoardEntity)
        +hasEntity(int) boolean
        +getEntity(int) BoardEntity
    }
    Board "1" *-- "*" BoardEntity : contains

    class DifficultyStrategy {
        <<interface>>
        +placeEntities(Board, int)
    }
    class EasyDifficultyStrategy {
        +placeEntities(Board, int)
    }
    class HardDifficultyStrategy {
        +placeEntities(Board, int)
    }
    DifficultyStrategy <|.. EasyDifficultyStrategy
    DifficultyStrategy <|.. HardDifficultyStrategy

    class Game {
        -Board board
        -Dice dice
        -Queue~Player~ players
        -Map~Player, Integer~ playerPositions
        -List~Player~ rank
        +play()
    }
    Game o-- Board
    Game o-- Dice
    Game o-- Player

    class App {
        +main(String[])
    }
    App ..> Game : creates
```

## Setup and Execution

1. Build the source code:
```bash
javac -d bin src/models/*.java src/entities/*.java src/strategies/*.java src/*.java
```

2. Run the application:
```bash
java -cp bin App
```

3. Provide inputs for board size (`n`), player count (`x`), and difficulty (`easy` or `hard`).
