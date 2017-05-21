package com.pikapika.view;
import com.pikapika.utils.Utils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.pikapika.utils.Utils.*;

/**
 * Created by anonymousjp on 5/20/17.
 */
public class MenuView extends JpanelBackground implements ActionListener{
    public final static int TYPE_EASY = 0;
    public final static int TYPE_MEDIUM = 1;
    public final static int TYPE_HARD = 2;
    private JButton btEasy;
    private JButton btMedium;
    private JButton btHard;
    private JButton btSetting;
    private JButton btQuit;
    private OnClickMenuListener onClickMenuListener;

    public MenuView(String backgroundPath) {
        super(backgroundPath);
        setVisible(false);
        initUI();
    }

    private void initUI() {
        btEasy = new JButton();
        btMedium = new JButton();
        btHard = new JButton();
        btSetting = new JButton();
        btQuit = new JButton();

        btEasy.setText(BT_EASY);
        btEasy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btEasy.addActionListener(this);

        btMedium.setText(BT_MEDIUM);
        btMedium.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btMedium.addActionListener(this);

        btHard.setText(BT_HARD);
        btHard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btHard.addActionListener(this);

        btSetting.setText(BT_SETTING);
        btSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSetting.addActionListener(this);

        btQuit.setText(BT_QUIT);
        btQuit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btQuit.addActionListener(this);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btQuit, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btSetting, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btHard, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btMedium, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btEasy, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(77, Short.MAX_VALUE)
                                .addComponent(btEasy, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btMedium, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btHard, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSetting, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btQuit, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case BT_EASY: if (onClickMenuListener!=null){
                onClickMenuListener.onNewGameClicked(TYPE_EASY);
            } break;
            case BT_MEDIUM: if (onClickMenuListener!=null){
                onClickMenuListener.onNewGameClicked(TYPE_MEDIUM);
            } break;
            case BT_HARD: if (onClickMenuListener!=null){
                onClickMenuListener.onNewGameClicked(TYPE_HARD);
            } break;
            case BT_SETTING: if (onClickMenuListener!=null){
                onClickMenuListener.onSettingClicked();
            } break;
            case BT_QUIT: if (onClickMenuListener!=null){
                onClickMenuListener.onQuitClicked();
            } break;
            default: break;
        }
    }

    public interface OnClickMenuListener{
        void onNewGameClicked(int type);
        void onSettingClicked();
        void onQuitClicked();
    }

    public void setOnClickMenuListener(OnClickMenuListener onClickMenuListener) {
        this.onClickMenuListener = onClickMenuListener;
    }
}
