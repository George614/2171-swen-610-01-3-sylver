package com.webcheckers.ui;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

/**
 * The Web Controller for the Sign-Out page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class SignOutController implements TemplateViewRoute {

    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        //vm.put("playerName", playerName);
        return new ModelAndView(vm , "signin.ftl");
    }

}
