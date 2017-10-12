package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Web Controller for the Sign-Out page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class SignOutController implements TemplateViewRoute {

    private String playersDisplay;
    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        //vm.put("playerName", playerName);
        //request.session(false);
        final Session session=request.session();
        List<String> names = session.attribute("names");
        playersDisplay=com.webcheckers.ui.HomePostRoute.getNames();
        names.remove(playersDisplay);
        System.out.println("DELETED LIST"+names);

        session.removeAttribute(playersDisplay);

        
        return new ModelAndView(vm , "home.ftl");
    }

}
