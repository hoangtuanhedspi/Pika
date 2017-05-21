package com.pikapika.view;

import javax.swing.*;

/**
 * Created by anonymousjp on 5/21/17.
 */
public class Pikachu extends JButton{
    private int xPoint;
    private int yPoint;

    public Pikachu(int x, int y){
        super();
        this.xPoint = x;
        this.yPoint = y;
    }

    public int getXPoint() {
        return xPoint;
    }

    public int getYPoint() {
        return yPoint;
    }
}
