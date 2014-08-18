import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JOGLQuad {

    public static void main(String[] args) {

        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getMaxFixedFunc(true);

        final JFrame frame = new JFrame("Jogl Quad drawing");
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

        frame.add(glcanvas);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) v += 0.1;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) v -= 0.1;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) x += 0.1;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) x -= 0.1;
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

        frame.setSize(640, 480);
        frame.setVisible(true);
        animator.start();
    }

    static float v = -6.0f;
    static float x = -0.5f;

    protected static void setup(GL2 gl2, int width, int height) {
        gl2.glViewport(0, 0, width, height);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(45.0f, width / height, 0.1f, 100.0f);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
//        glu.gluLookAt(10.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, 5.0f, 5.0f, 5.0f);
        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl2.glLoadIdentity();

        gl2.glTranslatef(x, -0.5f, v);
    }

    static GLU glu = new GLU();
    static float rtri;

    protected static void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();                 // reset the model-view matrix
        gl2.glTranslatef(x, -0.5f, v);
//        gl2.glRotatef(rtri, -1.0f, 1.0f, 1.0f);
        gl2.glBegin(GL.GL_TRIANGLES);
        gl2.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl2.glVertex3f(0.0f, 1.0f, 0.0f);
        gl2.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl2.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl2.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl2.glVertex3f(1.0f, -1.0f, 1.0f);

        // Right-face triangle
        gl2.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl2.glVertex3f(0.0f, 1.0f, 0.0f);
        gl2.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl2.glVertex3f(1.0f, -1.0f, 1.0f);
        gl2.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl2.glVertex3f(1.0f, -1.0f, -1.0f);

        // Back-face triangle
        gl2.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl2.glVertex3f(0.0f, 1.0f, 0.0f);
        gl2.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl2.glVertex3f(1.0f, -1.0f, -1.0f);
        gl2.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl2.glVertex3f(-1.0f, -1.0f, -1.0f);

        // Left-face triangle
        gl2.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl2.glVertex3f(0.0f, 1.0f, 0.0f);
        gl2.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl2.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl2.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl2.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl2.glEnd();
    }

}