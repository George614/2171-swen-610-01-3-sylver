package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.*;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class HomePostRoute implements TemplateViewRoute {
    static String playerName=null;

    public ModelAndView handle(Request request, Response response) {
        final String name = request.queryParams("playername");

        playerName=name;
        final Map<String, Object>vm = new HashMap<>();

        final Session session=request.session();

        storeName(name,session);

        vm.put("title", "Welcome");
        vm.put("name",name);


       // vm.put("names",session.attribute("names"));

        return new ModelAndView(vm,"home.ftl");
    }

    private void storeName(String name, Session session){
        List<String> names = session.attribute("names");
        if(names == null){
            names = new ArrayList<>();
            session.attribute("names",names);
        }
        names.add(name);
        System.out.println(name+"ADDED");

    }

    public static String getNames(){
        String players;
        players=playerName;
        return players;
    }
}