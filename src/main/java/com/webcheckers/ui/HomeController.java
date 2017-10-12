package com.webcheckers.ui;

import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController implements TemplateViewRoute {

  private String player;
  @Override
  public ModelAndView handle(Request request, Response response) {
    final Map<String, Object>vm = new HashMap<>();
    final Session session = request.session();
    vm.put("title", "Welcome");
    vm.put("names",session.attribute("names"));
    player=com.webcheckers.ui.HomePostRoute.getNames();
    vm.put("playerName",player);
    return new ModelAndView(vm,"home.ftl");
  }
}
