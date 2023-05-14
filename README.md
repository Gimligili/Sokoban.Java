# Sokoban Game - Java Implementation

[![License: MIT with Attribution](https://img.shields.io/badge/License-MIT%20with%20Attribution-green.svg)](https://opensource.org/licenses/MIT-0) ![Programming Language](https://img.shields.io/badge/Language-Java-blue) ![Dependencies](https://img.shields.io/badge/Dependencies-JavaFX-red)

## Table of Contents

- [Introduction](#Introduction)
- [Design Choices and Features](#Design)
- [Validation Points](#Validation)
- [Installation](#Installation)
- [Game Controls](#GameControls)
- [Game Classes](#GameClasses)
- [Conclusion](#Conclusion)
- [License](#license)

## Introduction

Sokoban is a classic puzzle game where the player must push boxes onto designated spots in order to complete the level. The player controls a character that can move up, down, left, and right and push boxes. The game is won when all boxes are on the designated spots.

## Design Choices and Features

During the early stages of development, the following design choices and features were considered:

- The game should have a simple, easy-to-use interface that allows the player to quickly understand how to play the game.
- The game should have multiple levels of increasing difficulty to keep the player engaged.
- The game should keep track of the player's score and time to add an element of competitiveness to the game.
- The game should have sound effects and music to enhance the player's experience.
- The game should have the ability to save and load game progress.

In order to meet these requirements, the following features were implemented:

- A graphical user interface (GUI) was designed to display the game board, the player's score and time, and the menu options.
- A set of levels with increasing difficulty was created.
- A scoring system was implemented based on the number of moves the player makes and the time taken to complete the level.
- Sound effects and background music were added to enhance the player's experience.
- The game state can be saved and loaded using the Java Serialization API.

## Validation Points

During the testing phase, the following validation points were checked to ensure the game is working correctly:

- The game interface is displayed correctly on different screen resolutions.
- The player's movements and box movements are correctly registered and displayed on the game board.
- The game levels are of increasing difficulty and can be completed by the player.
- The scoring system correctly calculates the player's score based on the number of moves and time taken to complete the level.
- The sound effects and background music are correctly played.
- The game state is correctly saved and loaded using the Java Serialization API.

## Installation

To play the Sokoban game, you need to have Java installed on your computer. Once you have Java installed, you can download the game files from the repository and compile them using a Java compiler.

## Game Controls

The Sokoban game is controlled using the arrow keys on the keyboard. The character moves one square at a time in the direction of the arrow key pressed. If there is a box in the way, the character will push the box in the direction of the arrow key. The game is won when all the boxes have been moved to their designated storage locations.

## Game Classes

The Sokoban game is implemented using several Java classes. These classes include:

- Game: The main class that runs the game.
- Level: Represents a single level of the game, including the maze layout, box and storage locations, and character starting position.
- Player: Represents the player character in the game.
- Box: Represents the boxes that the player pushes around the maze.
- Storage: Represents the storage locations for the boxes.

## Conclusion

The Sokoban game is a challenging and enjoyable puzzle game that requires strategy and planning to solve. With its multiple levels, undo feature, and sound effects, it provides hours of entertainment for players of all ages. Thank you for considering this game for your entertainment needs.

This README file was created with the help of ChatGPT, an AI language model trained by OpenAI. The content was based on the original report written by myself for a Software Engineering lab. Thank you ChatGPT for your contribution!

## License

This project is licensed under the terms of the MIT License with Attribution. See [LICENSE](LICENSE) for more information.
