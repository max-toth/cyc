import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JOGLQuad {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 780;

    public static void main(String[] args) {

        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getMaxFixedFunc(true);

        final JFrame frame = new JFrame("cyc");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        GLCapabilities glcapabilities = new GLCapabilities(glp);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
        glcanvas.addGLEventListener(new GLEventListener() {

            @Override
            public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
                setup(glautodrawable.getGL().getGL2(), width, height);
            }

            @Override
            public void init(GLAutoDrawable glautodrawable) {
            }

            @Override
            public void dispose(GLAutoDrawable glautodrawable) {
            }

            @Override
            public void display(GLAutoDrawable glautodrawable) {
                render(glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight());
                rtri += 0.2f;
            }
        });

        glcanvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
//                float v = (float) WIDTH / 2;
//                float v1 = (float) e.getX() - v;
//                eyex = v1/100;
//                eyex = e.getX();

                System.out.println(eyex + " " + eyey);
            }
        });
        glcanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) y += 0.01;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) y -= 0.01;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) x -= 0.01;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) x += 0.01;
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                new Thread() {
                    @Override
                    public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });
        frame.getContentPane().add(glcanvas);
//        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
//        frame.setUndecorated(true);     // no decoration such as title bar
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        animator.start();
    }

    static float eyex = 0.0f, eyey = 1.0f;
    static float z = 0.1f;
    static float x = 0.0f;
    static float y = 0.0f;

    protected static void setup(GL2 gl2, int width, int height) {
        gl2.glViewport(0, 0, width, height);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(45.0f, width / height, 0.1f, 100.0f);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);

        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl2.glLoadIdentity();

//        gl2.glTranslatef(x, -0.5f, z);
    }

    static GLU glu = new GLU();
    static float rtri = 0.01f;

    protected static void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();                 // reset the model-view matrix
//        gl2.glTranslatef(x, -0.5f, z);
        glu.gluLookAt(x, y, z, Math.sin(eyex), Math.cos(eyey), 0.1f, 0.0f, 0.0f, 1.0f);
        gl2.glBegin(GL.GL_POINTS);

        for (double s = -3.14; s < 3.14f; s += 0.01f)
            gl2.glVertex3f((float) Math.sin(s), (float) Math.cos(s), (float) s/100);

//        World.landscape(gl2);
        gl2.glEnd();
        World.pyramide(gl2);

    }


}