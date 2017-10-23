package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import java.util.Objects;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Web Controller for the Sign-Out page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetSignOutRoute implements TemplateViewRoute {

    private final PlayerLobby playerLobby;

    /**
     * The constructor for the {@code GET /sign-out} route handler.
     *
     * @param playerLobby
     *    The {@link PlayerLobby} for the application.
     */
    GetSignOutRoute(final PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.playerLobby = playerLobby;
    }

    @Override
    public ModelAndView handle(Request request, Response response) {

        // retrieve the HTTP session
        final Session httpSession = request.session();
        final String username = ((Player)httpSession.attribute(PlayerLobby.PLAYER_ID)).getUsername();
        playerLobby.signOut(username, httpSession);

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        vm.put(GetHomeRoute.IS_LOGGED_IN_ATTR, false);
        vm.put(GetHomeRoute.PLAYER_NAME_ATTR, "");
        vm.put(GetHomeRoute.PLAYER_LIST_ATTR, playerLobby.getUsersList());
        return new ModelAndView(vm, GetHomeRoute.VIEW_NAME);
    }
}
