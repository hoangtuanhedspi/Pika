package com.pikapika;

/**
 * Created by anonymousjp on 5/20/17.
 */

import com.pikapika.view.PlayGameView;

import javax.swing.*;

import static com.pikapika.utils.Utils.MAP_COL;
import static com.pikapika.utils.Utils.MAP_ROW;

public class Test extends JFrame{

    public static void main(String[] args) {
        Test t = new Test();
        PlayGameView view = new PlayGameView(MAP_ROW,MAP_COL);
        view.setSize(720,640);
        t.add(view);
        t.setSize(720,640);
        t.setVisible(true);
    }

}
