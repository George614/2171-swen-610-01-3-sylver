package com.webcheckers.ui;

import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;
import static com.webcheckers.ui.SignInController.*;
import static org.eclipse.jetty.util.LazyList.size;

import static com.webcheckers.ui.SignInController.getNames;

public class GameController implements TemplateViewRoute {

    static final String PLAYER_NAME = "playerName";


    static final String OPPONENT_NAME = "opponentName";
    static final String OPPONENT_PARAM = "opponentRadio";

    static final String IS_MY_TURN = "isMyTurn";
    static final String PLAYER_COLOR = "playerColor";
    static final String OPPONENT_COLOR = "opponentColor";

    static final String PLAYER_CURRENT = "currentPlayer";

    static final String BOARD = "board";
    static final String ROW= "row";

    static final String CELL_ID_X = "cellIdx";

    static final String SPACE = "space";

    private int space=0;

    private int cellIdx=0;

    private String player;
    private String opponent;

    String playersDisplay;

    //static int[][] board = new int[8][8];
    //static int[] row = new int[8];
    //List<String> row = new ArrayList<String>();
    //List<String> board= new ArrayList<String>();

    //final Map<Integer[],Integer> row = new HashMap<Integer[],Integer>();
    //final HashMap<Integer[],Integer[]> board = new HashMap<Integer[], Integer[]>();


    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game Play!");



        playersDisplay=com.webcheckers.ui.SignInController.getNames();

        opponent=request.queryParams(OPPONENT_PARAM);
        //System.out.println("OPPONENT FROM GAME CONTROLLER"+opponent);
        vm.put(PLAYER_NAME,String.format(playersDisplay));
        vm.put(OPPONENT_NAME,String.format(opponent));

        vm.put(PLAYER_COLOR,String.format("RED"));
        vm.put(OPPONENT_COLOR,String.format("WHITE"));

        vm.put(IS_MY_TURN,true);

        vm.put(CELL_ID_X,cellIdx );

        vm.put(PLAYER_CURRENT,String.valueOf(playersDisplay));

        return new ModelAndView(vm , "game.ftl");
    }
}
