# Web-Checkers Design Documentation

## Executive Summary

The application must allow players to play checkers with other players who are currently signed-in. The game user interface (UI) will support a game experience using drag-and-drop browser capabilities for making moves.

Beyond this minimal set of features, we have grand vision for what we could do including watching live games, playing multiple games, playing games asynchronously, reviewing past games, running tournaments with timed moves, and offering AI players to compete against.

## Requirements

* **Player Sign-in:** Players can enter their name and start a gaming session.
* **Start a Game:** After signing in, players can pick an opponent from a list and start a checkers game with them.
* **Game Play:** The two players will be able to play a game of checkers based upon the American rules. The interface will support a game experience using drag-and-drop browser capabilities for making moves.
* **Player Resignation:** Any of the players can resign from a game in progress.
* **Player Sign-out:** Players can end their gaming session, freeing up their name for others to use.

### Definition of MVP

1. The minimal viable product, according to the Web-Checkers vision document, must include the following features:
2. Every player must sign-in before playing a game.
3. Two players must be able to play a game of checkers based upon the American rules.
Either player of a game may choose to resign, which ends the game.

### MVP Features

- Player Sign-in / Sign-out
- Epic: Set-up game
- Epic: Single Player Moves
- Epic: King Player Moves
- Player Resignation

## Architecture

The Web-Checkers application uses a Java based server. We have used the Spark web micro framework along with the FreeMarker template engine to handle HTTP requests and to generate HTML responses. We have used Java v8 for this project.

### Summary

1. The landing page is the home page (home.ftl) which welcomes the user to the Web-Checkers game. The user can then sign in using the link in the navigation bar.
2. Once the user clicks on the sign-in link, they are redirected to the sign-in page which consists of an input that can be submitted. This username must be unique and the user can only play once they are logged in. Once the user is logged in, a list of opponents that are online is displayed. The user can select an opponent and play against them once the “Let’s Play!” button is clicked.
3. The user is then redirected to the game page which displays a checkers board, the players’ pieces and links to submit a turn, reset a turn and undo. 
4. The user can also sign out using the link from the navigation pane if they wish to do so.

### UI Tier

This tier contains our code for the sign-in, home display, sign out and web server files. These are the files that the user interacts with. Following are the files with the description:

**Sign In Controller:** This file contains the Sign-in authentication logic.
**Sign Out Controller:** This file contains the sign out redirection.
**Home Controller:** This file is responsible for rendering home.ftl .
**Web Server:** This file contains the routing information for the Web-Checkers application.
**Game Controller:** This file contains the game controller to enable game play.

### Model Tier 

This tier has the WebCheckers.java in order to instantiate the model for the Web-Checkers application.

### Application Tier

This tier will contain the “business logic” for our WebCheckers application, that is, the logic to enable game play.

