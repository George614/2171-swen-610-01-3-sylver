package com.webcheckers.ui;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

/**
 * The Web Controller for the Game page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GameController implements TemplateViewRoute {

  static final String TITLE = "Welcome to WebChecker!";
  static final String playerName = "Matt";
  static final String playerColor = "Red";
  static final String opponentName = "Phil";
  static final String opponentColor = "White";
  static final boolean isMyTurn = true;

  @Override
  public ModelAndView handle(Request request, Response response) {
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    vm.put("playerName", playerName);
    vm.put("playerColor", playerColor);
    vm.put("opponentName", opponentName);
    vm.put("opponentColor", opponentColor);
    vm.put("isMyTurn", isMyTurn);

    return new ModelAndView(vm , "game.ftl");
  }

}