package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import java.util.Objects;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class GetGameRoute implements TemplateViewRoute {

    // View-Model attribute names
    static final String TITLE_ATTR = "title";
    static final String PLAYER_NAME_ATTR = "playerName";
    static final String OPPONENT_NAME_ATTR = "opponentName";
    static final String IS_MY_TURN_ATTR = "isMyTurn";
    static final String PLAYER_COLOR_ATTR = "playerColor";
    static final String OPPONENT_COLOR_ATTR = "opponentColor";
    static final String IS_LOGGED_IN_ATTR = "isLoggedIn";
    static final String CELL_ID_X_ATTR = "cellIdx";
    // Constants
    static final String TITLE = "Game Play!";
    static final String OPPONENT_PARAM = "opponentRadio";
    static final String VIEW_NAME = "game.ftl";
    static final String BOARD = "board";
    static final String ROW= "row";
    static final String SPACE = "space";
    private int space = 0;
    private int cellIdx = 0;

    private final GameCenter gameCenter;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param gameCenter
     *    The {@link GameCenter} for the application.
     */
    GetGameRoute(final GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        //
        this.gameCenter = gameCenter;
    }


    @Override
    public ModelAndView handle(Request request, Response response) {
        final String opponent = request.queryParams(OPPONENT_PARAM);

        // retrieve the HTTP session
        final Session httpSession = request.session();
        final String currentUser = ((Player)httpSession.attribute(PlayerLobby.PLAYER_ID)).getUsername();
        // retrieve the game object
        Player newPlayer = new Player(currentUser);
        Player opponentPlayer = new Player(opponent);
        final Game game = gameCenter.get(httpSession,newPlayer , opponentPlayer );

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);
        vm.put(OPPONENT_NAME_ATTR, game.getPlayerWhiteUsername());
        vm.put(PLAYER_COLOR_ATTR, "RED");
        vm.put(OPPONENT_COLOR_ATTR, "WHITE");
        vm.put(IS_MY_TURN_ATTR, true);
        vm.put(CELL_ID_X_ATTR, cellIdx);
        // checks if the user is signed-in
        if (httpSession.attribute(PlayerLobby.PLAYER_ID) != null) {
            vm.put(IS_LOGGED_IN_ATTR, true);
            vm.put(PLAYER_NAME_ATTR, game.getPlayerRedUsername());
        }
        return new ModelAndView(vm , VIEW_NAME);
    }
}
