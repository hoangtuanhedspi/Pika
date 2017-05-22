package com.pikapika.control;

import com.pikapika.utils.Utils;
import com.pikapika.view.MenuView;
import com.pikapika.view.Pikachu;
import com.pikapika.view.PlayGameView;
import com.pikapika.view.SplashView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.border.LineBorder;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class GameController extends JFrame {

    private SplashView splashView;
    private MenuView menuView;
    private PlayGameView playGameView;
    private Matrix matrix;      // @Hien add
    private Timer timer;
    private int countDown;      // thoi gian dem nguoc
    private int score;          // diem
    private int mapNumber;      // stt map
    private int coupleDone;     // so cap da chon dung

    public GameController(String title) throws HeadlessException {
        super(title);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    protected void frameInit() {
        super.frameInit();
        this.splashView = new SplashView("../resources/splash_background.png");
        this.splashView.setSize(Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        this.menuView = new MenuView("../resources/menu_bg.png");
        this.menuView.setSize(Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        this.playGameView = new PlayGameView(8, 10); 
        this.playGameView.setSize(Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        this.matrix = new Matrix(8, 10);

        this.splashView.setLoadingListener(new SplashView.OnLoadingListener() {
            @Override
            public void onStartLoading() {

            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onStopLoading() {
                splashView.setVisible(false);
                menuView.setVisible(true);
            }
        });

        menuView.setOnClickMenuListener(new MenuView.OnClickMenuListener() {
            @Override
            public void onNewGameClicked(int type) {
                menuView.setVisible(false);
                playGameView.renderMap(matrix.getMatrix());
                int i = (new Random()).nextInt(5);
                playGameView.setBackgroundImage("../resources/bg_"+i+".png");
                playGameView.setVisible(true);
                score = 0;
                mapNumber = 0;
                countDown = 100;
                coupleDone = 0;
                ActionListener timeAction = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        --countDown;
                        playGameView.updateProgress(countDown);
                        playGameView.updateTimer("Time: " + countDown);
                        if (countDown == 0) {
                            JOptionPane.showMessageDialog(null, "TIME OUT, GAME OVER!");
                            timer.stop();
                        }
                    }
                };
                timer = new Timer(1000, timeAction);
                timer.start();

            }

            @Override
            public void onSettingClicked() {
                Utils.debug(getClass(), "setting");
            }

            @Override
            public void onQuitClicked() {
                dispose();
            }
        });

        this.playGameView.setPlayGameListener(new PlayGameView.PlayGameListener() {
            @Override
            public void onReplayClicked() {
                //TODO: Resum old game
            }

            @Override
            public void onPauseClicked(boolean isPlaying) {
                Utils.debug(GameController.this.getClass(),isPlaying+"");
                if (!isPlaying){
                    timer.stop();
                    menuView.setVisible(true);
                    playGameView.setVisible(false);
                }
            }

            @Override
            public void onPikachuClicked(int clickCounter, Pikachu... pikachus) {

                // TODO
                Utils.debug(getClass(), clickCounter + "");
                if (clickCounter == 1) {
                    Utils.debug(this.getClass(), "" + matrix.getXY(pikachus[0]));
                }
                if (clickCounter == 2) {
                    Utils.debug(this.getClass(), "" + matrix.getXY(pikachus[1]));

                }

                if (clickCounter == 1) {
                    pikachus[0].drawBorder(Color.red);
                } else if (clickCounter == 2) {
                    pikachus[1].drawBorder(Color.red);
                    if (matrix.algorithm(pikachus[0], pikachus[1])) {
                        matrix.setXY(pikachus[0], 0);
                        matrix.setXY(pikachus[1], 0);
                        pikachus[0].setVisible(false);
                        pikachus[1].setVisible(false);
                        coupleDone++;
                        score += 100;
                        playGameView.getScore().setText("Score: " + score);
                        if (coupleDone == (matrix.getRow()) * (matrix.getCol()) / 2) {
                            ++mapNumber;
                            if (mapNumber < 3) {  // tinh tu 0, 1, 2
                                countDown -= 15 * mapNumber;
                                String timeStr = playGameView.getTimer().getText();
                                int timeCur = Integer.parseInt(timeStr.substring(6));
                                score = timeCur * 10 + 500;
                                coupleDone = 0;
                                
                                // TODO: Chuyen map moi
                                playGameView.updateMap(matrix.renderMatrix());
                                playGameView.updateScore("Score: "+score);
                                playGameView.updateTimer("Time: "+countDown);
                                playGameView.validate();
                            }
                            else{  // mapNumber == 3
                                // TODO : chuc mung chien thang!
                            }

                        }
                    } else {
                        pikachus[0].removeBorder();
                        pikachus[1].removeBorder();
                        playGameView.setCountClicked(0);

                    }
                }
            }
        });

        this.add(splashView, BorderLayout.CENTER);
        this.add(menuView, BorderLayout.CENTER);
        this.add(playGameView, BorderLayout.CENTER);
    }

    public void start() {
        splashView.start();
        setVisible(true);
    }
}
