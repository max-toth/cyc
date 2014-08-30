package com.unlocked.awt;

import com.jogamp.opengl.util.FPSAnimator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by max_tolstykh on 30/08/14.
 */
public class CustomWindowAdapter extends WindowAdapter {

    private final FPSAnimator animator;

    public CustomWindowAdapter(FPSAnimator animator) {
        this.animator = animator;
    }

    public void windowClosing(WindowEvent windowevent) {
        new Thread() {
            @Override
            public void run() {
                if (animator.isStarted()) animator.stop();
                System.exit(0);
            }
        }.start();
    }
}
