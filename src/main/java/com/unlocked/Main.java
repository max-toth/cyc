package com.unlocked;

import com.jogamp.opengl.util.FPSAnimator;
import com.unlocked.awt.CustomWindowAdapter;
import com.unlocked.awt.KeyboardListener;
import com.unlocked.awt.MouseMovementAdapter;
import com.unlocked.objects.*;
import com.unlocked.objects.car.Car;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {

        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getMaxFixedFunc(true);

        final JFrame frame = new JFrame("cyc");

        GLCapabilities glcapabilities = new GLCapabilities(glp);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);

        glcanvas.addGLEventListener(new GLEventListenerImpl(new GLU()));
        glcanvas.addMouseMotionListener(new MouseMovementAdapter(glcanvas));
        glcanvas.addKeyListener(new KeyboardListener());

        final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
        frame.addWindowListener(new CustomWindowAdapter(animator));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(glcanvas);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        animator.start();
    }
}