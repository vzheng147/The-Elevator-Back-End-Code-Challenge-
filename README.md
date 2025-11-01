# Elevator Simulation

## Overview
This project implements a simplified elevator system that simulates the movement and scheduling of an elevator serving multiple floors. The system handles passenger pickup requests, destination selections, and processes both upward and downward movements.

## Features
- **Request Queue Management:** Maintains separate hash maps for UP and DOWN service calls.
- **Passenger Tracking:** Tracks passengers from pickup to their destination floor.
- **Floor-by-Floor Processing:** Stops at each relevant floor to pick up and drop off passengers.
- **Status Logging:** Prints detailed console output of elevator operations to show the order of events.

## Core Components
- **Direction States:** The elevator operates in three states: `UP`, `DOWN`, or `IDLE`.
- **Service Calls:** Passengers request service from specific floors with a direction (`requestService(personId, floor, direction)`).
- **Destination Selection:** Once aboard, passengers select their target floor (`selectFloor(personId, targetFloor)`).
- **Processing Logic:** The elevator services **all UP requests first**, then services **all DOWN requests**. This mirrors a simple “finish current direction, then reverse” strategy.

## Assumptions
- There will always be at least **one UP request** before any DOWN requests are processed.
- Single elevator only.
- Elevator moves **one floor at a time**.
- Passenger destinations are known (via `selectFloor(...)`) before processing begins.
- No door timing, capacity limits, or safety/emergency modes are modeled.

## Implementations
- Hashmaps are used for O(1) insert, get, and remove keys
