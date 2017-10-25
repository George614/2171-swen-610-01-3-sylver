package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *  The unit test suite for the {@link GetGameRoute} component.
 *
 *  @author <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 */
public class GetGameRouteTest {

    private static final String OPPONENT_COLOR = "Red";
    private static final String PLAYER_COLOR = "White";
    private static final String PLAYER_RED_NAME = "Bob";
    private static final String PLAYER_WHITE_NAME = "Sally";
    private static final Player PLAYER_RED = new Player(PLAYER_RED_NAME);
    private static final Player PLAYER_WHITE = new Player(PLAYER_WHITE_NAME);

    /**
     *  Component-under-test CuT
     */
    private final GetGameRoute CuT = new GetGameRoute(new GameCenter());

    // Mock objects
    private Request request;
    private Session session;
    private Response response;

    /**
     *  Set up mock objects for each test
     */
    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response=mock(Response.class);
    }

    @Test
    public void handle() throws Exception {

        final ModelAndView result = CuT.handle(request, response);
        final Object model = result.getModel();

        assertNotNull(model);
        assertTrue(model instanceof Map);

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(GetGameRoute.TITLE, vm.get(GetGameRoute.TITLE_ATTR));
        assertEquals(GetGameRoute.VIEW_NAME, result.getViewName());
        assertEquals(PLAYER_WHITE_NAME, vm.get(GetGameRoute.OPPONENT_COLOR_ATTR));
        assertEquals(PLAYER_RED_NAME, vm.get(GetGameRoute.PLAYER_COLOR_ATTR));

        session.attribute(PLAYER_RED_NAME, PlayerLobby.PLAYER_ID);
        when(session.attribute(PlayerLobby.PLAYER_ID) != null).thenReturn(true);

    }
}