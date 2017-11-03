package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Session;
import java.util.List;
import java.util.Objects;

/**
 * The object to coordinate the state of the Web Application.
 *
 * @author <a href='mailto:dl8006@g.rit.edu'>Diosdavi Lara</a>
 */
public class GameCenter {

  /**
   * The user session attribute name that points to a game object.
   */
  private Game game;
  public final static String GAME_ID = "game";
  private List<Game> ongoingGames;

  //
  // Public methods
  //

  /**
   * Get the {@linkplain Game game} for the current user
   * (identified by a browser session).
   *
   * @param session
   *   The HTTP session
   * @param playerRed
   *   The Player class for the red pieces
   * @param playerWhite
   *   The Player class for the white pieces
   *
   * @return
   *   A existing or new {@link Game}
   */
  public Game get(final Session session, final Player playerRed, final Player playerWhite) {
    // validation
    Objects.requireNonNull(session, "session must not be null");
    Objects.requireNonNull(session, "playerRed must not be null");
    Objects.requireNonNull(session, "playerWhite must not be null");
    //
    game = session.attribute(GAME_ID);
    if (game == null) {
      // create new game
      game = new Game(playerRed, playerWhite);
      session.attribute(GAME_ID, game);
      System.out.println("New game created: " + game);
    }
    return game;
  }

  /**
   * End the user's current {@linkplain Game game}
   * and remove it from the session.
   *
   * @param session
   *   The HTTP session
   */
  public void end (Session session) {
    // validation
    Objects.requireNonNull(session, "session must not be null");
    // remove the game from the user's session
    session.removeAttribute(GAME_ID);
  }


  public boolean isUserPlaying(String username){



    if(PlayerLobby.onlinePlayers.get(username)==null){
      //do nothing as user is not playing
      return false;
    }
    else{
      return true;
    }

  }
}
