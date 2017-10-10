package com.webcheckers.ui;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import static com.webcheckers.ui.GameController.OPPONENT_NAME;
import static com.webcheckers.ui.GameController.OPPONENT_PARAM;

public class SignInController implements TemplateViewRoute {
    static String username="X";

    static final String NAME_PARAM="playername";
    static final String USER_PARAM="userName";
    static final String CORRECT_NAME="correctName";
    static final String REMOVE_FORM="removeform";
    static final String OPPONENT_LIST="opponentList";

    static final String PLAYER_PARAM = "correctUsername";


    private static final Logger LOG = Logger.getAnonymousLogger();
    private boolean correctname=false;
    private boolean removeForm=false;

    private static String player;
    private static String opponent;


    private List<String> opponentList=new ArrayList<String>();
    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign In");
        correctname=false;
        vm.put(CORRECT_NAME,correctname);

        //System.out.println("CORRECTNAME:"+correctname);
        vm.put(USER_PARAM,"X");
        username=request.queryParams(NAME_PARAM);
        vm.put(USER_PARAM,username);

        //System.out.println("USERNAME 1"+username);

        boolean matches=ReadNamesFromFile(request);

        //System.out.println("MATCHES:"+matches);

        if(matches){

            correctname=false;
            vm.put(USER_PARAM,"INVALID");

        }
        else{
            correctname=true;
            removeForm=true;
            WriteToFile(username,true);
        }

        //System.out.println("CORRECTNAME+"+correctname);
        vm.put(CORRECT_NAME,correctname);


        opponentList=DisplayOpponents();
        vm.put(OPPONENT_LIST,opponentList);

        player=request.queryParams(PLAYER_PARAM);
        //System.out.println("PLAYER"+player);
        return new ModelAndView(vm , "signin.ftl");
    }



    private boolean ReadNamesFromFile(Request request){
        username=request.queryParams(NAME_PARAM);
        //vm.put(USER_PARAM,username);

        System.out.println("USERNAME INSIDE FUNCTION:"+username);
        try {

            Scanner sc=new Scanner(new File("onlineusers.txt"));
            int count=0;
            String s="null";
            while(sc.hasNext()){
                s=sc.next();
                String[] data=s.split(",");
                //System.out.println("DATA 0"+data[0]);
                if(Objects.equals(username, new String(data[0]))){
                    //System.out.println("MATCHES");
                    return true;
                }

            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    };

    private void WriteToFile(String name, boolean active){
        BufferedWriter wr = null;
        try {
            wr = new BufferedWriter(new FileWriter("onlineusers.txt",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(name!=null){
                wr.write("\n"+name+","+active);

            }

            //LOG.fine("WON: " + won);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    private List<String> DisplayOpponents() {
        List<String> opponents = new ArrayList<String>();
        //System.out.println("INSIDE DISPLAYOPPONENTS");
        try {

            Scanner sc = new Scanner(new File("onlineusers.txt"));
            int count = 0;
            String s = "null";
            while (sc.hasNext()) {
                s = sc.next();
                String[] data = s.split(",");
                //System.out.println("DATA 0" + data[0]);
                //System.out.println("DATA 1"+data[1]);
                if (Objects.equals(new String("false"), new String(data[1]))) {
                    opponents.add(data[0]);
                    //System.out.println("Adding opponent: "+data[0]);

                }//end of if

            };//end of while
        } //end of try
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } //end of catch
        return opponents;

    };

    public static String getNames(){
        String players ;
        //players=String.valueOf(player);
        players=username;
        //System.out.println("RETURNING LIST");
        //System.out.println(player);
        return players;
    }

}
