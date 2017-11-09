package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostValidateMoveRouteTest {

    static final String PLAYER_NAME = "playerName";
    Session session;
    Request request;
    Response response;
    GameCenter gameCenter;
    PlayerLobby playerLobby;
    Board board;
    private static final String PLAYER1NAME = "NAMEFORTEST1";
    private static final String PLAYER2NAME = "NAMEFORTEST2";
    private Session session1 = mock(Session.class);
    private Session session2 = mock(Session.class);
    private static final Player PLAYER1 = new Player(PLAYER1NAME);
    private static final Player PLAYER2 = new Player(PLAYER2NAME);
    private Request R1;
    private Request R2;

    private PostValidateMoveRoute CuT;

    @Before

    public void setUp() throws Exception {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        playerLobby = mock(PlayerLobby.class);
        gameCenter=mock(GameCenter.class);

    }

    @Test
    public void handle() throws Exception {
        R1 = mock(Request.class);
        R2 = mock(Request.class);
        assertNotNull(playerLobby);
        assertNotNull(gameCenter);
        when(session1.attribute(PLAYER1NAME)).thenReturn(PLAYER1);
        when(session2.attribute(PLAYER2NAME)).thenReturn(PLAYER2);
        when(session.attribute(GameCenter.GAME_ID) != null).thenReturn(true);
        when(session1.attribute(PLAYER_NAME)).thenReturn(PLAYER1NAME);
        when(session2.attribute(PLAYER_NAME)).thenReturn(PLAYER2NAME);


    }

}