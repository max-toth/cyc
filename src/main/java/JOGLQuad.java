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
                float scaleX = scale(glcanvas.getWidth(), e.getX());
                float scaleY = scale(glcanvas.getHeight(), e.getY());

                x = (float) Math.sin(scaleX) + qube.A.x;
                y = (float) Math.cos(scaleX) + qube.A.y;
                z = (float) Math.sin(scaleY);
            }
        });

        glcanvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                qube.a = PhysicConstants.At;
                qube.intertia = true;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    qube.moveUp();
                    y += qube.V;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    qube.brakes();
                    y += qube.V;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN ||
                        e.getKeyCode() == KeyEvent.VK_S) {
                    qube.moveDown();
                    y += qube.V;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    qube.move(-0.01f, 0);
                    x -= 0.01f;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    qube.move(0.01f, 0);
                    x += 0.01f;
                }
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

    static float eyex = 0.0f, eyey = 0.0f, eyez = 0.03f;
    static float z = 0.1f;
    static float x = eyex - 0.05f;
    static float y = eyey;

    protected static void setup(GL2 gl2, int width, int height) {
        gl2.glViewport(0, 0, width, height);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(45.0f, width / height, 0.1f, 100.0f);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);

        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl2.glLoadIdentity();
    }

    static GLU glu = new GLU();
    static float rtri = 0.01f;
    static float direction = 0.0f;
    static Qube qube = World.qube();

    protected static void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        glu.gluLookAt(x, y, z, qube.A.x, qube.A.y, eyez, 0.0f, 0.0f, 1.0f);
        System.out.println("a=" + qube.a + " V=" + qube.V);
        World.circle(gl2);
        World.landscape(gl2);
        qube.move(direction, qube.V);
        qube.draw(gl2);
        World.pyramide(gl2);
    }

    public static float scale(int width, int _x) {
        return (float) (_x - width / 2) * (float) Math.PI / (width / 2);
    }

}