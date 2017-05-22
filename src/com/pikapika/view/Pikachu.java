package com.pikapika.view;

import com.pikapika.utils.Utils;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

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

    public void drawBorder(Color color){
        this.setBorder(new LineBorder(color, 2));
    }

    public void removeBorder(){
        this.drawBorder(Color.white);
    }
}
