package com.unlocked;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
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

    static {
        GLProfile.initSingleton();
    }

    public static GLProfile glp = GLProfile.getMaxFixedFunc(true);
    public static GLCapabilities glcapabilities = new GLCapabilities(glp);
    public static final GLCanvas glcanvas = new GLCanvas(glcapabilities);
    public static GLU glu = new GLU();
    public static KeyboardListener keyboardListener = new KeyboardListener();
    public static GLEventListenerImpl glEventListener = new GLEventListenerImpl(glu);
    public static MouseMovementAdapter mouseMovementAdapter = new MouseMovementAdapter(glcanvas);
    public static GraphicsEnvironment graphicsEnvironment;
    public static DisplayMode dmOld, dm;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("cyc");
        
        glcanvas.addGLEventListener(glEventListener);
        glcanvas.addMouseMotionListener(mouseMovementAdapter);
        glcanvas.addKeyListener(keyboardListener);

        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();

//        dmOld = graphicsDevices[0].getDisplayMode();
//        dm = dmOld;
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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