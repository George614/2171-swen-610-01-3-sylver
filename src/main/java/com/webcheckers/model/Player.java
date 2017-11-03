package com.webcheckers.model;

/**
 * A single player.
 *
 * @author <a href='mailto:dl8006@g.rit.edu'>Diosdavi Lara</a>
 */
public class Player {

  //
  // Constants
  //

  //
  // Attributes
  //

  private String username="null";
  private String colorAttribute;

  //
  // Constructors
  //

  /**
   * Create a player with a known username.
   *
   * @param username
   *          The username of the player.
   * @param colorAttribute
   */
  public Player(String username, String colorAttribute) {
    this.username = username;
    this.colorAttribute = "white";
  }

  public void setPlayerColor(String color){
    this.colorAttribute=color;
  }

  /**
   * Get the player's username.
   *
   * @return
   *   The player's username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized String toString() {
    return "{Player " + username + "}";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof Player)) return false;
    final Player that = (Player) obj;
    return this.username.equals(that.username);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return username.hashCode();
  }
}
