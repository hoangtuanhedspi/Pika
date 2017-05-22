package com.pikapika;

/**
 * Created by anonymousjp on 5/20/17.
 */

import com.pikapika.view.JpanelBackground;

import javax.swing.*;

public class Test extends JFrame{

    public static void main(String[] args) {
        Test t = new Test();
        t.setSize(720,460);
        JpanelBackground background = new JpanelBackground("../resources/bg_0.png");
        t.add(background);
        t.setVisible(true);
    }

}
