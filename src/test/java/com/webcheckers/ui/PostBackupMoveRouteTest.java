package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostBackupMoveRouteTest {
   Session session;
   Request request;
   GameCenter gameCenter;
   PlayerLobby playerLobby;

  @Before
  public void setUp() throws Exception {
    request = mock(Request.class);
    session = mock(Session.class);
    playerLobby = mock(PlayerLobby.class);
    gameCenter = mock(GameCenter.class);

    when(request.session()).thenReturn(session);
  }


  @Test
  public void handle() throws Exception {
    final PostBackupMoveRoute CuT = new PostBackupMoveRoute(gameCenter);
    assertNotNull(gameCenter);
    when(session.attribute(GameCenter.GAME_ID) != null).thenReturn(true);

  }

}