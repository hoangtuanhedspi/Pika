package com.pikapika.view;

import com.pikapika.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Random;

/**
 * Created by anonymousjp on 5/19/17.
 */
public class SplashView extends JpanelBackground implements PropertyChangeListener{
    /**
     *
     */
    private JProgressBar loadingProgress;

    /**
     *
     */
    private JLabel loadingText;

    /**
     *
     */
    private OnLoadingListener listener;

    /**
     *
     */
    private GroupLayout layout;

    /**
     *
     */
    private Task loadingTask;

    public SplashView(String background) {
        super(background);
        layout = new GroupLayout(this);
        setOpaque(true);
        setLayout(layout);
        initUI();
        setVisible(false);
    }

    private void initUI(){
        loadingProgress = new JProgressBar(0,100);
        loadingProgress.setValue(0);

        loadingText = new JLabel("Loading...");
        loadingText.setFont(new java.awt.Font("Shree Devanagari 714", 0, 15));
        loadingText.setHorizontalAlignment(SwingConstants.CENTER);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                      .addGap(193, 193, 193)
                      .addComponent(loadingProgress, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
                      .addGroup(layout.createSequentialGroup()
                      .addGap(327, 327, 327)
                      .addComponent(loadingText)))
                      .addContainerGap(203, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap(340, Short.MAX_VALUE)
                      .addComponent(loadingProgress, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(loadingText)
                      .addGap(80, 80, 80))
        );

        loadingTask = new Task();
        loadingTask.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int prog = (Integer) evt.getNewValue();
            loadingProgress.setValue(prog);
            if (listener!=null){
                listener.onLoading();
            }
        }
    }

    public void setLoadingListener(OnLoadingListener listener) {
        this.listener = listener;
    }

    public void start(){
        setVisible(true);
        loadingTask.execute();
        if (listener!=null){
            listener.onStartLoading();
        }
    }

    public interface OnLoadingListener{
        void onStartLoading();
        void onLoading();
        void onStopLoading();
    }

    class Task extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            while (progress < 100) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }

        @Override
        public void done() {
            setCursor(null);
            if (listener!=null){
                listener.onStopLoading();
            }
        }
    }
}
