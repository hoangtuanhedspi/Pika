package com.pikapika.view;

import com.pikapika.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anonymousjp on 5/21/17.
 */
public class PikachuPanel extends JPanel implements ActionListener{
    private GridLayout layout;
    private Pikachu[][] pikachuIcon;
    private int row;
    private int col;
    private int countClicked = 0;

    public PikachuPanel(){
        this(10,10);
    }

    public PikachuPanel(int row, int col){
        setOpaque(false);
        this.row = row;
        this.col = col;
        initUI(row,col);
    }

    private void initUI(int row, int col){
        layout = new GridLayout(row,col,0,0);
        setLayout(layout);
        setAlignmentY(JPanel.CENTER_ALIGNMENT);
    }

    public void renderMatrix(int[][] matrix){
        pikachuIcon = new Pikachu[row][col];
        for (int i = 0;i < row;i++){
            for (int j = 0; j < col;j++){
                pikachuIcon[i][j] = createButton(i + 1,j+1);
                Icon icon = getIcon(matrix[i][j]);
                pikachuIcon[i][j].setIcon(icon);
                add(pikachuIcon[i][j]);
            }
        }
    }

    public void updateMaxtrix(int[][] matrix){
        for (int i = 0;i < row;i++){
            for (int j = 0; j < col;j++){
                pikachuIcon[i][j].setIcon(getIcon(matrix[i][j]));
            }
        }
        invalidate();
        this.repaint();
    }

    private Icon getIcon(int index) {
        int width = 40, height = 40;
        Image image = new ImageIcon(getClass().getResource(
                "../resources/ic_" + index + ".png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height,
                image.SCALE_SMOOTH));
        return icon;
    }

    private Pikachu createButton(int x, int y) {
        Pikachu btn = new Pikachu(x,y);
        btn.setActionCommand(x+","+y);
        btn.setBorder(null);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnIndex = e.getActionCommand();
        int indexDot = btnIndex.lastIndexOf(",");
        int x = Integer.parseInt(btnIndex.substring(0, indexDot));
        int y = Integer.parseInt(btnIndex.substring(indexDot + 1,
                btnIndex.length()));
        if (countClicked<2)
            countClicked+=1;
        else countClicked = 0;
        Utils.debug(getClass(),"Click: " + countClicked + "point:"+x+","+y);
    }
}
