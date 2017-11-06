package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import java.util.Objects;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class PostSubmitTurnRoute implements TemplateViewRoute {

  private final GameCenter gameCenter;

  /**
   * The constructor for the {@code POST /submitTurn} route handler.
   *
   * @param gameCenter
   *    The {@link GameCenter} for the application.
   */
  public PostSubmitTurnRoute(GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(gameCenter, "gameCenter must not be null");
    //
    this.gameCenter = gameCenter;
  }

  @Override
  public ModelAndView handle(Request request, Response response) {
    return null;
  }
}
