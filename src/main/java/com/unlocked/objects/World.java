package com.unlocked.objects;

import com.unlocked.objects.car.Car;
import com.unlocked.objects.car.CarHolder;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mtolstykh
 * Date: 19.08.14
 * Time: 17:15
 */
public class World {

    private Landscape landscape;

    public static int selected;

    public Landscape getLandscape() {
        return landscape;
    }

    public void setLandscape(Landscape landscape) {
        this.landscape = landscape;
    }

    public void landscape(GL2 gl2) {
        gl2.glBegin(GL.GL_POINTS);
        for (float i = -500; i < 500.0; i++)
            for (float j = 0; j < 500; j++) {
                if (i < 0 && j < 0) {
                    gl2.glColor3f(255, 0, 255);
                } else if (i > 0 && j > 0) {
                    gl2.glColor3f(255, 255, 255);
                } else if (i > 0 && j < 0) {
                    gl2.glColor3f(0, 255, 255);
                } else if (i < 0 && j > 0) {
                    gl2.glColor3f(255, 255, 0);
                }
//                Short h = landscape.getHeights().get(i + landscape.getHeight()*j);
                gl2.glVertex3f(i/100, j/100, 0.0f);//(float)h/1000);
            }
        gl2.glEnd();
    }

    public void pyramide(GL2 gl2) {
        gl2.glBegin(GL.GL_TRIANGLES);

        gl2.glVertex3f(0.51f, 0.49f, 0.0f);
        gl2.glVertex3f(0.5f, 0.5f, 0.05f);
        gl2.glVertex3f(0.51f, 0.51f, 0.0f);

        gl2.glVertex3f(0.49f, 0.49f, 0.0f);
        gl2.glVertex3f(0.5f, 0.5f, 0.05f);
        gl2.glVertex3f(0.51f, 0.49f, 0.0f);

        gl2.glVertex3f(0.49f, 0.51f, 0.0f);
        gl2.glVertex3f(0.5f, 0.5f, 0.05f);
        gl2.glVertex3f(0.49f, 0.49f, 0.0f);

        gl2.glVertex3f(0.51f, 0.51f, 0.0f);
        gl2.glVertex3f(0.5f, 0.5f, 0.05f);
        gl2.glVertex3f(0.49f, 0.51f, 0.0f);

        gl2.glEnd();
    }

    public void init() {

        CarHolder.setCar(new Car(2.4f, 0.25f, 0.05f, new Vertex(0.0f, 1.0f, 0.05f)));

        drawables.add(CarHolder.getCar());
//        drawables.add(new Qube(new Vertex(0.0f, 0.0f, 0.0f)));
        selected = 0;
    }

    public List<Drawable> drawables = new ArrayList<Drawable>();

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public void draw(GL2 gl2) {
        landscape(gl2);
//        for (Drawable drawable : drawables) {
//            if (drawable instanceof Nonstatic) {
//                ((Nonstatic) drawable).move(((Nonstatic) drawable).direction(), ((Nonstatic) drawable).velocity());
//            }
//            drawable.draw(gl2);
//        }
        CarHolder.getCar().draw(gl2);
    }
}
