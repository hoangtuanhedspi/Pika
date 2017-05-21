package com.pikapika.view;

import com.pikapika.utils.Utils;

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
        Utils.debug(getClass(),x+":"+y);
    }

    public int getXPoint() {
        return xPoint;
    }

    public int getYPoint() {
        return yPoint;
    }

    public void drawBorder(){

    }

    public void removeBorder(){

    }
}
