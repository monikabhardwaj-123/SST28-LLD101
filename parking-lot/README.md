# Multi-Level Parking Lot LLD

An Object-Oriented design implementation of a Multi-Level Parking Lot system accommodating scalable architectural concepts like Strategy Patterns & Thread-Safe Resource Locking.

## Features Design Support
- A **ParkingLotService** controlling entry-exit APIs.
- Accommodates N floors and N gates simultaneously across 3 sizing structures (`SMALL`, `MEDIUM`, `LARGE`).
- Resolves the "Nearest physical slot" routing automatically through an intelligent **DistanceMatrix** mapping algorithm.
- Robust concurrent thread safety via object-level intrinsic locks (`synchronized` block upon `Slot` instances) avoiding severe global bottlenecks while protecting identical slot reservations.
- Extensible fee-calculation logic via implemented behavior strategies.

## System Architecture Class Diagram

```mermaid
classDiagram
    class Vehicle {
        -String number
        -String color
        -String model
        -VehicleType type
    }
    class Slot {
        -String id
        -int floorId
        -SlotType type
        -boolean isAvailable
        +reserve() boolean
        +release()
    }
    class Gate {
        -String id
        -int floorId
        -GateType type
    }
    class Ticket {
        -String id
        -Vehicle vehicle
        -Slot slot
        -Gate entryGate
        -Date inTime
    }
    class Bill {
        -String id
        -Ticket ticket
        -Gate exitGate
        -Date outTime
        -double amount
    }

    class DistanceMatrix {
        +addDistance(String, String, double)
        +getDistance(String, String) double
    }

    class SlotAssignmentStrategy {
        <<interface>>
        +getSlot(Gate, SlotType, List~Slot~) Slot
    }
    class NearestSlotAssignmentStrategy {
        +getSlot(Gate, SlotType, List~Slot~) Slot
    }
    SlotAssignmentStrategy <|.. NearestSlotAssignmentStrategy

    class FeeCalculationStrategy {
        <<interface>>
        +generateBill(Ticket, Gate, Date) Bill
    }
    class DynamicDurationFeeStrategy {
        +generateBill(Ticket, Gate, Date) Bill
    }
    FeeCalculationStrategy <|.. DynamicDurationFeeStrategy

    class ParkingLotService {
        +generateParkingTicket(...) Ticket
        +generateBill(...) Bill
        +showStatus()
    }
    
    ParkingLotService o-- SlotAssignmentStrategy
    ParkingLotService o-- FeeCalculationStrategy
    ParkingLotService o-- Slot
    NearestSlotAssignmentStrategy o-- DistanceMatrix
    Ticket o-- Vehicle
    Ticket o-- Slot
    Ticket o-- Gate
    Bill o-- Ticket
    Bill o-- Gate
```

## Running Example 
1. Build code:
```bash
javac -d bin src/models/*.java src/services/*.java src/strategies/*.java src/*.java
```

2. Run testing entry point (includes simulated Multi-Threading Concurrency check):
```bash
java -cp bin App
```
