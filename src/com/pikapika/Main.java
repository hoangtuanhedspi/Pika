package com.pikapika;
import com.pikapika.control.GameControler;
import static com.pikapika.utils.Utils.WINDOW_HEIGHT;
import static com.pikapika.utils.Utils.WINDOW_WIDTH;

public class Main{
    public static void main(String[] args) {
        GameControler pikachu = new GameControler("Pikachu");
        pikachu.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        pikachu.start();
    }
}