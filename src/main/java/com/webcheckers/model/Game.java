package com.webcheckers.model;

import java.util.Objects;
import java.util.Random;

/**
 * A single checkers game.
 *
 * @author <a href='mailto:dl8006@g.rit.edu'>Diosdavi Lara</a>
 */
public class Game {

  //
  // Constants
  //

  //
  // Attributes
  //

  private final Player playerOne = null;
  private final Player playerTwo;
  public Board board;
  public Color currentTurn;

  //
  // Constructors
  //

  /**
   * Create a checkers game with two known players.
   *
   * @param playerOne
   *          The player with the red pieces.
   * @param playerTwo
   *          The player with the white pieces.
   *
   */
  public Game(Player playerOne, Player playerTwo) {
    // validation
    Objects.requireNonNull(playerOne, "red player must not be null");
    Objects.requireNonNull(playerTwo, "white player must not be null");

    this.board = new Board();
    this.currentTurn = Color.RED;
    double playerOrder = java.lang.Math.random();
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;

    if (playerOrder % 2 == 0) {
      //color is red
      playerOne.setPlayerColor("red");
    }
    else {
      //color is white
      playerTwo.setPlayerColor("white");
    }
  }

  /**
   * Get the first player's username.
   *
   * @return
   *   The first player's username.
   */
  public String getPlayerRedUsername() {
    return playerOne.getUsername();
  }

  /**
   * Get the second player's username.
   *
   * @return
   *   The second player's username.
   */
  public String getPlayerWhiteUsername() {
    return playerTwo.getUsername();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized String toString() {
    return "{Game: " + playerOne + " vs " + playerTwo + "}";
  }
}
