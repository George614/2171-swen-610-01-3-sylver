package com.webcheckers.ui;

import static spark.Spark.halt;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRoute implements TemplateViewRoute {
    // View-Model attribute names
    static final String TITLE_ATTR = "title";
    static final String CURRENT_PLAYER_ATTR = "currentPlayer";
    static final String PLAYER_NAME_ATTR = "playerName";
    static final String PLAYER_COLOR_ATTR = "playerColor";
    static final String OPPONENT_NAME_ATTR = "opponentName";
    static final String OPPONENT_COLOR_ATTR = "opponentColor";
    static final String IS_MY_TURN_ATTR = "isMyTurn";
    static final String MESSAGE_ATTR = "message";
    static final String BOARD_ATTR = "board";

    // Constants
    static final String TITLE = "Game Play!";
    static final String OPPONENT_PARAM = "opponentRadio";
    static final String VIEW_NAME = "game.ftl";

    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param gameCenter
     *    The {@link GameCenter} for the application.
     * @param playerLobby
     *    The {@link PlayerLobby} for the application.
     */
    GetGameRoute(final GameCenter gameCenter, final PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
    }

    @Override
    public ModelAndView handle(Request request, Response response) {
        // retrieve the HTTP session and query params
        final Session httpSession = request.session();
        String opponent = request.queryParams(OPPONENT_PARAM);

        final Player currentPlayer;
        final Player opponentPlayer;
        final Game game;

        // if no parameters are given, pull the data from the session
        if (opponent != null) {
            // retrieve the two player objects
            currentPlayer = playerLobby.getPlayer(httpSession);
            opponentPlayer = playerLobby.getPlayer(opponent);
            // retrieve the game object
            game = gameCenter.get(httpSession, currentPlayer, opponentPlayer);
        } else {
            currentPlayer = playerLobby.getPlayer(httpSession);
            game = gameCenter.get(httpSession);
            Color currentColor = game.getPlayerColor(currentPlayer.getUsername());
            opponent = currentColor == Color.RED ? game.getPlayerRedUsername() : game.getPlayerWhiteUsername();
            opponentPlayer = playerLobby.getPlayer(opponent);
        }

        //check if the opponent is already playing a Game with someone else
        if (gameCenter.isUserPlaying(opponent)) {
            Game ongoingGame = gameCenter.get(opponent);
            boolean isCurrentPlayerNotOnThatGame = (ongoingGame.getPlayerRedUsername() != currentPlayer.getUsername()) && (ongoingGame.getPlayerWhiteUsername() != currentPlayer.getUsername());
            if (isCurrentPlayerNotOnThatGame) {
                // redirect to the home page, that player is already with someone else
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            }
        }

        // check if the current player's turn
        boolean isMyTurn = game.getPlayerColor(currentPlayer.getUsername()) == game.getCurrentTurn();

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);
        vm.put(CURRENT_PLAYER_ATTR, currentPlayer);
        vm.put(PLAYER_NAME_ATTR, currentPlayer.getUsername());
        vm.put(PLAYER_COLOR_ATTR, game.getPlayerColor(currentPlayer.getUsername()).name());
        vm.put(OPPONENT_NAME_ATTR, opponentPlayer.getUsername());
        vm.put(OPPONENT_COLOR_ATTR, game.getPlayerColor(opponentPlayer.getUsername()).name());
        vm.put(IS_MY_TURN_ATTR, isMyTurn);
        vm.put(MESSAGE_ATTR, null);
        vm.put(BOARD_ATTR, game.board);
        return new ModelAndView(vm, VIEW_NAME);
    }
}
