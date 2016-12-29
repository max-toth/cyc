package com.unlocked.objects.car;

import com.unlocked.MathUtils;
import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.Vertex;

import javax.media.opengl.GL2;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * User: mtolstykh
 * Date: 22.08.14
 * Time: 12:09
 */
public class Wheel implements Nonstatic, Drawable {
    private Vertex center;
    private float radius;
    private float alfa = 0;
    private boolean maximum = false;

    public boolean isMaximum() {
        return maximum;
    }

    public Vertex getCenter() {
        return center;
    }

    public void setCenter(Vertex center) {
        this.center = center;
    }

    public float getAlfa() {
        return alfa;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    public Wheel(Vertex center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public void turn(int direction, boolean onOff) {
        switch (direction) {
            case 0: //left

                if (onOff) {
                    this.alfa = 0.8f;
                }
                else {
                    this.alfa = 0;
                }

//                if (this.alfa <= 0.8f) {
//                    this.alfa += 0.03f;
//                    maximum = false;
//                    System.out.println("angle ["+ (alfa * 180 / Math.PI)+"]");
//                } else {
//                    maximum = true;
//                }
                break;
            case 1: //right
                if (onOff) {
                    this.alfa = -0.8f;
                }
                else {
                    this.alfa = 0;
                }
//                if (this.alfa >= -0.8f) {
//                    this.alfa -= 0.03f;
//                    maximum = false;
//                    System.out.println("angle ["+ (alfa * 180 / Math.PI)+"]");
//
//                }
//                else {
//                    maximum = true;
//                }
                break;
        }

    }

    @Override
    public void draw(GL2 gl2) {
        gl2.glBegin(GL2.GL_POLYGON);
        for (double s = 0.0f; s < 1.0f; s += 0.001f) {
            float theta = (float) (2.0f * Math.PI * (s) / 1.0f);
            float x = 0.0f;//(float) (radius * sin(theta));
            float y = (float) (radius * cos(theta));
            float z = (float) (radius * sin(theta));
            Vertex v = new Vertex(x, y, z);
            MathUtils.rotate(v, new Vertex(0, 0, 1), alfa);
            gl2.glVertex3f(
                    v.getX() + center.getX(),
                    v.getY() + center.getY(),
                    v.getZ() + center.getZ()
            );
        }

        gl2.glEnd();
    }

    @Override
    public void move(float x, float y) {
        center.move(x, y);
    }

    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void brakes() {
        center.brakes();
    }

    @Override
    public void inertia() {
        center.inertia();
    }

    @Override
    public float velocity() {
        return center.velocity();
    }

    @Override
    public float direction() {
        return center.getX();
    }
}
