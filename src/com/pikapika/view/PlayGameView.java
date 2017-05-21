package com.pikapika.view;

import com.pikapika.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class PlayGameView extends JpanelBackground implements ActionListener{
    private JPanel topMenuPanel;
    private JPanel pikachuPanel;
    private BorderLayout mainLayout;
    private GroupLayout topMenuLayout;
    private JButton quitPlay;
    private JProgressBar timerProgress;
    private JLabel timer;
    private JLabel score;
    private JButton pauseGame;
    private PlayGameListener playGameListener;
    private GridLayout pikachuLayout;
    private Pikachu[][] pikachuIcon;
    private int row;
    private int col;
    private int countClicked = 0;
    private Pikachu one;
    private Pikachu two;


    public PlayGameView(){
        this(10,10);
    }

    public PlayGameView(int row, int col){
        super();
        this.row = row;
        this.col = col;
        setVisible(false);
        initUI();
    }

    private void initUI(){
        setVisible(false);
        mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        this.setBackgroundImage("../resources/bg_1.png");

        topMenuPanel = new JPanel();
        topMenuLayout = new GroupLayout(topMenuPanel);

        topMenuPanel.setSize(720,60);
        topMenuPanel.setOpaque(false);
        topMenuPanel.setBorder(new EmptyBorder(5,20,5,20));

        quitPlay = new JButton("Menu");
        quitPlay.addActionListener(this);
        timerProgress = new JProgressBar(0,100);
        timer = new JLabel("Time: 100");
        timer.setForeground(Color.WHITE);
        score = new JLabel("Score: 100");
        score.setForeground(Color.WHITE);
        pauseGame = new JButton("Pause");
        pauseGame.addActionListener(this);

        topMenuPanel.setLayout(topMenuLayout);
        topMenuLayout.setHorizontalGroup(
                topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topMenuLayout.createSequentialGroup()
                        .addComponent(quitPlay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topMenuLayout.createSequentialGroup()
                        .addComponent(timer)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(score))
                        .addComponent(timerProgress, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pauseGame)
                        .addContainerGap())
        );
        topMenuLayout.setVerticalGroup(
                topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topMenuLayout.createSequentialGroup()
                        .addGroup(topMenuLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(timer)
                        .addComponent(score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timerProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(topMenuLayout.createSequentialGroup()
                        .addGroup(topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(quitPlay,GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pauseGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        pikachuPanel = new JPanel();
        pikachuLayout = new GridLayout(row,col,0,0);
        pikachuPanel.setLayout(pikachuLayout);
        pikachuPanel.setOpaque(false);
        setAlignmentY(JPanel.CENTER_ALIGNMENT);

        add(topMenuPanel,BorderLayout.PAGE_START);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(pikachuPanel);
        add(panel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(e.getSource() instanceof Pikachu)){
            switch (e.getActionCommand()){
                case "Menu" : if (playGameListener!=null){
                    playGameListener.onMenuClicked();
                } break;
                case "Pause" : if (playGameListener!=null){
                    playGameListener.onPauseClicked();
                } break;
                default: break;
            }
        }else{
            countClicked +=1;
            switch (countClicked){
                case 1: one = (Pikachu) e.getSource();
                        if (playGameListener!=null)
                            playGameListener.onPikachuClicked(countClicked,one);
                        break;
                case 2: if(!one.equals(e.getSource())){
                            two = (Pikachu) e.getSource();
                            if (playGameListener!=null)
                                playGameListener.onPikachuClicked(countClicked,one,two);
                        }else {
                            Utils.debug(getClass(),"Remove border");
                        }
                        countClicked = 0;break;
                default: break;
            }
        }
    }

    public void renderMatrix(int[][] matrix){
        pikachuIcon = new Pikachu[row][col];
        pikachuPanel.removeAll();
        pikachuPanel.invalidate();
        for (int i = 0;i < row;i++){
            for (int j = 0; j < col;j++){
                pikachuIcon[i][j] = createButton(i + 1,j+1);
                Icon icon = getIcon(matrix[i][j]);
                pikachuIcon[i][j].setIcon(icon);
                pikachuPanel.add(pikachuIcon[i][j]);
            }
        }
        pikachuPanel.repaint();
    }

    public void updateMaxtrix(int[][] matrix){
        for (int i = 0;i < row;i++){
            for (int j = 0; j < col;j++){
                pikachuIcon[i][j].setIcon(getIcon(matrix[i][j]));
            }
        }
        pikachuPanel.invalidate();
        pikachuPanel.repaint();
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
        btn.setBorder(null);
        btn.addActionListener(this);
        return btn;
    }

    public void setPlayGameListener(PlayGameListener playGameListener) {
        this.playGameListener = playGameListener;
    }

    public interface PlayGameListener{
        void onMenuClicked();
        void onPauseClicked();
        void onPikachuClicked(int clickCounter, Pikachu... pikachus);
    }
}
