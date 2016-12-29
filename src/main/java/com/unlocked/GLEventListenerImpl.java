package com.unlocked;

import com.jogamp.opengl.util.gl2.GLUT;
import com.unlocked.objects.Vertex;
import com.unlocked.objects.World;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import static javax.media.opengl.GL.*;

/**
 * @author max_tolstykh
 * @since 26/08/14.
 */
public class GLEventListenerImpl implements GLEventListener {

    private GLU glu;
    private World world;

    public GLEventListenerImpl(GLU glu) {
        this.glu = glu;
        world = new World();
    }

    @Override
    public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
        GL2 gl2 = glautodrawable.getGL().getGL2();
        gl2.glViewport(0, 0, width, height);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(90.0f, width / height, 0.1f, 100.0f);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);

        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl2.glLoadIdentity();
    }

    @Override
    public void init(GLAutoDrawable glautodrawable) {
        world.init();
    }

    @Override
    public void dispose(GLAutoDrawable glautodrawable) {
        world.drawables.clear();
    }

    public static float Rotate = 0.0f;

    @Override
    public void display(GLAutoDrawable glautodrawable) {
        GL2 gl2 = glautodrawable.getGL().getGL2();
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        glu.gluLookAt(Camera.x, Camera.y, Camera.z, Camera.eyex, Camera.eyey, Camera.eyez, 0.0f, 0.0f, 1.0f);

        drawSimpleScene(gl2);

        world.draw(gl2);

        cords(gl2);
        gl2.glFlush();

        Const.angle += Const.delta;
        Const.x += Const.deltaStraight;
        Const.X += Const.deltaStraight;
    }

    private void drawSimpleScene(GL2 gl2) {
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glPushMatrix();

        gl2.glBegin(GL_LINES);
        gl2.glVertex3f(Const.x, 1, 0);
        gl2.glVertex3f(Const.x, 1, 3);
        gl2.glEnd();

        gl2.glTranslatef(Const.x, 1, 0);
        gl2.glRotatef(Const.angle, 0, 0, 1);
//        MathUtils.rotate(Const.v, new Vertex(0, 0, 1), Const.angle);
        gl2.glTranslatef(-Const.x, 1, 0);

        gl2.glBegin(GL2.GL_QUADS);
        gl2.glColor3f(0.0f,150,0);
        gl2.glVertex3f(Const.v.getX(), Const.v.getY(), 0);
        gl2.glVertex3f(Const.v.getX(), Const.v.getY() + 0.5f, 0);
        gl2.glColor3f(255,255,255);
        gl2.glVertex3f(Const.v.getX() + 1, Const.v.getY() + 0.5f, 0);
        gl2.glVertex3f(Const.v.getX() + 1, 0, 0);

//        gl2.glColor3f(0.0f,150,0);
//        gl2.glVertex3f(Const.X, 0, 0);
//        gl2.glVertex3f(Const.X, 0.5f, 0);
//        gl2.glColor3f(255,255,255);
//        gl2.glVertex3f(Const.X + 1, 0.5f, 0);
//        gl2.glVertex3f(Const.X + 1, 0, 0);
        gl2.glEnd();

        gl2.glPopMatrix();
    }

    private void cords(GL2 gl2) {
        //X
        gl2.glBegin(GL_LINES);
        gl2.glColor3f(255.0f,0,0);//red
        gl2.glVertex3f(0,0,0);
        gl2.glVertex3f(3, 0,0);
        gl2.glEnd();

//        Y
        gl2.glBegin(GL_LINES);
        gl2.glColor3f(0.0f,150,0); //green
        gl2.glVertex3f(0,0,0);
        gl2.glVertex3f(0,3,0);
        gl2.glEnd();
//        Z
        gl2.glBegin(GL_LINES);
        gl2.glColor3f(0,0,150);
        gl2.glVertex3f(0,0,0);
        gl2.glVertex3f(0, 0,3);
        gl2.glEnd();

        gl2.glColor3f(255,255,255);
    }
}
