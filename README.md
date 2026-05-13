# JavaTetris

JavaTetris is a Java Swing prototype of the classic Tetris game. The project explores grid-based rendering, keyboard input, timed gravity, board state, and tetromino movement in a small desktop application.

## Status

This repository is a work in progress. The core Swing window, board rendering, gravity timer, and basic movement controls are in place. Piece definitions, rotations, row clearing, scoring, and full game-over handling are planned next steps.

## Features

- Desktop game window built with Java Swing
- Board rendering with a 2D grid representation
- Timer-based falling behavior using `javax.swing.Timer`
- Keyboard controls for left, right, and soft-drop movement
- Separate classes for the game frame, board logic, and shape management

## Project Structure

| File | Purpose |
| --- | --- |
| `Game.java` | Creates the main Swing window and starts the game. |
| `Board.java` | Handles board drawing, gravity, collision checks, and keyboard input. |
| `Shapes.java` | Manages tetromino shape selection and future shape definitions. |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or newer

### Run Locally

```bash
javac Game.java Board.java Shapes.java
java Game
```

## Controls

| Key | Action |
| --- | --- |
| `A` or Left Arrow | Move piece left |
| `D` or Right Arrow | Move piece right |
| `S` or Down Arrow | Soft drop |

## Roadmap

- Complete all tetromino shape definitions
- Add rotation support
- Improve collision detection around walls and stacked pieces
- Add completed-line clearing
- Add scoring and game-over states
- Add a short gameplay demo or screenshot

## License

No license file is currently included.
