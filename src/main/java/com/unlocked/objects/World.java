package com.unlocked.objects;

import com.unlocked.objects.car.Car;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mtolstykh
 * Date: 19.08.14
 * Time: 17:15
 */
public class World {

    public static int selected;

    public static void landscape(GL2 gl2) {
        gl2.glBegin(GL.GL_POINTS);
        for (float i = -5.0f; i < 5.0f; i += 0.01)
            for (float j = -5.0f; j < 5.0f; j += 0.01) {
                if (i < 0 && j < 0) {
                    gl2.glColor3f(255, 0, 255);
                } else if (i > 0 && j > 0) {
                    gl2.glColor3f(255, 255, 255);
                } else if (i > 0 && j < 0) {
                    gl2.glColor3f(0, 255, 255);
                } else if (i < 0 && j > 0) {
                    gl2.glColor3f(255, 255, 0);
                }
                gl2.glVertex3f(i, j, 0.0f);
            }
        gl2.glEnd();
    }

    public static void pyramide(GL2 gl2) {
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

    public static void init() {
        drawables.add(new Car(0.4f, 0.25f, 0.05f, new Vertex(0, 0, 0.05f)));
        drawables.add(new Qube(new Vertex(0.0f, 0.0f, 0.0f)));
        selected = 0;
    }

    public static List<Drawable> drawables = new ArrayList<Drawable>();

    public static void draw(GL2 gl2) {
        landscape(gl2);
        for (Drawable drawable : drawables) {
            if (drawable instanceof Nonstatic) {
                ((Nonstatic) drawable).move(((Nonstatic) drawable).direction(), ((Nonstatic) drawable).velocity());
            }
            drawable.draw(gl2);
        }
    }
}
