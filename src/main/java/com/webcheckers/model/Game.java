package com.webcheckers.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
  public Board board;
  public Color currentTurn;
  private static Map<Color, Player> players;

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
    this.players = new HashMap<>();
    double playerOrder = java.lang.Math.random();

    if (playerOrder % 2 == 0) {
      this.players.put(Color.RED, playerOne);
      this.players.put(Color.WHITE, playerTwo);
    } else {
      this.players.put(Color.WHITE, playerOne);
      this.players.put(Color.RED, playerTwo);
    }
  }

  /**
   * Get the first player's username.
   *
   * @return
   *   The first player's username.
   */
  public String getPlayerRedUsername() {
    return players.get(Color.RED).getUsername();
  }

  /**
   * Get the second player's username.
   *
   * @return
   *   The second player's username.
   */
  public String getPlayerWhiteUsername() {
    return players.get(Color.WHITE).getUsername();
  }

  /**
   * Get the Color associated with the player's username.
   *
   * @return
   *   The player's username.
   */
  public Color getPlayerColor(String username) {
    for (Entry<Color, Player> entry : players.entrySet()) {
      if (entry.getValue().getUsername() == username) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("Invalid username, that Player is not part of the game.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized String toString() {
    Player playerOne = players.get(Color.RED);
    Player playerTwo = players.get(Color.WHITE);
    return "{Game: " + playerOne + " vs " + playerTwo + "}";
  }
}
