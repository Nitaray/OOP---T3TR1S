# OOP - T3TR1S

T3tr1s is the tile-matching puzzle game where tetromino falls from the top to the bottom. In the falling process, user can move it left, right, or down, and can rotate clockwise, or counterclockwise. User gains score by filling rows with blocks, and loses when next shape cannot move down and is higher than the top board.

This is a Java implementation of the famous game Tetris, made by some university students for their Object Oriented Programming course. We hold no copyrights over the title nor the idea of the game. Not for commercial purposes.

## Features
* Fully implemented tetris game. (Scores not implemented.)
* Super Rotation System.
* Unique homemade graphical assets/textures.

# Getting started
## Dependencies
* Java 1.8 (Java 8) for Windows
* lwjgl 3.2.4 (Windows native included in the repository.)
* IntelliJ preferably (optional).

## How to download
```bash
git clone https://github.com/Nitaray/OOP---T3TR1S.git
```
## Run
Navigate to the repository folder. Nagivate to the T3TR1S folder where the T3TR1S.jar file is located.
Run the following command:
```bash
java -jar T3TR1S.jar
```
Or just run the T3TR1S.bat file.

# Gameplay
## Gamemode
Currently there is only one gamemode, which is survival. You must clear line to survive as long as possible.
More gamemodes will be implemented in the future.

## Controls
* Left and right arrows: Moving the t3tr1minos.
* Down arrow: Soft drop.
* Spacebar: Hard drop.
* E: Rotate Counter-clockwise.
* R: Rotate Clockwise.

# Accomplishments
## Design patterns used
* Singleton pattern.
* Stategy pattern.
* Command pattern?

## Goal
* Implement scoring system.
* Implement more gamemode.
* Implement ingame effects (Clear line, T-Spin effects, etc.)
