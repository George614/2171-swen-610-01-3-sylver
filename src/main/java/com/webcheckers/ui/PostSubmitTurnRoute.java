package com.webcheckers.ui;

import static spark.Spark.halt;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import java.util.List;
import java.util.Objects;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

public class PostSubmitTurnRoute implements TemplateViewRoute {

  private final GameCenter gameCenter;
  private final PlayerLobby playerLobby;

  /**
   * The constructor for the {@code POST /submitTurn} route handler.
   *
   * @param gameCenter
   *    The {@link GameCenter} for the application.
   * @param playerLobby
   *    The {@Link PlayerLobby} for the application.
   */
  public PostSubmitTurnRoute(GameCenter gameCenter, PlayerLobby playerLobby) {
    // validation
    Objects.requireNonNull(gameCenter, "gameCenter must not be null");
    Objects.requireNonNull(gameCenter, "playerLobby must not be null");
    //
    this.gameCenter = gameCenter;
    this.playerLobby = playerLobby;
  }

  @Override
  public ModelAndView handle(Request request, Response response) {
    // retrieve the HTTP session
    final Session httpSession = request.session();

    // checks if the user is signed-in
    final Player currentPlayer = playerLobby.getPlayer(httpSession);

    // retrieve the game from the session
    final Game currentGame = gameCenter.get(httpSession);
    final List<Move> validatedMove = currentGame.validatedMoves;
    int totalValidMoves = 0;
    System.out.println("SIZE OF VALIDATEDMOVES"+currentGame.validatedMoves.size());
    for (Move moveItem : validatedMove) {
      // If the move is valid, apply move and up the counter
      System.out.println("For validating move "+moveItem.getStart().getRow()+" "+moveItem.getEnd().getRow());
      if (currentGame.board.isMoveValid(moveItem)) {
        currentGame.board.makeMove(moveItem);
        totalValidMoves++;
      }
    }

    /*
    while(!validatedMove.isEmpty()){
      if(currentGame.board.isMoveValid(validatedMove.get(totalValidMoves))){
        System.out.println("VALID MOVE");
        currentGame.board.makeMove(validatedMove.get(totalValidMoves));
        validatedMove.remove(totalValidMoves);
        totalValidMoves++;

      }
    }
*/
    // flips the color to change the turn,
    // and clear the list of moves
    System.out.println("totalValidatedMoves "+totalValidMoves);
    if (totalValidMoves > 0) {
      currentGame.setCurrentTurn((currentGame.getCurrentTurn() == Color.RED ? Color.WHITE : Color.RED));
      currentGame.validatedMoves.clear();
    }
   // currentGame.setCurrentTurn((currentGame.getCurrentTurn() == Color.RED ? Color.WHITE : Color.RED));

    // Redirect the user to the Game view
    String opponentUsername = currentPlayer.getUsername() == currentGame.getPlayerRedUsername() ? currentGame.getPlayerWhiteUsername() : currentGame.getPlayerRedUsername();
    response.redirect(WebServer.GAME_URL + "?" + GetGameRoute.OPPONENT_PARAM + "=" + opponentUsername);
    halt();
    return null;
  }
}
