package com.webcheckers.ui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

/**
 * The unit test suite for the {@link GetSignOutRoute} component.
 *
 * @author <a href='mailto:dl8006@g.rit.edu'>Diosdavi Lara</a>
 */
public class GetSignOutRouteTest {

  /**
   * The component-under-test (CuT).
   *
   * <p>
   * This is a stateless component so we only need one.
   */
  private GetSignOutRoute CuT;

  private static final String USERNAME = "Pepito";
  // mock objects
  private Request request;
  private Session session;
  private PlayerLobby playerLobby;

  /**
   * Setup new mock objects for each test.
   */
  @Before
  public void setUp() {
    request = mock(Request.class);
    session = mock(Session.class);
    playerLobby = mock(PlayerLobby.class);
    when(request.session()).thenReturn(session);
    when(playerLobby.getUsersList()).thenReturn(new ArrayList<String>());
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test that CuT shows the Home view when the user has signed-out.
   */
  @Test
  public void handle() throws Exception {
    CuT = new GetSignOutRoute(playerLobby);

    // Arrange the test scenario: There is an existing session with a logged in user.
    when(session.attribute(PlayerLobby.PLAYER_ID)).thenReturn(new Player(USERNAME, "red"));
    final Response response = mock(Response.class);

    // Invoke the test
    final ModelAndView result = CuT.handle(request, response);

    // Analyze the results:
    //   * result is non-null
    assertNotNull(result);
    //   * model is a non-null Map
    final Object model = result.getModel();
    assertNotNull(model);
    assertTrue(model instanceof Map);
    //   * model contains all necessary View-Model data
    @SuppressWarnings("unchecked")
    final Map<String, Object> vm = (Map<String, Object>) model;
    assertEquals(GetHomeRoute.TITLE, vm.get(GetHomeRoute.TITLE_ATTR));
    assertEquals(Boolean.FALSE, vm.get(GetHomeRoute.IS_LOGGED_IN_ATTR));
    assertEquals("", vm.get(GetHomeRoute.PLAYER_NAME_ATTR));
    assertEquals(new ArrayList<String>(), vm.get(GetHomeRoute.PLAYER_LIST_ATTR));
    //   * test view name
    assertEquals(GetHomeRoute.VIEW_NAME, result.getViewName());
  }
}