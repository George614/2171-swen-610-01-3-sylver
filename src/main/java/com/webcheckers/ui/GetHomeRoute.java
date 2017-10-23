package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import java.util.Objects;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class GetHomeRoute implements TemplateViewRoute {

  // View-Model attribute names
  static final String TITLE_ATTR = "title";
  static final String IS_LOGGED_IN_ATTR = "isLoggedIn";
  static final String PLAYER_NAME_ATTR = "playerName";
  static final String PLAYER_LIST_ATTR = "playerList";
  static final String MESSAGE_ATTR = "message";
  // Constants
  static final String TITLE = "Welcome";
  static final String VIEW_NAME = "home.ftl";

  private final PlayerLobby playerLobby;

  /**
   * The constructor for the {@code GET /home} route handler.
   *
   * @param playerLobby
   *    The {@link PlayerLobby} for the application.
   */
  GetHomeRoute(final PlayerLobby playerLobby) {
    // validation
    Objects.requireNonNull(playerLobby, "playerLobby must not be null");
    //
    this.playerLobby = playerLobby;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ModelAndView handle(Request request, Response response) {
    // retrieve the HTTP session
    final Session session = request.session();

    // start building the View-Model
    final Map<String, Object> vm = new HashMap<>();
    vm.put(TITLE_ATTR, TITLE);
    vm.put(PLAYER_LIST_ATTR, playerLobby.getUsersList());
    // checks if the user is signed-in
    if (session.attribute(PlayerLobby.PLAYER_ID) != null) {
      vm.put(IS_LOGGED_IN_ATTR, true);
      vm.put(PLAYER_NAME_ATTR, ((Player)session.attribute(PlayerLobby.PLAYER_ID)).getUsername());
    } else {
      vm.put(IS_LOGGED_IN_ATTR, false);
      vm.put(PLAYER_NAME_ATTR, "");
    }

    return new ModelAndView(vm, VIEW_NAME);
  }
}
