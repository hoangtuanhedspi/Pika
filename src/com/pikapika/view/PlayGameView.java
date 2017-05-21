package com.pikapika.view;

import com.pikapika.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class PlayGameView extends JpanelBackground implements ActionListener{
    private JPanel topMenuPanel;
    private PikachuPanel pikachuPanel;
    private BorderLayout mainLayout;
    private GroupLayout topMenuLayout;
    private JButton quitPlay;
    private JProgressBar timerProgress;
    private JLabel timer;
    private JLabel score;
    private JButton pauseGame;
    private int[][] test;

    public PlayGameView(){
        super();
        setVisible(false);
        initUI();
    }

    private void initUI(){
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

        pikachuPanel = new PikachuPanel(6,10);

        add(topMenuPanel,BorderLayout.PAGE_START);


        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.add(pikachuPanel);

        add(panel,BorderLayout.CENTER);

        test = new int[10][10];

        for (int i = 0;i < 10;i++){
            for (int j = 0; j < 10;j++){
                Random random = new Random();
                test[i][j] = random.nextInt(10);
            }
        }

        pikachuPanel.renderMatrix(test);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Utils.debug(getClass(),e.getActionCommand());
        switch (e.getActionCommand()){
            case "Pause" :
            case "Menu" :
            default: break;
        }
    }

    public interface PlayGameListener{
        void onMenuClicked();
        void onPauseClicked();
        void onPickachuClicked(Pikachu... pikachus);
    }
}
