# Pen Modular Framework
A SOLID Java-based design for a multi-trait Pen system constructed via the Strategy and Factory patterns.

## Features Design Support
- A **Pen** natively separates its internal logic into two primary interfaces: `WritingBehavior` and `OpeningBehavior` (Bridged Pattern / Strategy Composition).
- Provides distinct and dynamically loadable rules for how writing and refilling function natively (`Ball`, `Gel`, `Ink`).
- Distinguishes operations for opening/closing the pen cleanly between `Cap` and `Click` styles without subclass generation limits (no `BlueClickBallPen` generated class bloat).
- Driven easily via an extendable `PenFactory`.

## System Architecture Class Diagram

```mermaid
classDiagram
    class Pen {
        -String color
        -WritingBehavior writingBehavior
        -OpeningBehavior openingBehavior
        +start()
        +close()
        +write()
        +refill(String newColor)
        +getColor() String
    }

    class PenFactory {
        +createPen(String type, String color, boolean withCap) Pen$
    }

    class WritingBehavior {
        <<interface>>
        +write(String color)
        +refill(String color)
    }

    class BallWritingBehavior {
        +write(String color)
        +refill(String color)
    }

    class GelWritingBehavior {
        +write(String color)
        +refill(String color)
    }

    class InkWritingBehavior {
        +write(String color)
        +refill(String color)
    }

    WritingBehavior <|.. BallWritingBehavior
    WritingBehavior <|.. GelWritingBehavior
    WritingBehavior <|.. InkWritingBehavior

    class OpeningBehavior {
        <<interface>>
        +start()
        +close()
    }

    class CapOpeningBehavior {
        +start()
        +close()
    }

    class ClickOpeningBehavior {
        +start()
        +close()
    }

    OpeningBehavior <|.. CapOpeningBehavior
    OpeningBehavior <|.. ClickOpeningBehavior

    Pen *-- WritingBehavior : uses
    Pen *-- OpeningBehavior : uses
    PenFactory ..> Pen : creates

    class App {
        +main(String[] args)$
    }
    App ..> PenFactory : uses
```

## Running Example 
1. Build code:
```bash
javac -d bin src/behaviors/*.java src/core/*.java src/factory/*.java src/*.java
```

2. Run testing entry point:
```bash
java -cp bin App
```
