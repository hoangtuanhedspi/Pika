package com.pikapika.view;

import com.pikapika.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.pikapika.utils.Utils.BT_PAUSE;
import static com.pikapika.utils.Utils.BT_RESUM;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class PlayGameView extends JpanelBackground implements ActionListener{
    /**
     *
     */
    private JPanel topMenuPanel;

    /**
     *
     */
    private JPanel pikachuPanel;

    /**
     *
     */
    private BorderLayout mainLayout;

    /**
     *
     */
    private GroupLayout topMenuLayout;

    /**
     *
     */
    private JButton resumGame;

    /**
     *
     */
    private JProgressBar timerProgress;

    /**
     *
     */
    private JLabel timer;

    /**
     *
     */
    private JLabel score;

    /**
     *
     */
    private JButton pauseGame;

    /**
     *
     */
    private JLabel mapCount;

    /**
     *
     */
    private PlayGameListener playGameListener;

    /**
     *
     */
    private GridLayout pikachuLayout;

    /**
     *
     */
    private Pikachu[][] pikachuIcon;

    /**
     *
     */
    private int row;

    /**
     *
     */
    private int col;

    /**
     *
     */
    private int countClicked = 0;

    /**
     *
     */
    private Pikachu one;

    /**
     *
     */
    private Pikachu two;

    private boolean isPlaying = true;

    // them 2 phuong thuc getter

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

        resumGame = new JButton();
        resumGame.addActionListener(this);
        Image image = new ImageIcon(getClass().getResource(
                "../resources/resum.png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(40, 40, image.SCALE_SMOOTH));
        resumGame.setIcon(icon);

        resumGame.setMargin(new Insets(0, 0, 0, 0));
        resumGame.setBorder(null);
        resumGame.setActionCommand(BT_RESUM);

        timerProgress = new JProgressBar(0,100);
        timerProgress.setValue(100);
        timer = new JLabel("Time: 100");
        timer.setForeground(Color.WHITE);

        score = new JLabel("Score: 0");
        score.setForeground(Color.WHITE);

        mapCount = new JLabel("Map: 1");
        mapCount.setForeground(Color.WHITE);

        pauseGame = new JButton();
        pauseGame.addActionListener(this);
        Image img = new ImageIcon(getClass().getResource(
                "../resources/pause.png")).getImage();
        Icon ico = new ImageIcon(img.getScaledInstance(40, 40, img.SCALE_SMOOTH));
        pauseGame.setIcon(ico);
        pauseGame.setMargin(new Insets(0, 0, 0, 0));
        pauseGame.setBorder(null);
        pauseGame.setActionCommand(BT_PAUSE);

        topMenuPanel.setLayout(topMenuLayout);
        topMenuLayout.setHorizontalGroup(
            topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(topMenuLayout.createSequentialGroup()
                .addComponent(resumGame)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(topMenuLayout.createSequentialGroup()
                .addComponent(timer)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mapCount)
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
                .addComponent(mapCount)
                .addComponent(score, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timerProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(topMenuLayout.createSequentialGroup()
                .addGroup(topMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(resumGame,GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pauseGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pikachuPanel = new JPanel();
        pikachuLayout = new GridLayout(row-2,col-2,0,0);
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
                case BT_RESUM : if (playGameListener!=null){
                    playGameListener.onReplayClicked();
                } break;
                case BT_PAUSE : if (playGameListener!=null){
                    playGameListener.onPauseClicked();
                } break;
                default: break;
            }
        }else{
            ++countClicked;
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
                            one.removeBorder();
                        }
                        countClicked = 0;
                        break;
                default: break;
            }
        }
    }

    public void renderMap(int[][] matrix){
        pikachuIcon = new Pikachu[row][col];
        pikachuPanel.removeAll();
        pikachuPanel.invalidate();
        for (int i = 1;i <= row-2; i++){
            for (int j = 1; j <= col-2; j++){
                pikachuIcon[i][j] = createButton(i ,j);
                Icon icon = getIcon(matrix[i][j]);
                pikachuIcon[i][j].setIcon(icon);
                pikachuIcon[i][j].drawBorder(Color.white);
                pikachuPanel.add(pikachuIcon[i][j]);
            }
        }
        pikachuPanel.repaint();
    }

    public void updateMap(int[][] matrix){
        for (int i = 1;i <= row-2; i++){
            for (int j = 1; j <= col-2; j++){
                pikachuIcon[i][j].setIcon(getIcon(matrix[i][j]));
                pikachuIcon[i][j].setVisible(true);
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

    public void updateTimer(String timer){
        this.timer.setText(timer);
    }

    public void updateScore(String score){
        this.score.setText(score);
    }

    public void updateMapNum(String  map){
        this.mapCount.setText(map);
    }

    public void setCountClicked(int value){
        this.countClicked = value;
    }

    public void updateProgress(int progress){
        timerProgress.setValue(progress);
        invalidate();
    }

    public void updateMaxProgress(int progess){
        timerProgress.setMaximum(progess);
        timerProgress.setValue(progess);
    }

    public int getMaxCountDown(){
        return timerProgress.getMaximum();
    }

    public int getCountDownValue(){
        return timerProgress.getValue();
    }

    public String getTextCurrentTime(){
        return timer.getText();
    }

    public interface PlayGameListener{

        /**
         *
         */
        void onReplayClicked();

        /**
         * Được gọi khi nhấn Pause
         */
        void onPauseClicked();

        /**
         *
         * @param clickCounter Trả về số lần click
         * @param pikachus Trả về array pikachu đã đuợc click @arraySize = 2
         */

        void onPikachuClicked(int clickCounter, Pikachu... pikachus);
    }
}
