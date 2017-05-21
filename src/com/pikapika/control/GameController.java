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
/**
 * Created by anonymousjp on 5/20/17.
 */
public class GameController extends JFrame{
    private SplashView splashView;
    private MenuView menuView;
    private PlayGameView playGameView;
//    private int[][] test;
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
        this.splashView.setSize(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT);
        this.menuView = new MenuView("../resources/menu_bg.png");
        this.menuView.setSize(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT);
        this.playGameView = new PlayGameView(8,10);
        this.playGameView.setSize(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT);
//        test = new int[10][10];
        this.matrix = new Matrix(8, 10);    // @Hien add


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
//                for (int i = 0;i < 10;i++){
//                    for (int j = 0; j < 10;j++){
//                        Random random = new Random();
//                        test[i][j] = random.nextInt(10);
//                    }
//                }

                playGameView.renderMatrix(matrix.getMatrix());
                playGameView.setVisible(true);
//                @Hien add
                score = 0;
                mapNumber = 1;
                countDown = 100;
                coupleDone = 0;
                ActionListener timeAction = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        --countDown;
                        playGameView.getTimer().setText("Time: "+countDown);
                        if(countDown == 0){
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
            public void onMenuClicked() {
                playGameView.setVisible(false);
                menuView.setVisible(true);
            }

            @Override
            public void onPauseClicked() {
                // TODO

            }

            @Override
            public void onPikachuClicked(int clickCounter, Pikachu... pikachus) {
                // TODO
//                Utils.debug(getClass(),clickCounter+"");
//                Utils.debug(this.getClass(),pikachus[0].getXPoint() +":"+ pikachus[0].getYPoint()+"");
//                if (clickCounter==2){
//                    Utils.debug(this.getClass(),pikachus[1].getXPoint() +":"+ pikachus[1].getYPoint()+"");
//                }
            }
        });

        this.add(splashView,BorderLayout.CENTER);
        this.add(menuView,BorderLayout.CENTER);
        this.add(playGameView,BorderLayout.CENTER);
    }

    public void start(){
        splashView.start();
        setVisible(true);
    }
}
