package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Message;
import com.webcheckers.model.MessageType;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Objects;

public class PostBackupMoveRoute implements Route {

  /**
   * {@inheritDoc}
   */
  private final GameCenter gameCenter;

  public PostBackupMoveRoute(final GameCenter gameCenter) {
    Objects.requireNonNull(gameCenter, "gameCenter must not be null");
    this.gameCenter = gameCenter;

  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    gameCenter.get(request.session()).backupMove();
    return new Message("You have successfully backed up a move, Please Click on Submit Turn", MessageType.INFO);
  }
}