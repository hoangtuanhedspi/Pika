package com.pikapika;
import com.pikapika.control.GameController;

import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static com.pikapika.utils.Utils.WINDOW_HEIGHT;
import static com.pikapika.utils.Utils.WINDOW_WIDTH;

public class Main{
    public static void main(String[] args) {
        GameController pikachu = new GameController("Pikachu");
        pikachu.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        pikachu.setLocationRelativeTo(null);
        pikachu.start();
    }
}