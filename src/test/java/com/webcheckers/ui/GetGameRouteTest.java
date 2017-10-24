package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *  The unit test suite for the {@link GetGameRoute} component.
 *
 *  @author <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 */
class GetGameRouteTest {
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handle() throws Exception {
    }

    private static final String OPPONENT_COLOR = "Red";
    private static final String PLAYER_COLOR = "White";
    private static final String PLAYER_RED_NAME = "Bob";
    private static final String PLAYER_WHITE_NAME = "Sally";
    private static final Player PLAYER_RED = new Player(PLAYER_RED_NAME);
    private static final Player PLAYER_WHITE = new Player(PLAYER_WHITE_NAME);

    /**
     *  Component-under-test CuT
     */
    private GetGameRoute CuT = new GetGameRoute(new GameCenter());

    private Request request;
    private Session session;
    private Response response;

    /**
     *  Set up mock objects for each test
     */
    @Before
    void setUp() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
    }

    /**
     *  Test that the Game view will create a new game if none exists.
     */
    @Test
    public void new_game() {
        // Arrange the test scenario; The session holds no game
        final Game game = new Game(PLAYER_RED,PLAYER_WHITE);
        when(session.attribute(GameCenter.GAME_ID)).thenReturn(game);

        // Invoke the test
        final ModelAndView result = CuT.handle(request, response);

        // Analyze the results
        //  * is not null
        assertNotNull(result);

        //  * model is a not null Map
        final Object model = result.getModel();
        assertNotNull(model);
        assertTrue(model instanceof Map);

        //  * model contains all necessary View-Model data
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(GetGameRoute.TITLE, vm.get(GetGameRoute.TITLE_ATTR));
        assertEquals(PLAYER_COLOR, vm.get(GetGameRoute.PLAYER_COLOR_ATTR));
        assertEquals(OPPONENT_COLOR, vm.get(GetGameRoute.OPPONENT_COLOR_ATTR));

        //  * test view name
        assertEquals(GetGameRoute.VIEW_NAME, result.getViewName());
    }


}