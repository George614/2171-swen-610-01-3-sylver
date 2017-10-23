package com.webcheckers.model;

import java.util.Objects;

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

  private final Player playerRed;
  private final Player playerWhite;

  //
  // Constructors
  //

  /**
   * Create a checkers game with two known players.
   *
   * @param playerRed
   *          The player with the red pieces.
   * @param playerWhite
   *          The player with the white pieces.
   *
   */
  public Game(Player playerRed, Player playerWhite) {
    // validation
    Objects.requireNonNull(playerRed, "red player must not be null");
    Objects.requireNonNull(playerRed, "white player must not be null");

    this.playerRed = playerRed;
    this.playerWhite = playerWhite;
  }

}
