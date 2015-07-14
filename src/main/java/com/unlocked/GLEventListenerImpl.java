package com.unlocked;

import com.unlocked.objects.Landscape;
import com.unlocked.objects.World;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * @author max_tolstykh
 * @since 26/08/14
 */
public class GLEventListenerImpl implements GLEventListener {

    private GLU glu;
    private World world;


    public GLEventListenerImpl(GLU glu) {
        this.glu = glu;
        this.world = new World();
        this.world.setLandscape(new Landscape(100, 100));
    }

    @Override
    public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
        GL2 gl2 = glautodrawable.getGL().getGL2();
        gl2.glViewport(0, 0, width, height);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(45.0f, width / height, 0.1f, 100.0f);
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
        world.getDrawables().clear();
    }

    @Override
    public void display(GLAutoDrawable glautodrawable) {
        GL2 gl2 = glautodrawable.getGL().getGL2();
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glLoadIdentity();
        glu.gluLookAt(Camera.x, Camera.y, Camera.z, Camera.eyex, Camera.eyey, Camera.eyez, 0.0f, 0.0f, 1.0f);

        world.draw(gl2);
    }
}
