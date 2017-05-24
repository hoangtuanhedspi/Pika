package com.pikapika.utils;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class Utils {
    public static final int WINDOW_WIDTH = 720;
    public static final int WINDOW_HEIGHT = 460;
    public static final String DEFAULT_FONT = "Shree Devanagari 714";
    public static final String BT_EASY = "Easy";
    public static final String BT_MEDIUM = "Medium";
    public static final String BT_HARD = "Hard";
    public static final String BT_SETTING = "Setting";
    public static final String BT_QUIT = "Quit Game";
    public static final String BT_RESUM = "resum";
    public static final String BT_REPLAY = "replay";
    public static final String BT_PAUSE = "pause";
    public static final String BT_CONTINUE = "Continue";
    public static final String BT_BACK_MENU = "Back Menu";
    public static final int MAP_ROW = 8;
    public static final int MAP_COL = 12;
    public static final int PIKACHU_NUMBER = 34;
    public static final boolean DEBUG = true;

    public static void debug(Class clz,String debug){
        if (DEBUG){
            debug = debug == null ? "Null debug string!" : debug;
            String name = clz.getCanonicalName()==null?"Debug": clz.getCanonicalName();
            System.out.println(name+":"+debug);
        }
    }
}
