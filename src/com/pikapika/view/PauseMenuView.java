package com.pikapika.view;


import com.pikapika.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.pikapika.utils.Utils.BT_CONTINUE;
import static com.pikapika.utils.Utils.BT_BACK_MENU;
import static com.pikapika.utils.Utils.BT_QUIT;

/**
 * Created by anonymousjp on 5/22/17.
 */
public class PauseMenuView extends JpanelBackground implements ActionListener{
    private JButton continueGame;
    private JButton quitGame;
    private JButton backMenu;
    private GroupLayout layout;
    private PauseMenuListener pauseMenuListener;

    public PauseMenuView() {
        super();
    }

    public PauseMenuView(String imagePath) {
        super(imagePath);
        setVisible(false);
        initUI();
    }

    private void initUI() {
        super.updateUI();
        layout = new GroupLayout(this);
        continueGame = new JButton(BT_CONTINUE);
        continueGame.setSize(100,30);
        continueGame.addActionListener(this);

        backMenu = new JButton(BT_BACK_MENU);
        backMenu.setSize(100,30);
        backMenu.addActionListener(this);

        quitGame = new JButton(BT_QUIT);
        quitGame.setSize(100,30);
        quitGame.addActionListener(this);

        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(180, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(quitGame, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(backMenu, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueGame, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
                    .addGap(180, 180, 180))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(continueGame, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backMenu, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitGame, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(141, Short.MAX_VALUE))
        );

        add(continueGame);
        add(backMenu);
        add(quitGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Utils.debug(getClass(),e.getActionCommand());
        if (pauseMenuListener!=null){
            switch (e.getActionCommand()){
                case BT_CONTINUE : pauseMenuListener.onContinueCliked(); break;
                case BT_BACK_MENU : pauseMenuListener.onBackMenuClicked(); break;
                case BT_QUIT : pauseMenuListener.onQuitClicked(); break;
                default:break;
            }
        }
    }

    public void setPauseMenuListener(PauseMenuListener pauseMenuListener) {
        this.pauseMenuListener = pauseMenuListener;
    }

    public interface PauseMenuListener{
        void onContinueCliked();
        void onBackMenuClicked();
        void onQuitClicked();
    }
}
