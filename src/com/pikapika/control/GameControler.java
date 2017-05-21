package com.pikapika.control;

import com.pikapika.utils.Utils;
import com.pikapika.view.MenuView;
import com.pikapika.view.PlayGameView;
import com.pikapika.view.SplashView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class GameControler extends JFrame{
    private SplashView splashView;
    private MenuView menuView;
    private PlayGameView playGameView;

    public GameControler(String title) throws HeadlessException {
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
        this.playGameView = new PlayGameView();
        this.playGameView.setSize(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT);
        this.playGameView.setVisible(true);

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
                playGameView.setVisible(true);
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

        this.add(splashView,BorderLayout.CENTER);
        this.add(menuView,BorderLayout.CENTER);
        this.add(playGameView,BorderLayout.CENTER);
    }

    public void start(){
        //splashView.start();
        setVisible(true);
    }
}
