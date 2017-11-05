package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
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
        final String opponent = request.queryParams(OPPONENT_PARAM);

        // retrieve the two player objects
        final Player currentPlayer = playerLobby.getPlayer(httpSession);
        final Player opponentPlayer = playerLobby.getPlayer(opponent);
        // retrieve the game object
        final Game game = gameCenter.get(httpSession, currentPlayer, opponentPlayer);

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);
        vm.put(CURRENT_PLAYER_ATTR, currentPlayer);
        vm.put(PLAYER_NAME_ATTR, currentPlayer.getUsername());
        vm.put(PLAYER_COLOR_ATTR, Color.RED.name());
        vm.put(OPPONENT_NAME_ATTR, opponentPlayer.getUsername());
        vm.put(OPPONENT_COLOR_ATTR, Color.WHITE.name());
        vm.put(IS_MY_TURN_ATTR, true);
        vm.put(MESSAGE_ATTR, null);
        vm.put(BOARD_ATTR, game.board);
        return new ModelAndView(vm, VIEW_NAME);
    }
}
