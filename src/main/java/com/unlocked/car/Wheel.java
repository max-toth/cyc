package com.unlocked.car;

import com.unlocked.Vertex;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * User: mtolstykh
 * Date: 22.08.14
 * Time: 12:09
 */
public class Wheel {
    Vertex center;
    float radius;

    public Wheel(Vertex center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public void draw(GL2 gl2) {
        gl2.glBegin(GL.GL_POINTS);

        int alfa = 45;
        for (double s = 0.0f; s < 1.0f; s += 0.001f) {
            float theta = (float) (2.0f * Math.PI * (s) / 1.0f);
            float x = (float) (radius * sin(theta));
            float y = (float) (radius * cos(theta));
            float z = 0.0f;//(float) (radius * Math.sin(theta));
            gl2.glVertex3f(x + center.getX(), y + center.getY(), z + center.getZ());
        }

        float xx = 0.001f, yy = 0.001f;
        gl2.glVertex3f((float)sin(xx), (float)cos(yy), 0.01f);
//        for (double s = 0.0f; s < 1.0f; s += 0.001f) {
//            float theta = (float) (2.0f * PI * (s) / 1.0f);
//            float x = (float) (radius * cos(theta));
//            float y = (float) (radius * cos(theta));
//            float z = (float) (radius * sin(theta));
//            gl2.glVertex3f(x + center.getX(), y + center.getY(), z + center.getZ());
//        }
//        for (double s = 0.0f; s < 1.0f; s += 0.001f) {
//            float theta = (float) (2.0f * PI * (s) / 1.0f);
//            float turnAngle = (float) (2.0f * PI * (s) / 1.0f);
//            float x = (float) (radius * sin(turnAngle));
//            float y = 0.0f;//(float) (radius * Math.cos(theta));
//            float z = (float) (radius * cos(theta));
//            gl2.glVertex3f(x + center.getX(), y + center.getY(), z + center.getZ());
//        }

        gl2.glEnd();
    }
}
